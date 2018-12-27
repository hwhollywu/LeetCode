150-EvaluateReversePolishNotation

// https://leetcode.com/problems/evaluate-reverse-polish-notation/

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack(); // stores numbers for operation
        for (int i = 0; i < tokens.length; i++){
            if (tokens[i].equals("+")){
                stack.push(Integer.toString(Integer.valueOf(stack.pop()) + Integer.valueOf(stack.pop())));
            }else if (tokens[i].equals("-")){
                int v2 = Integer.valueOf(stack.pop());
                int v1 = Integer.valueOf(stack.pop());
                stack.push(Integer.toString(v1 - v2));
            }else if (tokens[i].equals("*")){
                stack.push(Integer.toString(Integer.valueOf(stack.pop()) * Integer.valueOf(stack.pop())));
            }else if (tokens[i].equals("/")){
                int v2 = Integer.valueOf(stack.pop());
                int v1 = Integer.valueOf(stack.pop());
                stack.push(Integer.toString(v1 / v2));
            }else{
                stack.push(tokens[i]);
            }
        }
        //System.out.println(stack.size());
        return Integer.valueOf(stack.pop());
    }
}