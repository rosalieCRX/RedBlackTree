//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (BALST)
// Description: (implementing a Red-black tree)
// Course: (001 FALL 2019)
//
// Author: (Rosalie CAI)
// Email: (rcai25@wisc.edu)
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * Class to implement a BalanceSearchTree. Red-Black. Note which tree you implement here and as a
 * comment when you submit.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

  private BSTNode<K, V> root;// the root of the tree

  private int numKeys;// number of the keys in the tree

  public enum COLOR {
    RED, BLACK;
  }

  /**
   * the default constructor
   */
  public BALST() {
  }

  /**
   * Returns the key that is in the root node of this BST. If root is null, returns null.
   * 
   * @return key found at root node, or null
   */
  @Override
  public K getKeyAtRoot() {
    if (root != null) {
      return root.key;
    }
    return null;
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the left child. If the left child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // if key argument is null, throw IllegalNullKeyException
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    BSTNode<K, V> nodeFound = getNode(root, key);// node found
    if (nodeFound.getLeft() == null) {
      return null;
    } // if the node found has no left child

    return nodeFound.getLeft().getKey();
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the right child. If the right child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // if key argument is null, throw IllegalNullKeyException
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    BSTNode<K, V> nodeFound = getNode(root, key);// node found
    if (nodeFound.getRight() == null) {
      return null;
    } // if the node found has no left child

    return nodeFound.getRight().getKey();
  }

  /**
   * get the associated value of a key, private helper method
   * 
   * @param node the root of a subtree to check
   * @param k    the key
   * @return the associated value
   * @throws KeyNotFoundException if key is not found in this BST
   */
  private BSTNode<K, V> getNode(BSTNode<K, V> node, Comparable k) throws KeyNotFoundException {
    if (node == null) {
      throw new KeyNotFoundException();
    } // base case

    if (k.compareTo(node.getKey()) > 0) {
      return getNode(node.getRight(), k);
    } // if key smaller than current node, go left
    if (k.compareTo(node.getKey()) < 0) {
      return getNode(node.getLeft(), k);
    } // if key bigger than current node, go right

    return node;
  }

  /**
   * Returns the height of this BST. H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max( height(root.left),
   * height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key, has a height of one
   * (1). A BST with two keys, has a height of two (2). A BST with three keys, can be balanced with
   * a height of two(2) or it may be linear with a height of three (3) ... and so on for tree with
   * other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  @Override
  public int getHeight() {

    return getHeight(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param node pointer to the current root of a subtree
   * @return height of the subtree rooted at current counting the number of nodes from the current
   *         node to the deepest leaf in the subtree rooted at current node
   */
  private int getHeight(BSTNode<K, V> node) {
    if (node == null) {
      return 0;
    } // base of recursion

    return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
  }

  /**
   * Returns the keys of the data structure in sorted order. In the case of binary search trees, the
   * visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  @Override
  public List<K> getInOrderTraversal() {
    return getInOrderTraversal(root);
  }

  /**
   * private helper method that traverse the tree in order
   * 
   * @param node the root of the subtree to be traversed
   */
  private List<K> getInOrderTraversal(BSTNode<K, V> node) {
    if (node == null) {
      return new ArrayList<>();
    } // base case

    // add the left subtree, itself, and its right subtree
    ArrayList<K> allNodes = (ArrayList<K>) getInOrderTraversal(node.getLeft());
    allNodes.add(node.getKey());
    allNodes.addAll(getInOrderTraversal(node.getRight()));

    return allNodes;
  }

  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getPreOrderTraversal() {
    return getPreOrderTraversal(root);
  }

  /**
   * private helper method that traverse the tree pre order
   * 
   * @param node the root of the subtree to be traversed
   */
  private List<K> getPreOrderTraversal(BSTNode<K, V> node) {
    if (node == null) {
      return new ArrayList<>();
    } // base case

    // add the left subtree, itself, and its right subtree
    ArrayList<K> allNodes = (ArrayList<K>) getPreOrderTraversal(node.getLeft());
    allNodes.add(0, node.getKey());
    allNodes.addAll(getPreOrderTraversal(node.getRight()));

    return allNodes;
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    return getPostOrderTraversal(root);
  }

  /**
   * private helper method that traverse the tree post order
   * 
   * @param node the root of the subtree to be traversed
   */
  private List<K> getPostOrderTraversal(BSTNode<K, V> node) {
    if (node == null) {
      return new ArrayList<>();
    } // base case

    // add the left subtree, itself, and its right subtree
    ArrayList<K> allNodes = (ArrayList<K>) getPostOrderTraversal(node.getLeft());
    allNodes.addAll(getPostOrderTraversal(node.getRight()));
    allNodes.add(node.getKey());

    return allNodes;
  }

  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down, and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    ArrayList<K> allNodes = new ArrayList<>();
    for (int i = 1; i <= getHeight(); i++) {
      allNodes.addAll(getLevelOrderTraversal(root, i));
    }

    return allNodes;
  }

  /**
   * The private helper method to return the keys of the data structure in level-order traversal
   * order.
   * 
   * @return List of Keys in level-order for the subtree rooted at node
   */
  private List<K> getLevelOrderTraversal(BSTNode<K, V> node, int level) {
    ArrayList<K> allNodes = new ArrayList<>();
    if (node == null) {
      return Arrays.asList();
    } // base case

    if (level == 1) {
      allNodes.add(node.getKey());
    } // base case
    else {
      allNodes.addAll(getLevelOrderTraversal(node.getLeft(), level - 1));
      allNodes.addAll(getLevelOrderTraversal(node.getRight(), level - 1));

    }

    return allNodes;
  }

  /**
   * Add the key,value pair to the data structure and increase the number of keys. If key is null,
   * throw IllegalNullKeyException; If key is already in data structure, throw
   * DuplicateKeyException(); Do not increase the num of keys in the structure, if key,value pair is
   * not added.
   * 
   * @param key   key to insert
   * @param value value associated with key
   * @throws IllegalNullKeyException if key is null
   * @throws DuplicateKeyException   if key is already in the tree
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // If key is null throw IllegalNullKeyException
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // insert
    if (root == null) {
      root = new BSTNode<K, V>(key, value);
      root.setColor(COLOR.BLACK);
      numKeys++;
    } else {

      insert(root, new BSTNode<K, V>(key, value));
    }


  }

  /**
   * private helper method for insert
   * 
   * @param node the root of the subtree that is checked for insertion
   * @throws DuplicateKeyException
   */
  private void insert(BSTNode<K, V> node, BSTNode<K, V> newNode) throws DuplicateKeyException {
    // if key smaller than current node, go left
    if (newNode.getKey().compareTo(node.getKey()) < 0) {
      // if left is null, insert
      if (node.getLeft() == null) {
        newNode.setParent(node);
        node.setLeft(newNode);
        numKeys++;
      } else {
        insert(node.getLeft(), newNode);
      }

    } // if key bigger than current node, go right
    else if (newNode.getKey().compareTo(node.getKey()) > 0) {
      // if right is null, insert
      if (node.getRight() == null) {
        newNode.setParent(node);
        node.setRight(newNode);
        fix(newNode);
        numKeys++;
      } else {
        insert(node.getRight(), newNode);
      }
    } else {
      throw new DuplicateKeyException();
    } // if key already in the tree

    // fix tree
    fix(newNode);
    fix(node);

  }

  /**
   * fix balance
   * 
   * @param node the root of the subtree to check
   * @return the fixed subtree
   */
  private void fix(BSTNode<K, V> node) {
    // do not fix if root or the direct children of root
    if (node.getColor() == COLOR.BLACK || node.getParent() == null
        || node.getParent().getParent() == null) {
    }
    // if parent is black
    else if (node.getParent() == null || node.getParent().getColor() == COLOR.BLACK) {
      // leave it there
    }
    // recoloring, if parent and its sibling is red
    else if (node.getParent().getParent().getLeft() != null
        && node.getParent().getParent().getRight() != null
        && node.getParent().getParent().getLeft().getColor() == COLOR.RED
        && node.getParent().getParent().getRight().getColor() == COLOR.RED) {
      node.getParent().getParent().getLeft().setColor(COLOR.BLACK);
      node.getParent().getParent().getRight().setColor(COLOR.BLACK);// recolor parent and its
                                                                    // sibling to black
      if (node.getParent().getParent() != root)
        node.getParent().getParent().setColor(COLOR.RED);// recolor Grandparent if not root
    }

    // Tri node resctrucure
    // --parent is left child
    else if (node.getParent() == node.getParent().getParent().getLeft()) {
      if (node == node.getParent().getRight()) {
        rotateLeft(node = node.getParent());
      }
      node.getParent().setColor(COLOR.BLACK);
      node.getParent().getParent().setColor(COLOR.RED);// recolor Grandparent if not root
      rotateRight(node.getParent().getParent());
    }
    // --parent is right child
    else if (node.getParent() == node.getParent().getParent().getRight()) {
      if (node == node.getParent().getLeft()) {
        rotateRight(node = node.getParent());
      }
      node.getParent().setColor(COLOR.BLACK);
      node.getParent().getParent().setColor(COLOR.RED);// recolor Grandparent if not root
      rotateLeft(node.getParent().getParent());
    }

  }


  /**
   * rotate right
   * 
   * @param parent
   */
  private void rotateRight(BSTNode<K, V> node) {
    BSTNode<K, V> temp = node.getLeft().getRight();
    node.getLeft().setRight(node);
    // setting new pointer to this subtree
    if (node.getParent() == null) {
      root = node.getLeft();
    } else {
      if (node == node.getParent().getLeft()) {
        node.getParent().setLeft(node.getLeft());
      }
      if (node == node.getParent().getRight()) {
        node.getParent().setRight(node.getLeft());
      }
    }

    node.getLeft().setParent(node.getParent());
    node.setParent(node.getLeft());
    node.setLeft(temp);
    if (temp != null)
      node.getLeft().setParent(node);

  }

  /**
   * rotate left
   * 
   * @param node
   */
  private void rotateLeft(BSTNode<K, V> node) {
    BSTNode<K, V> temp = node.getRight().getLeft();
    node.getRight().setLeft(node);
    // setting new pointer to this subtree
    if (node.getParent() == null) {
      root = node.getRight();
    } else {
      if (node == node.getParent().getLeft()) {
        node.getParent().setLeft(node.getRight());
      }
      if (node == node.getParent().getRight()) {
        node.getParent().setRight(node.getRight());
      }
    }

    node.getRight().setParent(node.getParent());
    node.setParent(node.getRight());
    node.setRight(temp);
    if (temp != null)
      node.getRight().setParent(node);

  }

  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys. If
   * key is not found, do not decrease the number of keys in the data structure. If key is null,
   * throw IllegalNullKeyException If key is not found, throw KeyNotFoundException().
   * 
   * 
   * @param key key to remove
   * @return if removed successfully
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if key not found
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // If key is null, throws IllegalArgumentException("null key")
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int size = this.numKeys;
    root = remove(root, key);

    return (size - this.numKeys == 1) && (!contains(key));
  }

  /**
   * private helper method for removing a node
   * 
   * @param node the subtree to check for removal
   * @param k    the key of the value pair/node to be removed
   * @return the updated subtree rooted at node
   */
  private BSTNode<K, V> remove(BSTNode<K, V> node, Comparable k) {
    // base case-key not found
    if (node == null) {
      return null;
    }
    if (k.compareTo(node.getKey()) < 0) {
      node.setLeft(remove(node.getLeft(), k));
    } // if key smaller than current node, go left
    else if (k.compareTo(node.getKey()) > 0) {
      node.setRight(remove(node.getRight(), k));
    } // if key bigger than current node, go right
    else {

      numKeys--;// decrease size
      if (node.getLeft() == null) {
        return node.getRight();
      } // if left child is null, return right child
      else if (node.getRight() == null) {
        return node.getLeft();
      } // if right child is null, return left child
      else {
        BSTNode<K, V> runner = node.getLeft();
        while (runner.getRight() != null) {
          runner = runner.getRight();
        } // get the in-order predecessor

        // replace value and key
        node.setKey(runner.getKey());
        node.setValue(runner.getValue());

        // delete node
        node.setLeft(remove(node.getLeft(), runner.getKey()));
        numKeys++;

      } // it has two child, get the in-order predecessor to replace the current node

    } // if value found


    return node;
  }// if key is found, delete and return true

  /**
   * Returns the value associated with the specified key
   *
   * Does not remove key or decrease number of keys. If key is null, throw IllegalNullKeyException
   * If key is not found, throw KeyNotFoundException().
   * 
   * @param key key to get
   * @return the value assicated with the key
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    return get(root, key);
  }

  /**
   * get the associated value of a key
   * 
   * @param node the root of a subtree to check
   * @param k    the key
   * @return the associated value
   * @throws KeyNotFoundException
   */
  private V get(BSTNode<K, V> node, K k) throws KeyNotFoundException {
    // base case-find the leaf to insert
    if (node == null) {
      throw new KeyNotFoundException();
    }
    if (k.compareTo(node.getKey()) < 0) {
      return get(node.getLeft(), k);
    } // if key smaller than current node, go left
    else if (k.compareTo(node.getKey()) > 0) {
      return get(node.getRight(), k);
    } // if key bigger than current node, go right

    return node.getValue();// if found
  }

  /**
   * Returns true if the key is in the data structure If key is null, throw IllegalNullKeyException
   * Returns false if key is not null and is not present
   * 
   * @param key the key to search
   * @return whether the key is in the tree
   * @throws IllegalNullKeyException if key is null
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    return contains(root, key);
  }

  /**
   * check if the BST contains the key
   * 
   * @param node the root of the subtree to check
   * @param k    key
   * @return whether the key is contained in the tree
   */
  private boolean contains(BSTNode<K, V> node, Comparable k) {
    // base case-find the leaf to insert
    if (node == null) {
      return false;
    }
    if (k.compareTo(node.getKey()) < 0) {
      return contains(node.getLeft(), k);
    } // if key smaller than current node, go left
    else if (k.compareTo(node.getKey()) > 0) {
      return contains(node.getRight(), k);
    } // if key bigger than current node, go right

    return true;// if found
  }

  /**
   * Returns the number of key,value pairs in the data structure
   * 
   * @return the # of keys in the subtree
   */
  @Override
  public int numKeys() {

    return numKeys(root);
  }

  /**
   * Returns the number of elements in the subtree
   * 
   * @param node the root of the subtree to check
   * @return the size of the subtree
   */
  private int numKeys(BSTNode<K, V> node) {
    if (node == null) {
      return 0;
    }
    return numKeys(node.getLeft()) + numKeys(node.getRight()) + 1;
  }

  /**
   * Print the tree.
   *
   * For our testing purposes: all keys that we insert in the tree will have a string length of
   * exactly 2 characters. example: numbers 10-99, or strings aa - zz, or AA to ZZ
   *
   * This makes it easier for you to not worry about spacing issues.
   *
   * You can display in any of a variety of ways, but we should see a tree that we can identify left
   * and right children of each node
   *
   * For example:
   * 
   * | |-------50 |-------40 | |-------35 30 |-------20 | |-------10
   * 
   * Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
   * 
   * Or, you can display a tree of this kind.
   * 
   * 30 /\ / \ 20 40 / /\ / / \ 10 35 50
   * 
   * Or, you can come up with your own orientation pattern, like this.
   * 
   * 10 20 30 35 40 50
   * 
   * The connecting lines are not required if we can interpret your tree.
   * 
   */
  @Override
  public void print() {
    int treeH = getHeight(root);

    if (treeH <= 0) {
      return;
    } // if empty, return

    String[][] levels = new String[treeH][(int) (Math.pow(2, treeH)) - 1];
    for (String[] arr : levels) {
      Arrays.fill(arr, "  ");
    } // initialize the array

    // store the tree in a tree-like way
    print(levels, root, 0, 0, levels[0].length);

    // print the tree
    for (String[] arr : levels) {
      for (String s : arr) {
        System.out.print(s);
      }
      System.out.println();
    }
  }

  /**
   * the private helper method to fill the array for printing
   * 
   * @param levels the array for storing items to print
   * @param node   the root of this subtree
   * @param row    row of the this item that should occupy
   * @param left   left boundary of this item that should occupy in the array
   * @param right  right boundary of this item that should occupy in the array
   */
  private void print(String[][] levels, BSTNode<K, V> node, int row, int left, int right) {
    if (node == null) {
      return;
    } // leaf, base case

    levels[row][(left + right) / 2] = "" + node.getKey();// print in the middle
    print(levels, node.getLeft(), row + 1, left, (left + right) / 2);// print left childvin the
                                                                     // middle of
    // the left half
    print(levels, node.getRight(), row + 1, (left + right + 1) / 2, right);// print right child in
                                                                           // the middle of
    // the right half
  }

  /**
   * Tree node
   * 
   * @author rosaliecarrow
   *
   * @param <K> type of the key that can be searched for in the tree
   * @param <V> type of the value to be stored
   */
  class BSTNode<K, V> {

    private K key;// key that can be searched
    private V value;// value associated
    private BSTNode<K, V> left;// pointer left child
    private BSTNode<K, V> right;// pointer right child
    private BSTNode<K, V> parent;// pointer parent

    private COLOR color = COLOR.RED;// default color is red



    /**
     * @param key        the key that can be searched for in the tree
     * @param value      the value to be stored
     * @param leftChild  point to its left child
     * @param rightChild point to its right child
     */
    BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
      this.key = key;
      this.value = value;
      this.left = leftChild;
      this.right = rightChild;
    }

    BSTNode(K key, V value) {
      this(key, value, null, null);
    }


    /**
     * Key getter
     * 
     * @return the key
     */
    public K getKey() {
      return key;
    }

    /**
     * key setter
     * 
     * @param key the key to set
     */
    public void setKey(K key) {
      this.key = key;
    }

    /**
     * value getter
     * 
     * @return the value
     */
    public V getValue() {
      return value;
    }

    /**
     * value setter
     * 
     * @param value the value to set
     */
    public void setValue(V value) {
      this.value = value;
    }

    /**
     * left child getter
     * 
     * @return the left
     */
    public BSTNode<K, V> getLeft() {
      return left;
    }

    /**
     * left child setter
     * 
     * @param left the left to set
     */
    public void setLeft(BSTNode<K, V> left) {
      this.left = left;
    }

    /**
     * right child getter
     * 
     * @return the right
     */
    public BSTNode<K, V> getRight() {
      return right;
    }

    /**
     * right child getter
     * 
     * @param right the right to set
     */
    public void setRight(BSTNode<K, V> right) {
      this.right = right;
    }


    /**
     * @return the color
     */
    public COLOR getColor() {
      return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(COLOR color) {
      this.color = color;
    }

    /**
     * @return the parent
     */
    public BSTNode<K, V> getParent() {
      return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(BSTNode<K, V> parent) {
      this.parent = parent;
    }

  }


}
