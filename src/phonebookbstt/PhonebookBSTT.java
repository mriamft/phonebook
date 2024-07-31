/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package phonebookbstt;
import java.util.Scanner;

/**
 *
 * @author Maryam
 */
public class PhonebookBSTT {


/**
 *
 * @author Maryam
 */

    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        BST <String,Contact> contacts= new BST <String, Contact>();
        LinkedList<Event> events = new LinkedList<Event>();

            

        System.out.println("Welcome to BST Phonebook!");
        
        int answer; //user's answer
        do {
            
            System.out.println("Please choose an option:\n" +
            "1. Add a contact\n" +
            "2. Search for a contact\n" +
            "3. Delete a contact\n" +
            "4. Schedule an event\n" +
            "5. Print event details\n" +
            "6. Print contacts by first name\n" +
            "7. Print all events alphabetically\n" +
            "8. Contact with shared event\n" +
            "9. Exit\n" +
            "Enter your choice:");
            answer = input.nextInt();
            input.nextLine();
  
            switch (answer) {
                case 1: ////////////////////////////////ADD CONTACT/////////////////////////////////////
                    System.out.print("Enter the contact's name: ");
                    String name = input.nextLine();
                    System.out.print("Enter the contact's phone number: ");
                    String phone = input.nextLine();
                    System.out.print("Enter the contact's email address: ");
                    String email = input.nextLine();
                    System.out.print("Enter the contact's address: ");
                    String address = input.nextLine();
                    System.out.print("Enter the contact's birthday: ");
                    String birthday = input.nextLine();
                    System.out.print("Enter any notes for the contact: ");
                    String notes = input.nextLine();
                    System.out.println(); //clean garpage
                    Contact contact = new Contact(name, phone, email, address, birthday, notes);

                    // Check if the contact phone number already exists
                    if (!uniquePhone(contacts.getRoot(),phone)){
                        System.out.println("Contact with the same phone number already exists, Try again");
                        break;
                    }
             
                    //Phone is uniqe, Check the name and inesrt
                    if(contacts.insert(contact.getName(),contact))
                        System.out.println("Contact added successfull");
                    else
                        System.out.println("Contact with the same name already exists, Try again");
                    
                    break;
                    
                    
                case 2:///////////////////////////SEARCH for a contact/////////////////////////////
                    System.out.print("Enter search criteria:\n" +
                    "1. Name\n" +
                    "2. Phone Number\n" +
                    "3. Email Address\n" +
                    "4. Address\n" +
                    "5. Birthday\n" +
                    "Enter your choice:");
                    int criteria = input.nextInt();
                    input.nextLine();//to clean garbage
                    
                    Contact result= searchContatc(criteria,contacts);
                    if(result==null)
                        System.out.println("No result founded");
                    else
                        System.out.println("Contact founded! \n"+result.toString());
                    break;
                    
                            
                case 3: // DELETE a contact
                    System.out.println("Insert phone number of contact you want to delete");
                    String phoneNumber = input.nextLine();
                    Contact deleted = delete(phoneNumber, contacts, events);

                    if (deleted == null) {
                        System.out.println("Wrong phone number");
                    } else {
                        contacts.remove_key(deleted.getName());
                        System.out.println(deleted.toString() + "\nDeleted successfully");
                    }
                    break;
                    
                    
                case 4: // Add an event
    System.out.println("Enter type:\n1. Event\n2. Appointment");
    int type = input.nextInt();
    input.nextLine();
    boolean isEvent = (type == 1);

    System.out.print("Enter title: ");
    String title = input.nextLine();
    System.out.print("Enter contact names separated by commas: ");
    String line = input.nextLine();
    String[] names = line.split(",");

    System.out.print("Enter event date and time (MM/DD/YYYY HH:MM): ");
    String dateTime = input.nextLine();
    int space1 = dateTime.indexOf(' ');
    String date = dateTime.substring(0, space1); // MM/DD/YYYY
    String time = dateTime.substring(space1 + 1); // HH:MM

    System.out.print("Enter event location: ");
    String location = input.nextLine();

    boolean eventAdded = false;
    Event event = new Event(title, date, time, location, isEvent, null);

    for (int i = 0; i < names.length; i++) {
        String contactName = names[i].trim();

        // Check if the contact exists
        Contact eventContact = searchByName(contacts, contactName);
        if (eventContact == null) {
            System.out.println("Contact not available: " + contactName);
            continue;
        }

        // Check for conflicts
        if (eventContact.hasConflict(time, date)) {
            System.out.println("There is a conflict for contact: " + contactName);
            continue;
        }

        if (!isEvent && eventContact.hasAppointment()) {
            System.out.println("Only one appointment is allowed per contact.");
            continue;
        }

        if (event.getContactList() == null) {
            LinkedList<Contact> eventContacts = new LinkedList<>();
            eventContacts.insert(eventContact);
            event.setContactList(eventContacts);
        } else {
            event.getContactList().insert(eventContact);
        }

        if (eventContact.addEvent(event)) {
            System.out.println("Event added successfully for contact: " + eventContact.getName());
            eventAdded = true;
        } else {
            System.out.println("Failed to add event for contact: " + eventContact.getName());
        }
    }

    if (eventAdded) {
        events.insert(event);
        System.out.println("Event added successfully.");
    } else {
        System.out.println("No events added successfully.");
    }
    break;    
                case 5: // PRINT EVENT details
                    System.out.print("Enter search for Event:\n" +
                    "1. By Contact Name\n" +
                    "2. By Event Title\n"+
                    "Enter your choice:");
                    int choice = input.nextInt();
                    input.nextLine(); // to clean garbage
                    searchEvent(contacts, events, choice);
                    break;

                case 6:///////////////////////////PRINT CONTACT by first name//////////////////
                    System.out.print("Enter the contact's First name: ");
                    String fname = input.nextLine();
                    if (contacts.empty())
                        System.out.println("Empty tree, try again");
                    else
                    searchContactsByFirstName(contacts.getRoot(), fname);
                    break;
                    
                case 7:///////////////////////////PRINT ALL EVENTS alphabetically//////////////
                    events.printResults();
                    break;
                    
                case 8:///////////////////////////CONTACT with SHARED event/////////////////////////
                    System.out.println("Enter the Event title: ");
                    String eventTitle = input.nextLine();                    
                    sharedEvent(events, eventTitle);
                    break;
            }
        }while (answer != 9);
        System.out.println("Good Bye!");
    }
    
    
    /////////////////////////////////////METHODS////////////////////////////////////////////
    public static boolean uniquePhone(BST<String, Contact> contact,String phone) {
        return uniquePhone(contact.getRoot(),phone);
    }
    private static boolean uniquePhone(BSTNode<String, Contact> node, String phone) {
        if (node == null) 
            return true; // unique
    
        else 
            if (node.getData().getPhone().compareTo(phone)==0)
                return false;
        return (uniquePhone(node.getLeft() , phone)&&uniquePhone(node.getRight(), phone));
     
    }
    
    ////////////////////////////////////////////////////////////////////////////////////
    
    public static Contact searchContatc(int choice, BST<String, Contact> contact){
        Contact result=null;
        String answer=null;
        switch(choice){
            case 1://///////////////////////search by name//////////////////////////////
                System.out.print("Enter the contact's name: ");
                answer=input.nextLine();
                result= searchByName (contact,answer);
                break;
            case 2://///////////////////////search by phone/////////////////////////////
                System.out.print("Enter the contact's Phone Number: ");
                answer=input.nextLine();
                result= searchByPhone (contact,answer);
                break;
            case 3://///////////////////////search by email/////////////////////////////
                System.out.print("Enter the contact's Email: ");
                answer=input.nextLine();
                result= searchByEmail (contact,answer);
                break;
            case 4://///////////////////////search by address/////////////////////////////
                System.out.print("Enter the contact's Address: ");
                answer=input.nextLine();
                result= searchByAddress (contact,answer);
                break;
            case 5://///////////////////////search by birthday/////////////////////////////
                System.out.print("Enter the contact's Birthday: ");
                answer=input.nextLine();
                result= searchByBirthday (contact,answer);
                break;
        }       
        return result;
        
    }
    
    ////////////////////////////////////Event search methods//////////////////////////////////////////////////
    public static void searchEvent( BST<String, Contact> contact,LinkedList<Event> events, int choice) {
        Contact result = null;
        LinkedList<Event> result2=null;
        String answer = null;

        switch (choice) {
            case 1: ///////////////////////// search by contact name//////////////////////////////////////////
                System.out.print("Enter the contact's name: ");
                answer = input.nextLine();
                result = searchByName(contact, answer);
                if (result!=null){
                    LinkedList<Event> eventList = result.getEventList();
                    eventList.printResults();

                } 
                else 
                System.out.println("No events found for the contact.");
            break;
            
            
            case 2://///////////////////////////search by title///////////////////////////////////////////////
                System.out.print("Enter the event's title: ");
                answer = input.nextLine();
                result2= searchByTitle(events,answer);
                if (result2==null)
                    System.out.println("Not founded");
                else
                // Print the matched events
                    result2.printResults();

                    break;
    }
    }
    
    ////////////////////////////////////Contact search methods//////////////////////////////////////////////////
    
    public static Contact searchByName(BST<String, Contact> contact, String name) {
    return searchByName(contact.getRoot(), name);
}

