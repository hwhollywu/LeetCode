// 108. 109. Convert Sorted List to Binary Search Tree

// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBST(nums, 0, nums.length-1);
    }
    
    public TreeNode sortedArrayToBST(int[] nums, int l, int r){
        if (l > r) return null;
        int middle = l + (r-l)/2;
        TreeNode n = new TreeNode(nums[middle]);
        n.left = sortedArrayToBST(nums, l, middle-1);
        n.right = sortedArrayToBST(nums, middle+1, r);
        return n;
    }
}



class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode mid = this.findMiddleElement(head);
        TreeNode node = new TreeNode(mid.val);
        // base
        if (head == mid) return node;
        // recursive
        node.left = this.sortedListToBST(head);
        node.right = this.sortedListToBST(mid.next);
        return node;
        
    }
    
    public ListNode findMiddleElement(ListNode head){
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast !=  null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // disconnect left half from the mid node
        if (pre != null) pre.next = null;
        return slow;
    }
}