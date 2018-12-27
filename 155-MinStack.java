// Min Stack
// https://leetcode.com/problems/min-stack/


// Approach 1. use Stack + min integer
class MinStack {

    /** initialize your data structure here. */
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    
    public MinStack() {
        stack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x); // replace min if pushing min
    }
    
    public void pop() {
        if(stack.pop() == min) min=stack.pop(); // replace min if popping min
     
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


// Approach 2. use a linked list and a stack, list end storing min value

public class MinStack {
    Stack<Integer> stack; 
    List<Integer> list; 
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>(); 
        list = new LinkedList<>(); 
    }
    
    public void push(int x) {
        stack.push(x); 
        if (list.isEmpty() || x <= list.get(list.size() - 1))   
            list.add(x); 
    }
    
    public void pop() {
        int cur = stack.pop();
        if (cur == list.get(list.size() - 1)) 
            list.remove(list.size() - 1); 
    }
    
    public int top() {
        return stack.peek(); 
    }
    
    public int getMin() {
        return list.get(list.size() - 1); 
    }
}






