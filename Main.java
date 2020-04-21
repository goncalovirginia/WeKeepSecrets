import java.util.Scanner;

import documents.ClassifiedDocument;
import secrets.*;
import iterators.*;
import documents.Document;
import users.Officer;
import users.User;
import Accesses.Access;

/**
 * @author Goncalo Virginia - 56773
 *
 * This program manages the interactions between various types of documents and users with security
 * concerns for a governmental organisation.
 */
public class Main {
	
	/* Output Constants */
	private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
	private static final String USER_REGISTERED = "User %s was registered.\n";
	private static final String USER_ID_ALREADY_ASSIGNED = "Identifier %s is already assigned to another user.\n";
	private static final String NO_REGISTERED_USERS = "There are no registered users.";
	private static final String USER_NOT_REGISTERED = "Not a registered user.";
	private static final String DOCUMENT_UPLOADED = "Document %s was uploaded.\n";
	private static final String DOCUMENT_ALREADY_EXISTS = "Document %s already exists in the user account.\n";
	private static final String INSUFFICIENT_CLEARANCE = "Insufficient security clearance.";
	private static final String DOCUMENT_UPDATED = "Document %s was updated.\n";
	private static final String DOCUMENT_DOES_NOT_EXIST = "Document %s does not exist in the user account.\n";
	private static final String DOCUMENT_CANNOT_BE_UPDATED = "Document %s cannot be updated.\n";
	private static final String NOT_REGISTERED_USER = "Not a registered user.";
	private static final String ACCESS_GRANTED = "Access to document %s has been granted.\n";
	private static final String NOT_AN_OFFICER = "Grants can only be issued between officers.";
	private static final String ALREADY_HAS_ACCESS = "Already has access to document %s.\n";
	private static final String ACCESS_REVOKED = "Access to document %s has been revoked.\n";
	private static final String NO_GRANT = "Grant for officer %s does not exist.\n";
	private static final String INAPPROPRIATE_SECURITY_LEVEL = "Inappropriate security level.";
	private static final String ALREADY_REVOKED = "Grant for officer %s was already revoked.\n";
	private static final String NO_LEAKED_DOCUMENTS = "There are no leaked documents.";
	private static final String NO_GRANTS_GIVEN = "No officer has given grants.";
	private static final String NO_ACCESSES = "There are no accesses.";
	private static final String NO_GRANTS = "There are no grants.";
	private static final String NO_OFFICIAL_DOCUMENTS = "There are no official documents.";
	private static final String NO_CLASSIFIED_DOCUMENTS = "There are no classified documents.";
	private static final String EXITING = "Bye!";
	
	/**
	 * Reads and executes input commands repeatedly until the EXIT command is introduced.
	 * @param args Arguments used for the execution of the program (not used).
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		SecretManager secretManager = new SecretManagerClass();
		Command command = null;
		
		while (command != Command.EXIT) {
			try {
				command = Command.valueOf(in.nextLine().toUpperCase());
				executeCommand(command, in, secretManager);
			}
			catch (IllegalArgumentException e) {
				unknownCommand();
			}
		}
		
		in.close();
	}
	
	/**
	 * Interprets and executes commands.
	 * @param command Command introduced by the user.
	 * @param in Input scanner.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void executeCommand(Command command, Scanner in, SecretManager secretManager) {
		switch (command) {
			case EXIT:
				exit();
				break;
			case HELP:
				help();
				break;
			case REGISTER:
				registerUser(in, secretManager);
				break;
			case LISTUSERS:
				listUsers(secretManager);
				break;
			case UPLOAD:
				uploadDocument(in, secretManager);
				break;
			case WRITE:
				writeDocument(in, secretManager);
				break;
			case READ:
				readDocument(in, secretManager);
				break;
			case GRANT:
				grantClearance(in, secretManager);
				break;
			case REVOKE:
				revokeClearance(in, secretManager);
				break;
			case USERDOCS:
				listUserDocuments(in, secretManager);
				break;
			case TOPLEAKED:
				listTopLeakedDocuments(secretManager);
				break;
			case TOPGRANTERS:
				listTopGranters(secretManager);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Prints out the unknown command message.
	 */
	private static void unknownCommand() {
		System.out.println(UNKNOWN_COMMAND);
	}
	
