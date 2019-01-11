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


// ITERATIVE  Time & Space: O(n + m)

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode t = dummy;
        while (l1!=null && l2!=null){
            if (l1.val <= l2.val){
                t.next = l1;
                l1 = l1.next;
            }else{
                t.next = l2;
                l2 = l2.next;
            }
            t = t.next; 
        }
        t.next = (l1 == null) ? l2 : l1;
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


// Brute Force

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<Integer> arr = new ArrayList();
        // add each node into arr
        while(l1 != null){
            arr.add(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            arr.add(l2.val);
            l2 = l2.next;
        }
        Collections.sort(arr); //O(NlogN)
        // form new linked list
        ListNode pre = new ListNode(-1);
        ListNode p = pre;
        for(int i = 0; i < arr.size(); i++){
            p.next = new ListNode(arr.get(i));
            p = p.next;
        }
        return pre.next;
    }
}


