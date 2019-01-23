
173-BinarySearchTreeIterator

// https://leetcode.com/problems/binary-search-tree-iterator/
/*
   7
  3  15
     9  20

Calling next() will return the next smallest number in the BST.

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
*/

// use stack because want to get the iterator that it most recently inserted 