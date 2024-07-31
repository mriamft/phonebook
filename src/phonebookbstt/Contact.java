/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookbstt;

/**
 *
 * @author Maryam
 */
    public class Contact implements Comparable<Contact> {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String birhday;
    private String notes;
    private boolean hasAppointment;
    private LinkedList<Event> eventList;

    public Contact() {
        this.name = "";
        this.phone = "";
        this.email = "";
        this.address = "";
        this.birhday = "";
        this.notes = "";
        eventList = new LinkedList<Event>();
    }

    public Contact(String name, String phone, String email, String address, String birhday, String notes) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birhday = birhday;
        this.notes = notes;
        eventList = new LinkedList<Event>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getBirhday() {
        return birhday;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNotes() {
        return notes;
    }
    
    public void setHasAppointment(boolean hasAppointment) {
    this.hasAppointment = hasAppointment;
}

    
public boolean addEvent(Event event) {
    boolean isAppointment = !event.isIsEvent();

    if (isAppointment && hasAppointment()) {
        System.out.println("Only one appointment is allowed per contact.");
        return false;
    }

    eventList.insert(event);
    return true;
}
public boolean hasAppointment() {
    if(!eventList.empty()){
    eventList.findFirst();
    while (!eventList.last()) {
        if (!eventList.retrieve().isIsEvent()) {
            return true; // Appointment found
        }
        eventList.findNext();
    }
    return (!eventList.retrieve().isIsEvent()); // Check last event
    }
    return false;
}



    public boolean deleteEvent(String title) {
        Event deleted = null;
        boolean found = false;
        if (eventList.empty()) {
            System.out.println("Empty List");
            return found;
        } else {
            eventList.findFirst();
            while (!eventList.last()) {
                if (eventList.retrieve().getTitle().compareTo(title) == 0) {
                    found = true;
                    deleted = eventList.retrieve();
                    break;
                }
                eventList.findNext();
            }
        }
        if (eventList.retrieve().getTitle().compareTo(title) == 0) {
            deleted = eventList.retrieve();
            eventList.remove(deleted);
            found = true;
        }
        return found;
    }

    public boolean hasConflict(String newTime, String newDate) {
        if (eventList.empty())
            return false; // No conflict

        eventList.findFirst();
        while (!eventList.last()) {
            Event event = eventList.retrieve();
            if (event.getTime().equals(newTime) && event.getDate().equals(newDate))
                return true; // Conflict detected
            eventList.findNext();
        }

        Event lastEvent = eventList.retrieve();
        if (lastEvent.getTime().equals(newTime) && lastEvent.getDate().equals(newDate))
            return true; // Conflict detected at last event

        return false; // No conflict
    }



    public LinkedList<Event> getEventList() {
        return eventList;
    }

    public int compareTo(Contact other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
public String toString() {
    String result = "Contact:\n";
    result += "name=" + name + "\n";
    result += "phone=" + phone + "\n";
    result += "email=" + email + "\n";
    result += "address=" + address + "\n";
    result += "birthday=" + birhday + "\n";
    result += "notes=" + notes + "\n";

    LinkedList<Event> even = getEventList();
    if (!even.empty()) {
        result += "Events:\n";
        even.findFirst();
        while (!even.last()) {
            Event event = even.retrieve();
            if (event != null) {
                result += event.toString() + "\n";
            }
            even.findNext();
        }
        Event lastEvent = even.retrieve();
        if (lastEvent != null) {
            result += lastEvent.toString();
        }
    }

    return result;
}
}