	/**
	 * Prints out the exit message.
	 */
	private static void exit() {
		System.out.println(EXITING);
	}
	
	/**
	 * Prints out the list of possible input commands and their description.
	 */
	private static void help() {
		for (Command command: Command.values()) {
			System.out.println(command.toString().toLowerCase() + " - " + command.getDescription());
		}
	}
	
	/**
	 * Reads and checks the parameters in order to register a new user.
	 * @param in Input scanner.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void registerUser(Scanner in, SecretManager secretManager) {
		String type = in.next().toLowerCase();
		String userId = in.next();
		String level = in.next();
		in.nextLine();
		
		if (secretManager.userExists(userId)) {
			System.out.printf(USER_ID_ALREADY_ASSIGNED, userId);
		}
		else {
			secretManager.registerUser(type, userId, level);
			System.out.printf(USER_REGISTERED, userId);
		}
	}
	
	/**
	 * Lists all registered users.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void listUsers(SecretManager secretManager) {
		UserIterator users = secretManager.newUserIterator();
		
		if (!users.hasNext()) {
			System.out.println(NO_REGISTERED_USERS);
		}
		else {
			while (users.hasNext()) {
				User user= users.next();
				System.out.println(user.getType() + " " + user.getId() + " " + user.getLevel());
			}
		}
	}
	
	/**
	 * Reads and checks the parameters in order to upload a new document.
	 * @param in Input scanner.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void uploadDocument(Scanner in, SecretManager secretManager) {
		String documentName = in.next();
		String userId = in.next();
		String documentLevel = in.next();
		String documentDescription = in.next() + in.nextLine();
		
		if (!secretManager.userExists(userId)) {
			System.out.println(USER_NOT_REGISTERED);
		}
		else if (secretManager.userHasDocument(userId, documentName)) {
			System.out.printf(DOCUMENT_ALREADY_EXISTS, documentName);
		}
		else if (!secretManager.userHasLevel(userId, documentLevel)) {
			System.out.println(INSUFFICIENT_CLEARANCE);
		}
		else {
			secretManager.uploadDocument(documentName, userId, documentLevel, documentDescription);
			System.out.printf(DOCUMENT_UPLOADED, documentName);
		}
	}
	
	/**
	 * Reads and checks the parameters in order to edit a documents' description.
	 * @param in Input scanner.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void writeDocument(Scanner in, SecretManager secretManager) {
		String documentName = in.next();
		String userIdOwner = in.next();
		String userIdWriter = in.next();
		String description = in.next() + in.nextLine();
		
		if (!secretManager.userExists(userIdOwner) || !secretManager.userExists(userIdWriter)) {
			System.out.println(NOT_REGISTERED_USER);
		}
		else if (!secretManager.userHasDocument(userIdOwner, documentName)) {
			System.out.printf(DOCUMENT_DOES_NOT_EXIST, documentName);
		}
		else if (!secretManager.documentIsClassified(userIdOwner, documentName)) {
			System.out.printf(DOCUMENT_CANNOT_BE_UPDATED, documentName);
		}
		else if (!secretManager.userHasClearance(userIdWriter, userIdOwner, documentName)) {
			System.out.println(INSUFFICIENT_CLEARANCE);
		}
		else {
			secretManager.writeDocument(userIdWriter, userIdOwner, documentName, description);
			System.out.printf(DOCUMENT_UPDATED, documentName);
		}
	}
	
	/**
	 * Reads and checks the parameters in order to output a documents' description.
	 * @param in Input scanner.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void readDocument(Scanner in, SecretManager secretManager) {
		String documentName = in.next();
		String userIdOwner = in.next();
		String userIdReader = in.next();
		in.nextLine();
		
		if (!secretManager.userExists(userIdOwner) || !secretManager.userExists(userIdReader)) {
			System.out.println(NOT_REGISTERED_USER);
		}
		else if (!secretManager.userHasDocument(userIdOwner, documentName)) {
			System.out.printf(DOCUMENT_DOES_NOT_EXIST, documentName);
		}
		else if (!secretManager.userHasClearance(userIdReader, userIdOwner, documentName)) {
			System.out.println(INSUFFICIENT_CLEARANCE);
		}
		else {
			System.out.printf("Document: %s\n", secretManager.readDocument(userIdReader, userIdOwner, documentName));
		}
	}
	
	/**
	 * Reads and checks the parameters in order for a user to grant clearance to another user.
	 * @param in Input scanner.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void grantClearance(Scanner in, SecretManager secretManager) {
		String documentName = in.next();
		String userIdOwner = in.next();
		String userIdGrant = in.next();
		in.nextLine();
		
		if (!secretManager.userExists(userIdOwner) || !secretManager.userExists(userIdGrant)) {
			System.out.println(NOT_REGISTERED_USER);
		}
		else if (!secretManager.userIsOfficer(userIdOwner) || !secretManager.userIsOfficer(userIdGrant)) {
			System.out.println(NOT_AN_OFFICER);
		}
		else if (!secretManager.userHasDocument(userIdOwner, documentName)) {
			System.out.printf(DOCUMENT_DOES_NOT_EXIST, documentName);
		}
		else if (secretManager.userHasClearance(userIdGrant, userIdOwner, documentName)) {
			System.out.printf(ALREADY_HAS_ACCESS, documentName);
		}
		else {
			secretManager.grantClearance(userIdGrant, userIdOwner, documentName);
			System.out.printf(ACCESS_GRANTED, documentName);
		}
	}
	
	/**
	 * Reads and checks the parameters in order for a user to revoke the clearance granted to another user.
	 * @param in Input scanner.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void revokeClearance(Scanner in, SecretManager secretManager) {
		String documentName = in.next();
		String userIdOwner = in.next();
		String userIdRevoke = in.next();
		in.nextLine();
		
		if (!secretManager.userExists(userIdOwner) || !secretManager.userExists(userIdRevoke)) {
			System.out.println(NOT_REGISTERED_USER);
		}
		else if (!secretManager.userIsOfficer(userIdOwner) || !secretManager.userIsOfficer(userIdRevoke)) {
			System.out.println(NOT_AN_OFFICER);
		}
		else if (!secretManager.userHasDocument(userIdOwner, documentName)) {
			System.out.printf(DOCUMENT_DOES_NOT_EXIST, documentName);
		}
		else if (!secretManager.userHasClearance(userIdRevoke, userIdOwner, documentName) &&
				!secretManager.userHasGrantRevoked(userIdRevoke, userIdOwner, documentName)) {
			System.out.printf(NO_GRANT, userIdRevoke);
		}
		else if (secretManager.userHasGrantRevoked(userIdRevoke, userIdOwner, documentName)) {
			System.out.printf(ALREADY_REVOKED, userIdRevoke);
		}
		else {
			secretManager.revokeClearance(userIdRevoke, userIdOwner, documentName);
			System.out.printf(ACCESS_REVOKED, documentName);
		}
	}
	
	/**
	 * Reads and checks the parameters in order to list a users' documents (of a certain clearance level).
	 * @param in Input scanner.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void listUserDocuments(Scanner in, SecretManager secretManager) {
		String userId = in.next();
		String documentType = in.next();
		in.nextLine();
		
		if (!secretManager.userExists(userId)) {
			System.out.println(NOT_REGISTERED_USER);
		}
		else if (!secretManager.userIsOfficer(userId) && secretManager.isClassified(documentType)) {
			System.out.println(INAPPROPRIATE_SECURITY_LEVEL);
		}
		else {
			if (!secretManager.isClassified(documentType)) {
				officialDocumentAccesses(userId, secretManager);
			}
			else {
				classifiedDocumentAccesses(userId, secretManager);
			}
		}
	}
	
	/**
	 * Auxiliary method for 'listUserDocuments'. Lists all accesses of a certain type.
	 * @param userId The document owners' ID.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void officialDocumentAccesses(String userId, SecretManager secretManager) {
		OfficialDocumentIterator documents = secretManager.newUserOfficialDocumentsIterator(userId);
		
		if (!documents.hasNext()) {
			System.out.println(NO_OFFICIAL_DOCUMENTS);
		}
		else {
			while (documents.hasNext()) {
				Document document = documents.next();
				System.out.printf("%s %d: ", document.getName(), document.getNumAccesses());
				
				AccessIterator accesses = document.newReverseAccessIterator();
				
				if (!accesses.hasNext()) {
					System.out.println(NO_ACCESSES);
				}
				else {
					while (accesses.hasNext()) {
						Access access = accesses.next();
						
						System.out.printf("%s [%s]", access.getUser().getId(), access.getUser().getLevel());
						
						if (accesses.hasNext()) {
							System.out.print(", ");
						}
						else {
							System.out.println();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Auxiliary method for 'listUserDocuments'. Lists all accesses of a certain type.
	 * @param userId The document owners' ID.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void classifiedDocumentAccesses(String userId, SecretManager secretManager) {
		ClassifiedDocumentIterator documents = secretManager.newUserClassifiedDocumentsIterator(userId);
		
		if (!documents.hasNext()) {
			System.out.println(NO_CLASSIFIED_DOCUMENTS);
		}
		else {
			while (documents.hasNext()) {
				ClassifiedDocument document = documents.next();
				System.out.printf("%s %s %d\n", document.getName(), document.getLevel(), document.getNumAccesses());
				
				AccessIterator accesses = document.newAccessIterator();
				
				if (!accesses.hasNext()) {
					System.out.println(NO_ACCESSES);
				}
				else {
					printAccesses(accesses);
				}
				
				AccessIterator grants = document.newGrantIterator();
				
				if (!grants.hasNext()) {
					System.out.println(NO_GRANTS);
				}
				else {
					printAccesses(grants);
				}
			}
		}
	}
	
	/**
	 * Auxiliary method for 'classifiedDocumentAccesses'. Lists all accesses of a certain type.
	 * @param accesses Accesses to iterate.
	 */
	private static void printAccesses(AccessIterator accesses) {
		while (accesses.hasNext()) {
			Access access = accesses.next();
			
			System.out.printf("%s [%s, %s]", access.getUser().getId(), access.getUser().getLevel(), access.getType());
			
			if (accesses.hasNext()) {
				System.out.print(", ");
			}
			else {
				System.out.println();
			}
		}
	}
	
