224/227/772/770-BasicCalculator

224. Basic Calculator
// https://leetcode.com/problems/basic-calculator/

/*
Inputs: non-negative nums, +, -, , (,)

Input: "1 + 1"
Output: 2
Input: " 2-1 + 2 "
Output: 3
Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
*/

/*
digit: it should be one digit from the current number
'+': number is over, we can add the previous number and start a new number
'-': same as above
'(': push the previous result and the sign into the stack, 
set result to 0, just calculate the new result within the parenthesis.
')': pop out the top two numbers from stack, first one is the sign 
before this pair of parenthesis, second is the temporary result 
before this pair of parenthesis. We add them together.
*/

class Solution {
    public int calculate(String s) {
        // store the result and sign for parentheses
        Stack<Integer> stack = new Stack<Integer>();
        int res = 0;
        int sign = 1; // 1 for +ve, -1 for -ve
        for(int i = 0; i < s.length(); i++){
            // case 1: digit
            if(Character.isDigit(s.charAt(i))){
                int digit = s.charAt(i) - '0';
                // continue reading 
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    digit = digit * 10 + s.charAt(i + 1) - '0';
                    //System.out.println(digit);
                    i++;
                }
                // update result
                res += sign * digit;
            }else if (s.charAt(i) == '+'){
                sign = 1;
            }else if (s.charAt(i) == '-'){
                sign = -1;
            }else if (s.charAt(i) == '('){
                stack.push(res); // store res before stack 
                stack.push(sign); // LIFO
                // reset
                res = 0;
                sign = 1;
            }else if (s.charAt(i) == ')'){
                int prevsign = stack.pop();
                int prevres = stack.pop();
                res = res * prevsign + prevres;
            } // do nothing if char is empty space
        }
        return res;
    }
}

227. Basic Calculator II
// https://leetcode.com/problems/basic-calculator-ii/

/*
Input character: digit, +, -, *, / 

Input: "3+2*2"
Output: 7

Input: " 3/2 "
Output: 1

Input: " 3+5 / 2 "
Output: 5
*/

class Solution {
    public int calculate(String s) {
        // store numbers for operation computation
        Stack<Integer> stack = new Stack<>();
        char sign = '+'; // default sign
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                // store num 
                num = s.charAt(i) - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                //System.out.println("num "+ num + " char i "+ s.charAt(i) +" sign "+ sign);
            } 
            // for non digit, but must execute for last bit (default +)
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() -1){ 
                // operation of num
                //System.out.println("num "+ num + " char i "+ s.charAt(i) +" sign "+ sign);
                if (sign == '+'){ // and default case 
                    stack.push(num);
                }else if (sign == '-'){
                    stack.push(-num);
                }else if (sign == '*'){
                    stack.push(stack.pop() * num);
                }else if (sign == '/'){
                    stack.push(stack.pop() / num);
                }
                //num = 0; //reset
                sign = s.charAt(i); 
            }
            
        }
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}


772. Basic Calculator III
// https://leetcode.com/problems/basic-calculator-iii/
/*
Input: digits, +, -, *, / ,  (, )

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
*/



