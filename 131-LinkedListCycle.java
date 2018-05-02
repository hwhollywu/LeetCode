// 141. Linked List Cycle
// https://leetcode.com/problems/linked-list-cycle/description/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // base case
        if (head == null) return false;
        // two pointers, l1++, l2 += 2, see if they can meet (only in a circle)
        ListNode l1 = head, l2 = head.next;
        while (l1 != null && l2 != null){
            if (l1 == l2) return true;
            l1 = l1.next;
            if (l2.next == null) break; // check l2.next
            l2 = l2.next.next; 
        }
        return false;
    }
}