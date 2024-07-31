/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookbstt;

/**
 *
 * @author Maryam
 */
    public class BST <K extends Comparable<K>, T>{
    BSTNode<K,T> root, current;
/** Creates a new instance of BST */
    
    public BST() {
    root = current = null;
    }
    
    public boolean empty() {
    return root == null;
    }
    
    public boolean full() {
    return false;
    }
    
    public T retrieve() {
    return current.data;
    }
    
    public boolean findkey(K tkey) {
        BSTNode<K,T> p = root, q = root;
    
        if(empty())
        return false;
        
        while(p != null) {
            q = p;
            if(p.key.compareTo(tkey)==0) {
                current = p;
                return true;
            }
            else if(tkey.compareTo(p.key) < 0)
                p = p.left;
            else
                p = p.right;
        }
        current = q; //parent of node
        return false;
        
    }
    
    public boolean insert(K k, T val) {
        BSTNode<K,T> p, q = current;
        if(findkey(k)) {
            current = q; // findkey() modified current
            return false; // key already in the BST
        }
        p = new BSTNode<K,T>(k, val);
        if (empty()) {
            root = current = p;
            return true;
        }
        else {
// current is pointing to parent of the new key
            if (k.compareTo(current.key) < 0)
                current.left = p;
            else
                current.right = p;
            current = p;
            return true;
        }
    }
    
    public boolean remove_key (K tkey){
        boolean removed = false;
        BSTNode<K,T> p;
        p = remove_aux(tkey, root, removed);
        current = root = p;
        return removed;
    }

    private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, Boolean flag ){ //ensure {
        BSTNode<K,T> q, child = null;
        if(p == null)
            return null;
        if(key.compareTo(p.key) < 0)
            p.left = remove_aux(key, p.left, flag); //go left
        else if(key.compareTo(p.key) > 0)
            p.right = remove_aux(key, p.right, flag); //go right
        else { // key is found
            flag=true;
        if (p.left != null && p.right != null){ //two children
            q = find_min(p.right);
            p.key = q.key;
            p.data = q.data;
            p.right = remove_aux(q.key, p.right, flag);
        }
            else {
                if (p.right == null) //one child
                    child = p.left;
                else if (p.left == null) //one child
                    child = p.right;
                return child;
            }
        }
        return p;

    }

     
    private BSTNode<K,T> find_min(BSTNode<K,T> p){
        if(p == null)
            return null;
        while(p.left != null){
            p = p.left;
        }
        return p;
    }

    @Override
    public String toString() {
        String str="";
        if(root==null)
            return str;
        str=inorderTraversal(root,str);
        return str;           
    }
    

 private String inorderTraversal(BSTNode<K, T> p, String str) {
    if (p == null) {
        return str;
    }

    str = inorderTraversal(p.getLeft(), str);
    str += p.getData().toString() + ", ";
    str = inorderTraversal(p.getRight(), str);

    return str;
}
    public boolean update(K key, T data){
        remove_key(current.key);
        return insert(key, data);
    }

    public BSTNode<K, T> getRoot() {
        return root;
    }
    
    
}
    


