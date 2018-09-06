// 20. Valid Parentheses
// https://leetcode.com/problems/valid-parentheses/description/) 

/* 
	1. how to use stack in Java
	2. check empty case before using peek function
*/


class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i)  == '['){
                stack.push(s.charAt(i));
            }else if (s.charAt(i) == ')'){
                if (stack.empty()) return false;
                if (stack.peek() != '('){
                    return false;
                }else {
                    stack.pop();
                }
                
            }else if (s.charAt(i) == '}'){
                if (stack.empty()) return false;
                if (stack.peek() != '{'){
                    return false;
                }else {
                    stack.pop();
                }
            }else if (s.charAt(i) == ']'){
                if (stack.empty()) return false;
                if (stack.peek() != '['){
                    return false;
                }else {
                    stack.pop();
                    }   
                }
        }
        if (stack.empty()){
            return true;
        }else return false;
    }
}



// Only push expected parenthesis value to the stack. 

public boolean isValid(String s) {
	Stack<Character> stack = new Stack<Character>();
	for (char c : s.toCharArray()) {
		if (c == '(')
			stack.push(')');
		else if (c == '{')
			stack.push('}');
		else if (c == '[')
			stack.push(']');
		else if (stack.isEmpty() || stack.pop() != c)
			return false;
	}
	return stack.isEmpty();
}