382 - Linked List Random Node 
// https://leetcode.com/problems/linked-list-random-node/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    ListNode head;
    Random rand;
    int count;
    List<Integer> list;

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        rand = new Random();
        count = 0; // count the number of elements in list
        list = new ArrayList<Integer>();
        while(head != null){
            count ++;
            list.add(head.val);
            head = head.next;
        }
        
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int index = rand.nextInt(count);
        return list.get(index);
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */


public ListNode deepclone(ListNode head){
    ListNode node = head;
    ListNode newhead = new ListNode(0);
    ListNode node2 = newhead;
    while(node != null){
        ListNode newNode = new Node(node.val);
        node2.next = newNode;
        node2 = node2.next;
    }
    return newhead.next;
}