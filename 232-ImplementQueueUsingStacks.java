232-ImplementQueueUsingStacks

// https://leetcode.com/problems/implement-queue-using-stacks/

class MyQueue {
    
    // use two stacks to reverse the order
    Stack<Integer> s1; // for pushing to the back
    Stack<Integer> s2; // for popping from the front
    
    private int front;

    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack();
        s2 = new Stack();
    }
    
    /** Push element x to the back of queue.  Time O(1), Space O(n) */
    public void push(int x) {
        if(s1.empty()){
            front = x;
        }
        // add element to the back
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element.  Amortized O(1) */
    public int pop() {
        // worst case, s2 is empty, O(n)
        if(s2.isEmpty()){
            while(!s1.empty()){
            s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(!s2.isEmpty()){
            return s2.peek();
        }
        return front;
    }
    
    /** Returns whether the queue is empty. O(1) */
    public boolean empty() {
        return s1.empty() && s2.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */



class MyQueue {

    Stack<Integer> input = new Stack();
    Stack<Integer> output = new Stack();
    
    public void push(int x) {
        input.push(x);
    }

    public void pop() {
        peek();
        output.pop();
    }

    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }
}


225-Implement Stack Using Queues
// https://leetcode.com/problems/implement-stack-using-queues/


class MyStack {

    private Queue<Integer> queue = new LinkedList<>();

    public void push(int x) {
        queue.add(x);
        for (int i=1; i<queue.size(); i++)
            queue.add(queue.remove());
    }

    public void pop() {
        queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}


