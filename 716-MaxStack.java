// 716. Max Stack
// https://leetcode.com/articles/max-stack/


// Design a max stack that supports push, pop, top, peekMax and popMax.

/*
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
*/

class MaxStack{
	public MaxStack(){

	}
	public void push(int x){

	}

	public int pop(){

	}

	public int top(){

	}
	public int peekMax(){

	}

	public void popMax(){

	}

}

// 不知道对不对的approach
class MaxStack{
	Stack<Integer> stack;
	List<Integer> list;
	public MaxStack(){
		stack = new Stack<>();
		list = new LinkedList<>();
	}

	public void push(int x){
		stack.push(x);
		if (list.isEmpty()) list.add(x);
		// only add to list if x >= max in list
		if (x >= list.get(list.size() - 1)) list.add(x);
	}

	public void pop(){
		int cur = stack.pop();
		// if pop the current max, remove max on list
		if (cur == list.get(list.size() -1))
			list.remove(list.size()- 1);
	}

	public int top(){
		return stack.peek();
	}

	public int peekMax(){
		return list.get(list.size()-1);
	}

	public void popMax(){
		int max = list.get(list.size()-1);
		list.remove(list.size()-1);
		// ?? remove the index on stack 
		return max;
	}
}


// Approach #1: Two Stacks [Accepted] spaceO(n)

class MaxStack{
	Stack<Integer> stack;
	Stack<Integer> maxstack;
	public MaxStack(){
		stack = new Stack();
		maxstack = new Stack();
	}
	public void push(int x){
		int max = maxStack.isEmpty() ? x : maxstack.peek();
		// maxstack only add current max (even repeatly)
		maxstack.push(max > x ? max : x);
		stack.push(x);
	}

	public int pop(){
		maxstack.pop();
		return stack.pop();
	}

	public int top(){
		return stack.peek();
	}
	public int peekMax(){
		return maxstack.peek();
	}
	public void popMax(){ // O(N) operation 
		int max = peekMax();
		Stack<Integer> buffer = new Stack();
		while (top() != max) buffer.push(pop());
		// find the element on stack/maxstack and pop
		pop();
		while(! buffer.isEmpty()) push(buffer.pop());
		return max;
	}

}


// Double Linked List + TreeMap [Accepted] Space O(N), Time O(logN)

// https://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html
// https://www.geeksforgeeks.org/doubly-linked-list/

class MaxStack{

	TreeMap<Integer, List<Node>> map; 
	//-> find largest,insert, delete logN operation
	// use the natural ordering of key(integer) - ascending
    DoubleLinkedList dll;
    // use as stack, remove in O(1).
	public MaxStack(){
		map = new TreeMap();
		dll = new DoubleLinkedList();

	}
	public void push(int x){
		// always add to dll/stack
		Node node = dll.add(x);
		// add node to map
		if(!map.containsKey(x)) map.put(x,new ArrayList<Node>());
		map.get(x).add(node);
	}

	public int pop(){
		int val = dll.pop();
		List<Node> l = map.get(val);
		// remove the newest node on that arraylist of thevalue
		l.remove(l.size()-1); 
		if(l.isEmpty()) map.remove(val);
		return val;
	}

	public int top(){
		return dll.peek();

	}
	public int peekMax(){
		return map.lastKey(); // highest key
	}

	public void popMax(){
		int max = peekMax();
		// remove node on stack/dll and map
		List<Node> l = map.get(max);
		Node node = l.remove(l.size()-1);
		// get node from map and remove it from dll
		dll.unlink(node);
		if (l.isEmpty()) map.remove(max);
		return max;
	}

}

class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node x = new Node(val);
        x.next = tail;
        x.prev = tail.prev;
        tail.prev = tail.prev.next = x;
        return x;
    }

    public int pop() {
        return unlink(tail.prev).val;
    }

    public int peek() {
        return tail.prev.val;
    }

    public Node unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}

class Node {
    int val;
    Node prev, next;
    public Node(int v) {val = v;}
}
