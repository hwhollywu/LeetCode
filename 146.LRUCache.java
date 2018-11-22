// 146. LRU Cache

// https://leetcode.com/problems/lru-cache/description/
// https://aaronice.gitbooks.io/lintcode/data_structure/lru_cache.html

class LRUCache {
    
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
    
    private class Node {
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
    
    private int capacity;        
    private HashMap<Integer, Node> hm = new HashMap<>();
    private Node head = new Node(-1,-1);
    private Node tail = new Node(-1,-1);
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        // connect head and tail node
        this.head.next = this.tail;
        this.tail.prev = this.head;
        
    }
    
    public int get(int key) {
        if (!hm.containsKey(key)) return -1;
        Node n = hm.get(key);
        // disconnect node
        n.prev.next = n.next;
        n.next.prev = n.prev;
        moveToTail(n);
        return hm.get(key).value;
    }
    
    public void put(int key, int value) {
        if (get(key) != -1){
            hm.get(key).value = value;
            return;
        }
        if (hm.size() == capacity){
            hm.remove(head.next.key);
            // remove from list
            head.next = head.next.next;
            head.next.prev = head;
        }
        Node n = new Node(key,value);
        hm.put(key,n);
        moveToTail(n);
    }
    
    public void moveToTail(Node n){
        n.next = tail;
        tail.prev.next = n;
        n.prev = tail.prev;
        tail.prev = n;
    }
}