private static Contact searchByName(BSTNode<String, Contact> node, String name) {
    if (node == null) {
        return null;
    }

    int comparisonResult = name.compareToIgnoreCase(node.getKey());
    if (comparisonResult == 0) {
        return node.getData();
    } else if (comparisonResult < 0) {
        return searchByName(node.getLeft(), name);
    } else {
        return searchByName(node.getRight(), name);
    }
}
    

    public static LinkedList<Event> searchByTitle(LinkedList<Event> eventList, String title) {
    LinkedList<Event> matchedEvents = new LinkedList<>();

    if (!eventList.empty()) {
        eventList.findFirst();
        while (!eventList.last()) {
            if (eventList.retrieve().getTitle().compareTo(title) == 0) {
                matchedEvents.insert(eventList.retrieve());
            }
            eventList.findNext();
        }
        if (eventList.retrieve().getTitle().compareTo(title) == 0) {
            matchedEvents.insert(eventList.retrieve());
        }
    }

    return matchedEvents;
}
    

    
    public static Contact searchByPhone(BST<String, Contact> contact, String phone) {
        return searchByPhone(contact.getRoot(), phone);
    }

    private static Contact searchByPhone(BSTNode<String, Contact> node, String phone) {
        if (node == null) {
            return null;
        } 
        else 
            if (node.getData().getPhone().compareToIgnoreCase(phone) == 0) {
                return node.getData();
            }

        Contact leftResult = searchByPhone(node.getLeft(), phone);
        if (leftResult != null) {
            return leftResult;
        }

        return searchByPhone(node.getRight(), phone);
    }
    
    
    
    public static Contact searchByEmail(BST<String, Contact> contact, String email) {
        return searchByEmail(contact.getRoot(), email);
    }

    private static Contact searchByEmail(BSTNode<String, Contact> node, String email) {
        if (node == null) {
            return null;
        } 
        else 
            if (node.getData().getEmail().compareToIgnoreCase(email) == 0) {
                return node.getData();
            }

        Contact leftResult = searchByEmail(node.getLeft(), email);
        if (leftResult != null) {
            return leftResult;
        }

        return searchByEmail(node.getRight(), email);
    }
    
    
    
    public static Contact searchByAddress(BST<String, Contact> contact, String address) {
        return searchByAddress(contact.getRoot(), address);
    }

    private static Contact searchByAddress(BSTNode<String, Contact> node, String address) {
        if (node == null) {
            return null;
        } 
        else 
            if (node.getData().getAddress().compareToIgnoreCase(address) == 0) {
                return node.getData();
            }

        Contact leftResult = searchByAddress(node.getLeft(), address);
        if (leftResult != null) {
            return leftResult;
        }

        return searchByAddress(node.getRight(), address);
    }
    
    
    
    public static Contact searchByBirthday(BST<String, Contact> contact, String birth) {
        return searchByBirthday(contact.getRoot(), birth);
    }

    private static Contact searchByBirthday(BSTNode<String, Contact> node, String birth) {
        if (node == null) {
            return null;
        } 
        else 
            if (node.getData().getBirhday().compareToIgnoreCase(birth) == 0) {
                return node.getData();
            }

        Contact leftResult = searchByBirthday(node.getLeft(), birth);
        if (leftResult != null) {
            return leftResult;
        }

        return searchByBirthday(node.getRight(), birth);
    }
    
    
    //////////////////////////////////search by first name///////////////////////////////////////////
    public static void searchContactsByFirstName(BST<String, Contact> contact, String fname) {
        searchContactsByFirstName(contact.getRoot(), fname);
    }

    private static void searchContactsByFirstName(BSTNode<String, Contact> node, String fname) {
        if (node != null) {
        searchContactsByFirstName(node.getLeft(), fname);

        String contactFirstName = node.getData().getName().split(" ")[0]; // Extract first name

        if (fname.compareToIgnoreCase(contactFirstName)==0) {
            System.out.println(node.getData().toString());
        }

        searchContactsByFirstName(node.getRight(), fname);
    }
    }

