/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookbstt;

/**
 *
 * @author Maryam
 */

public class BSTNode <K extends Comparable<K>,T> {
    public K key;
    public T data;
    public BSTNode<K,T> left, right;
    
        public BSTNode() {
            left=right=null;
        }
    
    public BSTNode(K k, T val) {
        key = k;
        data = val;
        left = right = null;
    }
    
    public BSTNode(K k, T val, BSTNode<K,T> l, BSTNode<K,T> r) {
        key = k;
        data = val;
        left = l;
        right = r;
    }

    public BSTNode<K, T> getLeft() {
        return left;
    }

    public BSTNode<K, T> getRight() {
        return right;
    }

    public K getKey() {
        return key;
    }

    public T getData() {
        return data;
    }


}


