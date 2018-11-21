// 21. Merge Two Sorted Lists

// https://leetcode.com/problems/merge-two-sorted-lists/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


// ITERATIVE 

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode t = dummy;
        while (l1!=null && l2!=null){
            if (l1.val < l2.val){
                t.next = l1;
                t = l1;
                l1 = l1.next;
            }else{
                t.next = l2;
                t = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null){
            t.next = l1;
        }else if (l2 != null){
            t.next = l2;
        }
        return dummy.next;
    }
}

// RECURSIVE - FASTER

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //base case
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        //recursive case
        if (l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
        
    }
}

