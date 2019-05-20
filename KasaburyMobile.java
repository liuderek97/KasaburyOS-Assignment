

/**
 * Kasabury Mobile Phone Class.
 *
 *Assignment 3, Semester 1, 2017.
 *
 * KasaburyMobile
 * In this assignment you will be creating an Kasabury Mobile Phone as part of a simulation.
 * The Mobile phone includes several attributes unique to the phone and has simple functionality.
 * You are to complete 2 classes. KasaburyMobile and KasaburyContact
 *
 * The phone has data
 *  Information about the phone state. 
 *    If it is On/Off
 *    Battery level 
 *    If it is connected to network. 
 *    Signal strength when connected to network
 *  Information about the current owner saved as contact information. 
 *    First name
 *    Last name
 *    Phone number
 *  A list of 10 possible contacts.
 *    Each contact stores first name, last name, phone number and chat history up to 20 messages
 *  
 * The phone has functionality
 *  Turning on the phone
 *  Charging the phone. Increase battery level
 *  Change battery (set battery level)
 *  Use phone for k units of battery (decreases battery level by k)
 *  Search/add/remove contacts
 *
 * Attribute features
 *  if the phone is off. It is not connected. 
 *  if the phone is not connected there is no signal strength
 *  the attribute for battery life has valid range [0,100]. 0 is flat, 100 is full.
 *  the attribute for signal strength has a valid range [0, 5]. 0 is no signal, 5 is best signal.
 * 
 * Please implement the methods provided, as some of the marking is
 * making sure that these methods work as specified.
 *
 * @author A INFO1103 tutor.
 * @date April, 2017
 *
 */
public class KasaburyMobile 
{
	public static final int MAXIMUM_CONTACTS = 10;
	

	/* Use this to store contacts. Do not modify. */
	protected KasaburyContact[] contacts;
	//Phone Data
	protected boolean phoneIsOn;
	protected int batteryLife;
	protected boolean networkConnection;
	protected int signalStrength;
	private int pass;
	
	//Owner Data
	protected KasaburyContact ownerContact;
	


	/* Every phone manufactured has the following attributes
	 * 
	 * the phone is off
	 * the phone has battery life 25
	 * the phone is not connected
	 * the phone has signal strength 0
	 * Each of the contacts stored in the array contacts has a null value
	 * 
	 * the owner first name "Kasabury"
	 * the owner last name is "Incorporated"
	 * the owner phone number is "180076237867"
	 * the owner chat message should have only one message 
	 *         "Thank you for choosing Kasabury products"
	 *
	 */
	public KasaburyMobile() {
		/* given */
		phoneIsOn = false;
		batteryLife = 25;
		networkConnection = false;
		signalStrength = 0;
		pass = 1234;
		
		ownerContact = new KasaburyContact("Kasabury", "Incorporated", "180076237867");
		ownerContact.addChatMessage(ownerContact.getFirstName(), "Thank you for choosing Kasabury products");
		
		contacts = new KasaburyContact[MAXIMUM_CONTACTS];
		
	}

	/* returns a copy of the owner contact details
	 * return null if the phone is off
	 */
	public KasaburyContact getCopyOfOwnerContact() {
		
		if (phoneIsOn == true)
		{
			return ownerContact;
		}
		
		else if (phoneIsOn == false)
		{
			return null;
		}
		
		return null;
	}


