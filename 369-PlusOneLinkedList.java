369-PlusOneLinkedList

// https://leetcode.com/problems/plus-one-linked-list/

// Approach : two pointers

class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode (0);
        dummy.next = head;
        ListNode i = dummy;
        Listnode j = dummy;
        while (j.next != null) {
        	j = j.next; // j pointing to the end
            if (j.val < 9) {
                i = j; // i pointing to the adding digit
            }
        }
        if (j.val != 9){
        	j.val ++; // add one
        } else{
        	// add digit, non-9 digit add one, 9-digits to 0
        	i.val++; 
        	i = i.next;
        	while(i != null){
        		i.val = 0;
        		i = i.next;
        	}
        }
        if (dummy.val == 0) return dummy.next; // no adding digit
        return dummy;
    }
}

// Approach: recursion
class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null) return null;
        if(plusOneHelper(head)){
            // adding digit
            ListNode newhead = new ListNode(1);
            newhead.next = head;
            return newhead;
        }else return head;
    }
    
    private boolean plusOneHelper(ListNode node){
        if (node == null) return true; // if ending digit
        if (plusOneHelper(node.next)) {  
            node.val = (node.val + 1) % 10; // + 1 or 0
            if (node.val == 0) return true;  // if 0, keep adding 1
        }
        return false;
    }
}
