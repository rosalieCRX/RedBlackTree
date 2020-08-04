//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (BALST)
// Description: (implementing a Red-black tree)
// Course: (001 FALL 2019)
//
// Author: (Rosalie CAI)
// Email: (rcai25@wisc.edu)
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test the tree is balanced or not

// @SuppressWarnings("rawtypes")
/**
 * Test class for the balanced BST (R-B tree)
 * 
 * @author rosaliecarrow
 *
 */
public class BALSTTest {

  BALST<String, String> balst1;// test tree declaration 1
  BALST<Integer, String> balst2;// test tree declaration 2

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    balst1 = createInstance();
    balst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    balst1 = null;
    balst2 = null;
  }

  /**
   * test tree instantiation 1
   * 
   * @return a BALST
   */
  protected BALST<String, String> createInstance() {
    return new BALST<String, String>();
  }

  /**
   * test tree instantiation 2
   * 
   * @return a BALST
   */
  protected BALST<Integer, String> createInstance2() {
    return new BALST<Integer, String>();
  }

  // ----------------------general test cases----------------------------------------
  // --------------------------------------------------------------------------------



  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testBALST_002_insert_reversed_sorted_order_simple() {

    try {
      balst2.insert(30, "30");
      if (!balst2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");
      balst2.insert(20, "20");
      if (!balst2.getKeyOfLeftChildOf(30).equals(20))
        fail("avl insert to Left child of root does not work");
      balst2.insert(10, "10");

      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

      balst2.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 002: " + e.getMessage());
    }
  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_003_insert_smallest_largest_middle_order_simple() {


    try {
      balst2.insert(10, "10");

      if (!balst2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");
      balst2.insert(30, "30");

      if (!balst2.getKeyOfRightChildOf(10).equals(30))
        fail("avl insert to Left child of root does not work");
      balst2.insert(20, "20");

      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

      balst2.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 003: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_004_insert_largest_smallest_middle_order_simple() {

    try {
      balst2.insert(30, "30");

      if (!balst2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      balst2.insert(10, "10");

      if (!balst2.getKeyOfLeftChildOf(30).equals(10))
        fail("avl insert to Left child of root does not work");
      balst2.insert(20, "20");

      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

      balst2.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 004: " + e.getMessage());
    }

  }

  /**
   * test if adding many items is successful and correctly
   */
  @Test
  void testBALST_016_insert_contains_delete_bunch() {
    try {
      // insert first half
      for (int i = 49; i < 77; i++) {

        balst2.insert(i, "" + i);

      }

      // insert second half
      for (int i = 10; i < 49; i++) {
        balst2.insert(i, "" + i);
      }

      // check size
      if (balst2.numKeys() != 67)
        fail("data structure should have size=67, but size=" + balst2.numKeys());

      // check contains
      for (int i = 10; i < 77; i++) {
        if (!balst2.contains(i)) {
          fail("data structure should have " + i + " but doesn't");
        }
      }

      // check remove
      for (int i = 10; i < 77; i++) {
        if (!balst2.remove(i)) {
          fail("data structure should remove " + i + " but doesn't");
        }
      }

      // check contains
      for (int i = 10; i < 77; i++) {
        if (balst2.contains(i)) {
          fail("data structure should not have " + i + " but does");
        }
      }

    } catch (Exception e) {
      fail("data structure should not throw anything, but throws" + e.getMessage());
    }
  }


  /**
   * test if adding many items is successful and correctly
   */
  @Test
  void testBALST_021_insert_contains_delete_bunch() {
    try {
      // insert first half
      for (int i = 1; i < 11; i++) {

        balst2.insert(i, "" + i);
        balst2.insert(-i, "" + (-i));

        balst2.print();

      }

      // check size
      if (balst2.numKeys() != 20)
        fail("data structure should have size=20, but size=" + balst2.numKeys());

      // check contains
      for (int i = 1; i < 11; i++) {
        if (!balst2.contains(i) || !balst2.contains(-i)) {
          fail("data structure should have " + i + " but doesn't");
        }
      }

      for (int i = 1; i < 11; i++) {

        balst2.remove(i);
        balst2.remove(-i);

      }


    } catch (Exception e) {
      fail("data structure should not throw anything, but throws" + e.getMessage());
    }
  }


  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_022_random_insert() {
    try {
      balst2.insert(63, "1");
      balst2.insert(13, "1");
      balst2.insert(62, "1");
      balst2.insert(96, "1");
      balst2.insert(22, "1");
      balst2.insert(26, "1");
      balst2.insert(60, "1");
      balst2.insert(5, "1");

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(62));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(62), new Integer(22));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(62), new Integer(63));

      Assert.assertEquals(balst2.getKeyOfLeftChildOf(22), new Integer(13));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(22), new Integer(26));


      Assert.assertEquals(balst2.getKeyOfLeftChildOf(13), new Integer(5));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(13), null);


      Assert.assertEquals(balst2.getKeyOfLeftChildOf(5), null);
      Assert.assertEquals(balst2.getKeyOfRightChildOf(5), null);


      Assert.assertEquals(balst2.getKeyOfLeftChildOf(26), null);
      Assert.assertEquals(balst2.getKeyOfRightChildOf(26), new Integer(60));


      Assert.assertEquals(balst2.getKeyOfLeftChildOf(63), null);
      Assert.assertEquals(balst2.getKeyOfRightChildOf(63), new Integer(96));


      Assert.assertEquals(balst2.getKeyOfLeftChildOf(96), null);
      Assert.assertEquals(balst2.getKeyOfRightChildOf(96), null);


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 022: " + e.getMessage());
    }

  }


  // TODO: Add your own tests

  // Add tests to make sure that rebalancing occurs even if the
  // tree is larger. Does it maintain it's balance?
  // Does the height of the tree reflect it's actual height
  // Use the traversal orders to check.

  // Can you insert many and still "get" them back out?

  // Does delete work? Does the tree maintain balance when a key is deleted?


  // ----------------------test get key at root--------------------------------------
  // --------------------------------------------------------------------------------

  /**
   * test if empty data structure has size 0
   */
  @Test
  void testBALST_005_empty_ds_size() {
    if (balst1.numKeys() != 0)
      fail("data structure should be empty, with size=0, but size=" + balst1.numKeys());
  }

  /**
   * test if key at the root of an empty data structure is null
   */
  @Test
  void testBALST_006_null_key_at_root_empty_tree() {
    if (balst1.getKeyAtRoot() != null)
      fail("key at the root should be null, but really is" + balst1.getKeyAtRoot());
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testBALST_001_insert_sorted_order_simple() {
    try {
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      balst2.insert(30, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

      balst2.print();

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 001: " + e.getMessage());
    }
  }


  // ----------------------test getKeyOfLeftChildOf and getKeyOfRightChildOf and get-
  // --------------------------------------------------------------------------------

  /**
   * test if get a key throws exception
   */
  @Test
  void testBALST_007_get_key_left_null_exception() {
    // test getKeyOfLeftChildOf
    try {
      balst2.insert(10, "10");
      balst2.getKeyOfLeftChildOf(null);
      fail("getKeyOfLeftChildOf a null key does not throw the right exception");
    } catch (IllegalNullKeyException e) {
      // expected
    } catch (Exception e) {
      // unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 007: " + e.getMessage());
    }

    // test getKeyOfRightChildOf
    try {
      balst2.getKeyOfRightChildOf(null);
      fail("getKeyOfRightChildOf a null key does not throw the right exception");
    } catch (IllegalNullKeyException e) {
      // expected
    } catch (Exception e) {
      // unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 007: " + e.getMessage());
    }

    // test get
    try {
      balst2.get(null);
      fail("get a null key does not throw the right exception");
    } catch (IllegalNullKeyException e) {
      // expected
    } catch (Exception e) {
      // unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 007: " + e.getMessage());
    }
  }

  /**
   * test if get a key throws exception
   */
  @Test
  void testBALST_008_KeyNotFoundException() {
    // test getKeyOfLeftChildOf
    try {
      balst2.insert(10, "10");
      balst2.insert(1, "1");
      balst2.insert(11, "11");
      balst2.getKeyOfLeftChildOf(13);
      fail("getKeyOfLeftChildOf a not-found key does not throw the right exception");
    } catch (KeyNotFoundException e) {
      // expected
    } catch (Exception e) {
      // unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 008: " + e.getMessage());
    }
    // test getKeyOfRightChildOf
    try {
      balst2.getKeyOfRightChildOf(13);
      fail("getKeyOfRightChildOf a not-found key does not throw the right exception");
    } catch (KeyNotFoundException e) {
      // expected
    } catch (Exception e) {
      // unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 008: " + e.getMessage());
    }
    // test get
    try {
      balst2.get(13);
      fail("get a not-found key does not throw the right exception");
    } catch (KeyNotFoundException e) {
      // expected
    } catch (Exception e) {
      // unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 008: " + e.getMessage());
    }
  }

  /**
   * test if get a key works correctly
   */
  @Test
  void testBALST_009_get_key_correctly() {
    try {
      balst2.insert(10, "10");
      balst2.insert(1, "1");
      balst2.insert(11, "11");

      // test getKeyOfLeftChildOf
      if (balst2.getKeyOfLeftChildOf(10).compareTo(1) != 0) {
        fail("getKeyOfLeftChildOf a key does not work, instead has "
            + balst2.getKeyOfLeftChildOf(10));
      } // test get a left child's key
      if (balst2.getKeyOfLeftChildOf(1) != null) {
        fail("getKeyOfLeftChildOf a key does not work");
      } // test get a null left child's key


      // test getKeyOfRightChildOf
      if (balst2.getKeyOfRightChildOf(10).compareTo(11) != 0) {
        fail("getKeyOfRightChildOf a key does not work, instead has "
            + balst2.getKeyOfLeftChildOf(10));
      } // test get a left child's key
      if (balst2.getKeyOfRightChildOf(11) != null) {
        fail("getKeyOfRightChildOf a key does not work");
      } // test get a null left child's key

      // test get
      if (balst2.get(10).compareTo("10") != 0) {
        fail("get a key does not work");
      } // test get a left child's key
      if (balst2.get(1).compareTo("1") != 0) {
        fail("get a key does not work");
      } // test get a left child's key
      if (balst2.get(11).compareTo("11") != 0) {
        fail("get a key does not work");
      } // test get a left child's key

    } catch (Exception e) {
      // unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 009: " + e.getMessage());
    }
  }

  // ----------------------test get height-------------------------------------------
  // --------------------------------------------------------------------------------

  /**
   * test if get height works for empty tree
   */
  @Test
  void testBALST_010_get_height_empty_tree() {
    if (balst1.getHeight() != 0) {
      fail("data structure should have height 0, but height=" + balst1.getHeight());
    }
  }

  /**
   * test if get height works for empty tree
   */
  @Test
  void testBALST_011_get_height_some_insertion() {
    try {
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(30, "30");
      if (balst2.getHeight() != 2) {
        fail("data structure should have height 2, but height=" + balst2.getHeight());
      }
    } catch (Exception e) {// unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 011: " + e.getMessage());
    }
  }

  // ----------------------test traversal--------------------------------------------
  // --------------------------------------------------------------------------------

  /**
   * test if OrderTraversal works
   */
  @Test
  void testBALST_012_getOrderTraversal() {
    try {
      if (!(balst2.getInOrderTraversal().isEmpty() && balst2.getPreOrderTraversal().isEmpty()
          && balst2.getPostOrderTraversal().isEmpty())) {
        fail("data structure's order traversal is not empty when should be");
      }
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(30, "30");
      balst2.insert(40, "40");

      // test getInOrderTraversal
      for (int i = 0; i < 4; i++) {
        if (balst2.getInOrderTraversal().get(i).compareTo((i + 1) * 10) != 0) {
          fail("data structure's in order traversal fails at " + ((i + 1) * 10));
        }
      }

      // test getPreOrderTraversal
      if (balst2.getPreOrderTraversal().get(0).compareTo(20) != 0) {
        fail("data structure's pre order traversal fails at 20");
      }
      if (balst2.getPreOrderTraversal().get(1).compareTo(10) != 0) {
        fail("data structure's pre order traversal fails at 10");
      }
      if (balst2.getPreOrderTraversal().get(2).compareTo(30) != 0) {
        fail("data structure's pre order traversal fails at 30");
      }
      if (balst2.getPreOrderTraversal().get(3).compareTo(40) != 0) {
        fail("data structure's pre order traversal fails at 40");
      }

      // test getPostOrderTraversal
      if (balst2.getPostOrderTraversal().get(0).compareTo(10) != 0) {
        fail("data structure's post order traversal fails at 10");
      }
      if (balst2.getPostOrderTraversal().get(1).compareTo(40) != 0) {
        fail("data structure's post order traversal fails at 40");
      }
      if (balst2.getPostOrderTraversal().get(2).compareTo(30) != 0) {
        fail("data structure's post order traversal fails at 30");
      }
      if (balst2.getPostOrderTraversal().get(3).compareTo(20) != 0) {
        fail("data structure's post order traversal fails at 20");
      }

      // test getLevelOrderTraversal
      if (balst2.getLevelOrderTraversal().get(0).compareTo(20) != 0) {
        fail("data structure's Level order traversal fails at 20");
      }
      if (balst2.getLevelOrderTraversal().get(1).compareTo(10) != 0) {
        fail("data structure's Level order traversal fails at 10");
      }
      if (balst2.getLevelOrderTraversal().get(2).compareTo(30) != 0) {
        fail("data structure's Level order traversal fails at 30");
      }
      if (balst2.getLevelOrderTraversal().get(3).compareTo(40) != 0) {
        fail("data structure's Level order traversal fails at 40");
      }

    } catch (Exception e) {// unexpected
      e.printStackTrace();
      fail("Unexpected exception AVL 012: " + e.getMessage());
    }
  }

  // ----------------------test insert-----------------------------------------------
  // --------------------------------------------------------------------------------
  /**
   * test if exception is thrown after insertion of null key
   */
  @Test
  void testBALST_013_insert_null_key_exception_is_thrown() {
    try {
      balst2.insert(null, "10");
      fail("data structure should throw IllegalNullKeyException.");// shouldn't reach
                                                                   // here
    } catch (IllegalNullKeyException e) {
      // expected
    } catch (Exception e) {
      fail("data structure should throw IllegalNullKeyException.");
      e.printStackTrace();
    }

    // check size
    if (balst2.numKeys() != 0) {
      fail("size should be zero");
    }

  }


  /**
   * test if the right exception is thrown for duplicate insertion
   * 
   */
  @Test
  void testBALST_014_duplicate_exception_is_thrown() {
    try {
      balst2.insert(10, "10");
      balst2.insert(10, "10");
      fail("data structure should throw DuplicateKeyException.");// shouldn't reach
                                                                 // here
    } catch (DuplicateKeyException e) {
      // expected
    } catch (Exception e) {
      fail("data structure should throw DuplicateKeyException.");
      e.printStackTrace();
    }

    // check size
    if (balst2.numKeys() != 1) {
      fail("size should be 1");
    }
  }

  /**
   * test if value null is correctly inserted
   */
  @Test
  void testBALST_015_insert_null_value_is_stored() {
    // test if value null is inserted successfully
    try {
      balst2.insert(1, null);
      // test if value null is stored
      if (balst2.get(1) != (null)) {
        fail("data structure should contain value null");
      }
      // test if value null is stored
      if (balst2.numKeys() != 1) {
        fail("data structure should contain value null");
      }
    } catch (Exception e) {
      fail("data structure should not throw anything, but throws" + e.getMessage());
    }
  }


  // ----------------------test remove-----------------------------------------------
  // --------------------------------------------------------------------------------

  /**
   * test if the right exception is thrown for null key removal
   */
  @Test
  void testBALST_017_remove_null_key_exception_is_thrown() {
    try {
      balst2.remove(null);
      fail("data structure should throw IllegalArgumentException(\"null key\") here");// shouldn't
                                                                                      // reach here
    } catch (IllegalNullKeyException e) {
      // expected
    } catch (Exception e) {
      fail("data structure should throw IllegalNullKeyException.");
      e.printStackTrace();
    }
  }

  /**
   * test if false is returned for non-existent key
   */
  @Test
  void testBALST_018_remove_returns_false_when_key_not_present() {
    try {
      balst2.insert(10, "10");
      balst2.insert(11, "11");
      if (balst2.remove(77)) {
        fail("data structure should fail to remove key, but remove is true");
      }
    } catch (KeyNotFoundException e) {
      // expected
    } catch (Exception e) {
      fail("data structure should throw KeyNotFoundException.");
      e.printStackTrace();
    }

  }

  /**
   * test if false is returned for non-existent key
   */
  @Test
  void testBALST_019_remove_returns_true_when_key_present() {
    try {
      balst2.insert(10, "10");
      balst2.insert(11, "11");
      if (!balst2.remove(11)) {
        fail("data structure should fail to remove key, but remove is false");
      }
    } catch (KeyNotFoundException e) {
      // expected
    } catch (Exception e) {
      fail("data structure should throw KeyNotFoundException.");
      e.printStackTrace();
    }

  }

  // ----------------------test contains---------------------------------------------
  // --------------------------------------------------------------------------------

  /**
   * test if the right exception is thrown for null key contains
   */
  @Test
  void testBALST_020_contain_null_key_exception_is_thrown() {
    try {
      balst2.insert(10, "10");
      balst2.insert(11, "11");
      balst2.contains(null);

      fail("data structure should fail to get right key");

    } catch (IllegalNullKeyException e) {
      // expected
    } catch (Exception e) {
      fail("data structure should throw IllegalNullKeyException .");
      e.printStackTrace();
    }
  }



}