///////////////////////////////////////////////shared events///////////////////////////////////////////
    public static void sharedEvent (LinkedList<Event> event, String title) {
        if (event.empty())
            System.out.println("No events, try again");
        else{
            
            event.findFirst();
            while (!event.last()){
                if (event.retrieve().getTitle().compareToIgnoreCase(title)==0){
                    System.out.println(event.retrieve().toString()+"\n");
                }
                event.findNext();
            }
            if(event.retrieve().getTitle().compareToIgnoreCase(title)==0)
                    System.out.println(event.retrieve().toString());
            
//            LinkedList<Contact> result=event.retrieve().getContactList();
//            result.printResults();
        }
    }

//    private static void sharedEvent(BSTNode<String, Event> node, String title) {
//        if (node != null) {
//        sharedEvent(node.getLeft(), title);
//
//        if(node.getData().getTitle().compareToIgnoreCase(title)==0); // Extract first name
//            System.out.println(node.getData().getContactList().retrieve().toString());
//
//        sharedEvent(node.getRight(), title);
//    }
//    }
    ///////////////////////////////////////////////////////////////////////////////////////
    public static Contact delete(String phone, BST<String, Contact> contact,LinkedList<Event> event) {
        Contact deleted = null;
        boolean found=false;
        if (contact.empty()){
            System.out.println("Not founded" );
            return deleted;
        }

        
        deleted=searchByPhone(contact,phone);
        if (deleted!=null){
            found=true;
            contact.remove_key(deleted.getName());
        }
        
        if (!event.empty())
            deleteEvent(event,deleted.getName());
          
        return deleted;
    }
    ////////////////////////////////////////////////////
    public static void deleteEvent(LinkedList<Event> eventList, String contactName) {
    if (eventList.empty()) {
        System.out.println("This contact does not have any events");
        return;
    }
    if(!eventList.empty()){
    eventList.findFirst();
    while (!eventList.last()) {
        Event currentEvent = eventList.retrieve();
        LinkedList<Contact> contactList = currentEvent.getContactList();
        if (contactList != null && !contactList.empty()) {
            contactList.findFirst();
            while (!contactList.last()) {
                if (contactList.retrieve().getName().equalsIgnoreCase(contactName)) {
                    contactList.remove(contactList.retrieve());
                    break;
                }
                contactList.findNext();
            }
            if (contactList.last() && contactList.retrieve().getName().equalsIgnoreCase(contactName)) {
                contactList.remove(contactList.retrieve());
            }
            if (contactList.empty()) {
                eventList.remove(currentEvent);
            }
        }
        eventList.findNext();
    }
    }

    // Check the last event in the list separately
    Event lastEvent = eventList.retrieve();
    LinkedList<Contact> lastContactList = lastEvent.getContactList();
    if (lastContactList != null && !lastContactList.empty()) {
        lastContactList.findFirst();
        while (!lastContactList.last()) {
            if (lastContactList.retrieve().getName().equalsIgnoreCase(contactName)) {
                lastContactList.remove(lastContactList.retrieve());
                break;
            }
            lastContactList.findNext();
        }
        if (lastContactList.last() && lastContactList.retrieve().getName().equalsIgnoreCase(contactName)) {
            lastContactList.remove(lastContactList.retrieve());
        }
        if (lastContactList.empty()) {
            eventList.remove(lastEvent);
        }
    }
}
        
           
    }

    


//////////////////////////////////////////////////////////////////////////////////////////




    

