// 19. remove the n-th node from the end of list 

//https://leetcode.com/problems/remove-nth-node-from-end-of-list/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // two pointers
        ListNode l1 = head;
        ListNode l2 = head;
        // move l1 first by n
        while(n-- > 0){
            l1 = l1.next;
        }
        // special case: remove head
        if (l1 == null) return head.next;
        // move l1 and l2 together
        while(l1.next != null){
            l1 = l1.next;
            l2 = l2.next;
        }
        // find the goal node in l2.next
        l2.next = l2.next.next;
        return head;
    }
}