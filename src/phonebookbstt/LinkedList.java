/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookbstt;

/**
 *
 * @author Maryam
 */



public class LinkedList<T extends Comparable > {
    private Node<T> head;
    private Node<T> current;
    private int size;

    public LinkedList() {
        head=current=null;
    }
    
    public boolean empty() {
        return head == null;
    }
    
    public boolean last() {
        return current.next == null;
    }

    public boolean full() {
        return false; }
    
    public void findFirst() {
        current = head;
    }
    
    public void findNext() {
        current = current.next;
    }
    
    public T retrieve() {
        return current.data; 
    }
    
    public int size() {
        return size;
    }
    
    public void update(T val) {
        current.data = val;
    }
    
    public boolean insert ( T obj ) {
        Node<T> temp = new Node<T>(obj);
        
        
        if (empty ()) //empty list
            head = current = temp ;
            
        
        else //temp is less than head => temp should come before head
        if ((head.data).compareTo(temp.data) > 0){
            temp.next = head ; 
            head = temp ;
        }
        
        else{ //temp is in less than current => temp should come before current and after prev 
            current=head;
            Node<T> prev=current;
            while (current!= null){
                if (temp.data.compareTo(current.data) < 0){
                    temp.next = current ;
                    prev.next = temp ;
                    current = temp ;
                }
                else{
                    prev= current;
                    current=current.next;
                }
            }
            current=prev.next=temp; //last element 
        }
        return true;
    }
     

    public void remove (T obj) {
        if (head==null)
            return;
        
        if (current == head) {
            head = head.next;
            current= head;
            return;

        }
        else {
            Node<T> tmp = head;
            while (tmp.next != current)
                tmp = tmp.next;
            tmp.next = current.next;
        }
        if (current.next == null)
            current = head;
        else
            current = current.next;

    }
    
    
    public boolean search (T val)
    {
        if (head == null)
            return false;
        
        Node<T> node  = head;
        while ((node != null) && (node.getData().compareTo(val) != 0))
            node = node.getNext();
        if ((node != null) && (node.getData().compareTo(val) == 0))
        {
            current = node;
            return true;
        }
        return false;
    }
    
public void printResults() {

        if (this.empty()) { // check if it's not empty
            System.out.println("there is nothing to  print ");
            return;
        }
        current = head;
        while (current != null) { // loop on all element
            System.out.println(current.data.toString()); // print element's data
            current = current.next;
        }

    }// end method

}
    


