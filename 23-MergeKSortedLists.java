
// 23. Merge k Sorted Lists
// https://leetcode.com/problems/merge-k-sorted-lists/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Brute Force O(NlogN)

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> arr = new ArrayList();
        // add every node to arr
        for(ListNode l : lists){
            while(l != null){
                arr.add(l.val);
                l = l.next;
            }
        }
        Collections.sort(arr);
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

// PQ / Heap O(NlogK) N = #nodes, K = #lists

class Solution {
    
    private static final Comparator<ListNode> comp = new Comparator<ListNode>(){
        public int compare(ListNode a, ListNode b){
            return a.val - b.val;
        }
        
    };
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        Queue<ListNode> q = new PriorityQueue<ListNode>(lists.length, comp);
        for(ListNode node: lists){
            if (node != null) q.add(node);
        }
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while(!q.isEmpty()){
            ListNode min = q.poll();
            if (min.next != null) q.add(min.next);
            p.next = min;
            p = min;
            
        }
        return dummy.next;
        
    }
}

// Divede & Conquer - FASTER

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    
    public ListNode mergeKListsHelper(ListNode[] lists, int l, int r){
        if (l >= r) return lists[l];
        int mid = l + (r - l) / 2;
        ListNode left = mergeKListsHelper(lists, l, mid);
        ListNode right = mergeKListsHelper(lists, mid+1, r);
        return merge2Lists(left, right);
    }
    public ListNode merge2Lists(ListNode a, ListNode b){
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(a != null && b != null){
            if (a.val < b.val){
                tail.next = a;
                tail = a;
                a = a.next;
            }else{
                tail.next = b;
                tail = b;
                b = b.next;
            }
        }
        if (a != null){
            tail.next = a;
        }else{
            tail.next = b;
        }
        return dummy.next;
    }
}