	/* only works if phone is on
	 * will add the contact in the array only if there is space and does not exist
	 * The method will find an element that is null and set it to be the contact
	 */
	public boolean addContact(KasaburyContact contact) {
		
		if(phoneIsOn == true)
		{
			for (int i = 0; i < contacts.length; i++)
			{
				if(contacts[i]!=null) {
					return false;
				} else if(contacts[i] == null) {
					contacts[i] = contact;
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	/* only works if phone is on
	 * find the object and set the array element to null
	 */
	public boolean removeContact(KasaburyContact contact) {
		if(phoneIsOn == true) {
			for (int i = 0; i < contacts.length; i++)
			{
				if(contacts[i]!=null) {
					contacts[i] = null;
					return true;
				}
			}
			return false;
		} 
		else
		{
			return false;
		}
	}

	/* only works if phone is on
	 * return the number of contacts, or -1 if phone is off
	 */
	public int getNumberOfContacts() {
		
		if(isPhoneOn())
		{
			int count = 0;
			for (int i = 0; i < contacts.length; i++)
			{
				if(contacts[i] != null) {
					count++;
				}
			}
			return count;
		}
		
		else
		{
			return -1;
		}
	}

	/* only works if phone is on
	 * returns all contacts that match firstname OR lastname
	 * if phone is off, or no results, null is returned
	 */
	public KasaburyContact[] searchContact(String name) {
		KasaburyContact[] returnContacts = new KasaburyContact[]{};
		if(isPhoneOn()) {
			for (int i = 0; i < contacts.length; i++)
			{
				if(contacts[i] != null) {
					if (contacts[i].getFirstName().equals(name) || contacts[i].getLastName().equals(name)) {
						KasaburyContact[] tempContacts = new KasaburyContact[returnContacts.length +1];
						tempContacts[i] = returnContacts[i];
						tempContacts[returnContacts.length] = contacts[i];
						returnContacts = tempContacts;
					}
				}
			}
			
			return returnContacts;
		} else {
			return null;
		}
	}

	/* returns true if phone is on
	 */
	public boolean isPhoneOn() {
		return phoneIsOn;
	}

	/* when phone turns on, it costs 5 battery for startup. network is initially disconnected
	 * when phone turns off it costs 0 battery, network is disconnected
	 * always return true if turning off
	 * return true if can successfully turned on and have at least battery level 1
	 * return false if do not have enough battery level
	 */
	 public boolean setPhoneOn(boolean on) {
		if(on) {
			if (batteryLife >= 6) {
				batteryLife -= 5;
				networkConnection = false;
				phoneIsOn = on;
			}
			return (batteryLife >= 1);
		} else {
			networkConnection = false;
			return true;
		}
		 
	}
	
	/* Return the battery life leve. if the phone is off, zero is returned.
	 */
	public int getBatteryLife() {
		if(phoneIsOn == true)
		{
			return batteryLife;
		}
		
		else
		{
			return 0;
		}

	}
	
	/* Change battery of phone.
	 * On success. The phone is off and new battery level adjusted and returns true
	 * If newBatteryLevel is outside manufacturer specification of [0,100], then 
	 * no changes occur and returns false.
	 */
	public boolean changeBattery(int newBatteryLevel) {
		if(newBatteryLevel >= 0 && newBatteryLevel <= 100) {
			batteryLife = newBatteryLevel;
			setPhoneOn(false);
			return true;
		}
		return false;
	}
	
	/* only works if phone is on. 
	 * returns true if the phone is connected to the network
	 */
	public boolean isConnectedNetwork() {
		if (phoneIsOn == true) {
			return networkConnection;
		} else {
			return false;
		}

	}
	
	/* only works if phone is on. 
	 * when disconnecting, the signal strength becomes zero
	 */
	public void disconnectNetwork() {
		if (phoneIsOn == true) {
			networkConnection = false;
			signalStrength = 0;
		} else {
			return;
		}
	}
	
	/* only works if phone is on. 
	 * Connect to network
	 * if already connected do nothing
	 * if connecting: 
	 *  1) signal strength is set to 1 if it was 0
	 *  2) it will cost 2 battery life to do so
	 * returns the network connected status
	 */
	public boolean connectNetwork() {
		if (phoneIsOn == true) {
			if (isConnectedNetwork()) {
				return true;
			} else {
				signalStrength = (signalStrength == 0) ? 1 : signalStrength;
				batteryLife -= 2;
				return true;
			}
		} else {
			return false;
		}
	}
	
	/* only works if phone is on. 
	 * returns a value in range [1,5] if connected to network
	 * otherwise returns 0
	 */
	public int getSignalStrength() {
		if (phoneIsOn == true) {
			return isConnectedNetwork() ? signalStrength : 0;
		}
		return 0;

	}

	/* only works if phone is on. 
	 * sets the signal strength and may change the network connection status to on or off
	 * signal of 0 disconnects network
	 * signal [1,5] can connect to network if not already connected
	 * if the signal is set outside the range [0,5], nothing will occur and will return false
	 */
	public boolean setSignalStrength(int x) {
		if (phoneIsOn == true) {
			switch(x) {
			case 0 :
				disconnectNetwork();
				return true;
					
			case 1: case 2: case 3: case 4: case 5:
				signalStrength = x;
				
				if(!isConnectedNetwork()) 
				{
					connectNetwork(); 
				}
				return true;
			default :
				return false;
			}
		} else {
			return false;
		}

    }
	
	/* each charge increases battery by 10
	 * the phone has overcharge protection and cannot exceed 100
	 * returns true if the phone was charged by 10
	 */
	public boolean chargePhone()
	{
		if (batteryLife > 90) 
		{
			return false;
		}
		
		batteryLife += 10;
		return true;
	}
	
	/* Use the phone which costs k units of battery life.
	 * if the activity exceeds the battery life, the battery automatically 
	 * becomes zero and the phone turns off.
	 */
	public void usePhone(int k) 
	{	
		batteryLife -= k;
		
		if (batteryLife <= 0) 
		{
			batteryLife = 0; setPhoneOn(false);
		}
	}
	
}

