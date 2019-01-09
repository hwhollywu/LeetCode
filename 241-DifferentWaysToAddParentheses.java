241-DifferentWaysToAddParentheses

// https://leetcode.com/problems/different-ways-to-add-parentheses/description/

/*
Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
*/

/* 
for each character in input string, break it down by operators +,-,*
to left and right parts (recursively)
for each left and right part, add to result by diff switching operators
*/

class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList();
        if (input == null) return res;
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*'){
                List<Integer> lefts = diffWaysToCompute(input.substring(0, i));
                // ignore the operator at i
                List<Integer> rights = diffWaysToCompute(input.substring(i + 1));
                for (Integer l : lefts){
                    for (Integer r : rights){
                        switch(ch){
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.valueOf(input));
        return res;
    }
}