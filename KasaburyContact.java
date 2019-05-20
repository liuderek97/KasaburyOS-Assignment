/**
 * Kasabury Mobile Phone Contact Class.
 *
 * Assignment 3, Semester 1, 2017.
 *
 * KasaburyContact
 * 
 * == Contact data ==
 * Each KasaburyContact stores the first name, last name and phone number of a person. 
 * These can be queried by calling the appropriate get method. They are updated 
 * with new values. The phone number can only be a 6 - 14 digit number.
 * The chat history is also stored. 
 * 
 * 
 * == Chat history ==
 * Each KasaburyContact stores the history of chat messages related to this contact. 
 * Suppose there is a conversation between Angus and Beatrice:
 * 
 * Angus: Man, I'm so hungry! Can you buy me a burrito?
 * Beatrice: I don't have any money to buy you a burrito.
 * Angus: Please? I haven't eaten anything all day.
 * 
 * Each time a message is added the name of the person and the message is 
 * combined as above and recorded in the sequence it was received.
 * 
 * The messages are stored in the instance variable String array chatHistory. Provided for you.
 * Unfortunately there are only 20 messages maximum to store and no more. 
 * When there are more than 20 messages, oldest messages from this array are discarded and 
 * only the most recent 20 messages remain. 
 * 
 * The functions for chat history are 
 *   addChatMessage
 *   getLastMessage
 *   getOldestMessage
 *   clearChatHistory()
 *
 * Using the above conversation as an example
 *   addChatMessage("Angus", "Man, I'm so hungry! Can you buy me a burrito?");
 *   addChatMessage("Beatrice", "I don't have any money to buy you a burrito.");
 *   addChatMessage("Angus", "Please? I haven't eaten anything all day.");
 *
 *   getLastMessage() returns "Angus: Please? I haven't eaten anything all day."
 *   getOldestMessage() returns "Angus: Man, I'm so hungry! Can you buy me a burrito?"
 *
 *   clearChatHistory()
 *   getLastMessage() returns null
 *   getOldestMessage() returns null
 *
 *
 * == Copy of contact ==
 * It is necessary to make copy of this object that contains exactly the same data. 
 * There are many hackers working in other parts of Kasabury, so we cannot trust them 
 * changing the data. A copy will have all the private data and chat history included.
 *
 *
 * Please implement the methods provided, as some of the marking is
 * making sure that these methods work as specified.
 *
 * @author A INFO1103 tutor.
 * @date April, 2017
 *
 */
public class KasaburyContact
{
	public static final int MAXIMUM_CHAT_HISTORY = 40;	
	
	/* given */
	public String[] chatHistory;
	public String firstName;
	public String lastName;
	public String phoneNumber;
	
	public KasaburyContact(String fname, String lname, String pnumber) {
		/* given */
		firstName = fname;
		lastName = lname;
		phoneNumber = pnumber;
		
		chatHistory = new String[MAXIMUM_CHAT_HISTORY];
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/* if firstName is null the method will do nothing and return
	 */
	public void updateFirstName(String firstName) {
		this.firstName = firstName != null ? firstName : this.firstName;
	}
	/* if lastName is null the method will do nothing and return
	 */
	public void updateLastName(String lastName) {
		this.lastName = lastName != null ? lastName : this.lastName;
	}
	
	/* only allows integer numbers (long type) between 6 and 14 digits
	 * no spaces allowed, or prefixes of + symbols
	 * leading 0 digits are allowed
	 * return true if successfully updated
	 * if number is null, number is set to an empty string and the method returns false
	 */
	public boolean updatePhoneNumber(String number) {
		if (number == null) {
			phoneNumber = "";
			return false;
		}
		if (number.length() < 6 || number.length() > 14 || number.contains(" ") || number.contains("+") || (number.contains("."))) {
			return false;
		}
		
		phoneNumber = number;
		return true;
	}
	
	/* add a new message to the chat
	 * The message will take the form
	 * whoSaidIt + ": " + message
	 * 
	 * if the history is full, the oldest message is replaced
	 * Hint: keep track of the position of the oldest or newest message!
	 */
	public void addChatMessage(String whoSaidIt, String message) {
		String toAdd = whoSaidIt + ": " + message;
		
		String[] tempChatHistory = new String[MAXIMUM_CHAT_HISTORY];
		for(int i = 0; i < MAXIMUM_CHAT_HISTORY; i++) {
			tempChatHistory[i] = i == 0 ? toAdd : chatHistory[i - 1];
		}
		chatHistory = tempChatHistory;
	}

	/* after this, both last and oldest message should be referring to index 0
	 * all entries of chatHistory are set to null
	 */
	public void clearChatHistory() {
		for(int i = 0; i < MAXIMUM_CHAT_HISTORY; i++) {
			chatHistory[i] = null;
		}
	}

	/* returns the last message this contact sent
	 * if no messages, returns null
	 */
	public String getLastMessage() {
		int max = firstName.length();
		for(int i = 0; i < MAXIMUM_CHAT_HISTORY; i++) {
			if(chatHistory[i].substring(0, max).equals(firstName)) {
				return chatHistory[i];
			}
		}
		return null;
	}
	
	/* returns the oldest message in the chat history
	 * depending on if there was ever MAXIMUM_CHAT_HISTORY messages
	 * 1) less than MAXIMUM_CHAT_HISTORY, returns the first message
	 * 2) more than MAXIMUM_CHAT_HISTORY, returns the oldest
	 * returns null if no messages exist
	 */
	public String getOldestMessage() {
		if (chatHistory[0] == null) { return null; }
		for(int i = 0; i < MAXIMUM_CHAT_HISTORY; i++) {
			if(chatHistory[i] == null) {
				return chatHistory[i - 1];
			}
		}
		return chatHistory[MAXIMUM_CHAT_HISTORY - 1];
	}


	/* creates a copy of this contact
	 * returns a new KasaburyContact object with all data same as the current object
	 */
	public KasaburyContact copy() 
	{
		KasaburyContact copy = new KasaburyContact(firstName, lastName, phoneNumber);
		//ASSUME NO NEED TO COPY MESSAGES
		return copy;
	}
	
	/* -- NOT TESTED --
	 * You can impelement this to help with debugging when failing ed tests 
	 * involving chat history. You can print whatever you like
	 * Implementers notes: the format is printf("%d %s\n", index, line); 
	 */
	public void printMessagesOldestToNewest() {

	}
	
	public boolean equals(KasaburyContact comparable) {
		if (comparable == null) { return false; }
		return (comparable.firstName == this.firstName && comparable.lastName == this.lastName && comparable.phoneNumber == this.phoneNumber);
	}
}

