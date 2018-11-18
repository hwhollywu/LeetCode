// 7. Reverse Integer

//https://leetcode.com/problems/reverse-integer/

class Solution {
    public int reverse(int x) {
        Stack<Character> stack = new Stack<Character>();
        String s = String.valueOf(x);
        for (int i =0; i < s.length(); i++){
            Character ch = s.charAt(i);
            if (Character.isDigit(ch)){
                stack.push(ch);
            }
        }
        StringBuffer sf = new StringBuffer("");
        while (!stack.empty()){
            sf.append(stack.pop());
        }
        // transfer sf to result
        int result = Integer.valueOf(sf.toString());
        if(x < 0) return (-result);
        else return result;
        
    }
}

/*
Exception in thread "main" java.lang.NumberFormatException: For input string: "9646324351"
    at java.lang.NumberFormatException.forInputString(NumberFormatException.java:21)
    at java.lang.Integer.parseInt(Integer.java:539)
    at java.lang.Integer.valueOf(Integer.java:722)
    at Solution.reverse(Solution.java:16)
    at __DriverSolution__.__helper__(__Driver__.java:8)
    at __Driver__.main(__Driver__.java:52) 
*/

class Solution {
    public int reverse(int x) {
        Stack<Character> stack = new Stack<Character>();
        String s = String.valueOf(x);
        for (int i =0; i < s.length(); i++){
            Character ch = s.charAt(i);
            if (Character.isDigit(ch)){
                stack.push(ch);
            }
        }
        StringBuffer sf = new StringBuffer("");
        while (!stack.empty()){
            sf.append(stack.pop());
        }
        // transfer sf to result
        int result = 0;
        try{
        result = Integer.valueOf(sf.toString());
        }catch (NumberFormatException e){
           return result = 0;
        }
        if(x < 0) return (-result);
        else return result;
    }
}

// speed 26 ms   60%

class Solution {
    public int reverse(int x) {
        int result = 0;
        while(x!=0){
            int pop = x % 10;
            x /= 10;
           if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
           if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            result = result * 10 + pop;
        }
        return result;
    }
}

// 37ms 19%??