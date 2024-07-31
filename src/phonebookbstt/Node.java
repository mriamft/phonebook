/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookbstt;

/**
 *
 * @author Maryam
 */
    public class Node<T> {
    public Node<T> next;
    public T data;

    public Node() {
        next = null;
        data = null;
    }
    
    public Node(T val) {
        data = val;
        next= null;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T val) {
        data = val;
    }  
    
    
    



}
