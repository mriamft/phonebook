/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookbstt;

/**
 *
 * @author Maryam
 */
    public class Event implements Comparable <Event>{
   private String title;
   private String date;
   private String time;
   private String location;
   private boolean isEvent;
   private LinkedList <Contact> contactList;
   
   public Event() {//default constructer
        this.title = "";
        this.date = "";
        this.time = "";
        this.location = " ";
        this.isEvent=true;
        contactList = new LinkedList<Contact>();
    }
   
   public Event(String title, String date, String time, String location,boolean isevent, LinkedList contact) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        isEvent=isevent;
        contactList = contact;
    }

    public LinkedList getContactList() {
        return contactList;
    }
public boolean addContact(Contact contact) {
    if (isEvent || (!isEvent && !contact.hasAppointment())) {
        contactList.insert(contact);
        if (isEvent) {
            contact.setHasAppointment(true);
        }
        return true;
    } else {
        System.out.println("Only one appointment is allowed per contact.");
        return false;
    }
} 
      public boolean deleteContact(String contact){
        Contact deleted = null;
        boolean found=false;
        if (contactList.empty()){
            System.out.println("Empty List" );
            return found;
        }

        else{
            
            contactList.findFirst();
            while(!contactList.last()){
                if (contactList.retrieve().getName().compareTo(contact)==0){
                    found=true;
                    deleted=contactList.retrieve();
                    break;
                }
                contactList.findNext();
                
            }
        }
                if (contactList.retrieve().getName().compareTo(contact)==0){
                deleted=contactList.retrieve();
                    contactList.remove(deleted);
                    found=true;
                    
            }
            return found;////////////////////////////////////EVENT!?////////////

    }
      
      public Contact getContact() {
          contactList.findFirst();
          return contactList.retrieve();
    }
      
      public String getContactName() {
          contactList.findFirst();
          return contactList.retrieve().getName();
    }
  
       public String getTitle() {
        return title;
    }

    public String getDate() {
        return date ;
    }

    public String getTime() {
        return time;
    }

    public boolean isIsEvent() {
        return isEvent;
    }
    
//    public LinkedList<Event> searchContact(LinkedList<Event> eventList, String conName){
//        LinkedList<Event> result = new LinkedList<Event>();
//        if (contactList.empty())
//            return null;
//        contactList.findFirst();
//                while(!contactList.last()){
//                    if (contactList.retrieve().compareTo(conName)==0){
//                        result.insert(contactList.retrieve());
//                    }
//                    contactList.findNext();
//                }
//        
//                if (contactList.retrieve().compareTo(conName)==0) 
//                    result.insert(contactList.retrieve());
//                return result;
//    }   
    
//        public LinkedList<String> searchByTitle(LinkedList<Event> eventList, String title){
//        LinkedList<String> result = new LinkedList<String>();
//        if (contactList.empty())
//            return null;
//        contactList.findFirst();
//                while(!contactList.last()){
//                    if (contactList.retrieve().compareTo(conName)==0){
//                        result.insert(contactList.retrieve());
//                    }
//                    contactList.findNext();
//                }
//        
//                if (contactList.retrieve().compareTo(conName)==0) 
//                    result.insert(contactList.retrieve());
//                return result;
//    }

    public void setContactList(LinkedList<Contact> contactList) {
        this.contactList = contactList;
    }
    
       
       

    @Override
    public String toString() {
        String type;
        if(isEvent)
            type="Event";
        else type="Appointment";
        String con="";
        if (contactList!=null)
        if (!contactList.empty()){
                      contactList.findFirst();
            while(!contactList.last()){
        con+=contactList.retrieve().getName()+",";
                      contactList.findNext();
            }
            con+=contactList.retrieve().getName();
}
        return "Event: \n" + "title=" + title + "\n date=" + date + "\n time=" + time + "\n location=" + location+"\n Type:" + type+ "\n Contacts:"+con+"\n";
    }
    
    
    public int compareTo(Event other) { 
        return this.title.compareToIgnoreCase(other.title); 
    }
    
   
}