	/**
	 * Lists the top 10 most leaked (granted) documents.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void listTopLeakedDocuments(SecretManager secretManager) {
		ClassifiedDocumentIterator documents = secretManager.newTopLeakedDocumentsIterator();
		
		if (!documents.hasNext()) {
			System.out.println(NO_LEAKED_DOCUMENTS);
		}
		else {
			while (documents.hasNext()) {
				ClassifiedDocument document = documents.next();
				System.out.println(document.getName() + " " + document.getOwner() + " " + document.getLevel() + " " +
						document.getNumAccesses() + " " + document.getNumGrants() + " " + document.getNumRevokes());
			}
		}
	}
	
	/**
	 * Lists the top 10 officers that have given the most grants.
	 * @param secretManager Manages and controls the interactions between users and documents.
	 */
	private static void listTopGranters(SecretManager secretManager) {
		OfficerIterator granters = secretManager.newTopGrantersIterator();
		
		if (!granters.hasNext()) {
			System.out.println(NO_GRANTS_GIVEN);
		}
		else {
			while (granters.hasNext()) {
				Officer granter = granters.next();
				System.out.println(granter.getId() + " " + granter.getLevel() + " " + granter.getNumDocuments() +
						" " + granter.getNumGrants() + " " + granter.getNumRevokes());
			}
		}
	}
	
}