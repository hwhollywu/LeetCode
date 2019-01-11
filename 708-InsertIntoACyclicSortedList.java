708-InsertIntoACyclicSortedList

// https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/



/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        // base case, new cyclic list
        if (head == null){
            Node cur = new Node(insertVal);
            cur.next = cur;
            return cur;
        }
        // iterative case
        Node prev = head; // prev node of new node
        Node cur = head.next;
        while(cur != head){
            int preval = prev.val;
            int nextval = cur.val;
            // cases : find equal / right slot, insert largest, insert smallest
            if (insertVal == preval || preval < insertVal && insertVal <= nextval || (nextval < preval && preval < insertVal )||(insertVal < nextval && nextval < preval)){
                // find location
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        Node insert = new Node(insertVal);
        prev.next = insert;
        insert.next = cur;
        return head;
    }
}