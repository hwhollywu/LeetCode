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

// Solution: https://leetcode.com/problems/basic-calculator-iii/discuss/113590/Java-O(n)-using-two-stack

// problem: not necessarily * sign ; stack2 can't both store int and char
// solution: use helpful function for operation; use a int representation for parentheses in stack1

class Solution {
    public int calculate(String s) {
    	// base
    	if (s == null || s.length() == 0) return 0;
        // stack used to store number for operation
        Stack<Integer> stack1 = new Stack<>(); 
        // stack used to store results for parenthesis
        Stack<Character> stack2 = new Stack<>(); 
        int num = 0;
        char sign = '+'; // default sign
        for(int i = 0; i < s.length(); i++){
            // case 1: numbers
            if(Character.isDigit(s.charAt(i))){
                num = s.charAt(i) - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                operation(sign, stack1, num);
            }
            // case 2: non-numbers
            if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1){
                if(s.charAt(i) == '('){
                	// push to stack2
                    stack1.push(Integer.MAX_VALUE); // denote ( but as int
                    stack2.push(sign);
                    sign = '+'; // reset
                }else if (s.charAt(i) == ')'){
                	num = 0; // reset ??
                	while(stack1.peek() != Integer.MAX_VALUE){
                		num += stack1.pop();
                	}
                	stack1.pop(); // pop out (
                	// pop from stack2, push new result to stack1
                	operation(stack2.pop(), stack1, num);
                }else {
                // if(s.charAt(i) != '(' && s.charAt(i) != ')'){
                    sign = s.charAt(i); // set sign
                }
            }
        }
        int res = 0;
        while(!stack1.isEmpty()){
            res += stack1.pop();
        }
        return res;
    }
    // use helper function for operation of different signs
    public void operation(char sign, Stack<Integer> stack, int num){
    	if (sign == '+'){
            stack.push(num);
        }else if (sign == '-'){
            stack.push(-num);
        }else if (sign == '*'){
            stack.push(stack.pop() * num);
        }else if (sign == '/'){
            stack.push(stack.pop() / num);
        }
    }
}



770. Basic Calculator IV
// https://leetcode.com/problems/basic-calculator-iv/

/*
Input: expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
Output: ["-1*a","14"]

Input: expression = "e - 8 + temperature - pressure",
evalvars = ["e", "temperature"], evalints = [1, 12]
Output: ["-1*pressure","5"]

Input: expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
Output: ["1*e*e","-64"]

Input: expression = "7 - 7", evalvars = [], evalints = []
Output: []

Input: expression = "a * b * c + b * a * c * 4", evalvars = [], evalints = []
Output: ["5*a*b*c"]

Input: expression = "((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))",
evalvars = [], evalints = []
Output: ["-1*a*a*b*b","2*a*a*b*c","-1*a*a*c*c","1*a*b*b*b","-1*a*b*b*c","-1*a*b*c*c","1*a*c*c*c","-1*b*b*b*c","2*b*b*c*c","-1*b*c*c*c","2*a*a*b","-2*a*a*c","-2*a*b*b","2*a*c*c","1*b*b*b","-1*b*b*c","1*b*c*c","-1*c*c*c","-1*a*a","1*a*b","1*a*c","-1*b*c"]
*/

// Time O(2^N + M) N = #expression, M = #evalvars, #evalints
// Space O(N+M)

class Solution {
    public List<String> basicCalculatorIV(String expression, String[] evalVars, int[] evalInts) {
        Map<String, Integer> evalMap = new HashMap();
        for (int i = 0; i < evalVars.length; ++i)
            evalMap.put(evalVars[i], evalInts[i]);

        return parse(expression).evaluate(evalMap).toList();
    }

    public Poly make(String expr) {
        Poly ans = new Poly();
        List<String> list = new ArrayList();
        if (Character.isDigit(expr.charAt(0))) {
            ans.update(list, Integer.valueOf(expr));
        } else {
            list.add(expr);
            ans.update(list, 1);
        }
        return ans;
    }

    public Poly combine(Poly left, Poly right, char symbol) {
        if (symbol == '+') return left.add(right);
        if (symbol == '-') return left.sub(right);
        if (symbol == '*') return left.mul(right);
        throw null;
    }

    public Poly parse(String expr) {
        List<Poly> bucket = new ArrayList();
        List<Character> symbols = new ArrayList();
        int i = 0;
        while (i < expr.length()) {
            if (expr.charAt(i) == '(') {
                int bal = 0, j = i;
                for (; j < expr.length(); ++j) {
                    if (expr.charAt(j) == '(') bal++;
                    if (expr.charAt(j) == ')') bal--;
                    if (bal == 0) break;
                }
                bucket.add(parse(expr.substring(i+1, j)));
                i = j;
            } else if (Character.isLetterOrDigit(expr.charAt(i))) {
                int j = i;
                search : {
                    for (; j < expr.length(); ++j)
                        if (expr.charAt(j) == ' ') {
                            bucket.add(make(expr.substring(i, j)));
                            break search;
                        }
                    bucket.add(make(expr.substring(i)));
                }
                i = j;
            } else if (expr.charAt(i) != ' ') {
                symbols.add(expr.charAt(i));
            }
            i++;
        }

        for (int j = symbols.size() - 1; j >= 0; --j)
            if (symbols.get(j) == '*')
                bucket.set(j, combine(bucket.get(j), bucket.remove(j+1), symbols.remove(j)));

        if (bucket.isEmpty()) return new Poly();
        Poly ans = bucket.get(0);
        for (int j = 0; j < symbols.size(); ++j)
            ans = combine(ans, bucket.get(j+1), symbols.get(j));

        return ans;
    }
}

class Poly {
    HashMap<List<String>, Integer> count; // from a list of free variables to a coefficient
    Poly() {count = new HashMap();}

    void update(List<String> key, int val) {
        this.count.put(key, this.count.getOrDefault(key, 0) + val);
    }

    Poly add(Poly that) {
        Poly ans = new Poly();
        for (List<String> k: this.count.keySet())
            ans.update(k, this.count.get(k));
        for (List<String> k: that.count.keySet())
            ans.update(k, that.count.get(k));
        return ans;
    }

    Poly sub(Poly that) {
        Poly ans = new Poly();
        for (List<String> k: this.count.keySet())
            ans.update(k, this.count.get(k));
        for (List<String> k: that.count.keySet())
            ans.update(k, -that.count.get(k));
        return ans;
    }

    Poly mul(Poly that) {
        Poly ans = new Poly();
        for (List<String> k1: this.count.keySet())
            for (List<String> k2: that.count.keySet()) {
                List<String> kNew = new ArrayList();
                for (String x: k1) kNew.add(x);
                for (String x: k2) kNew.add(x);
                Collections.sort(kNew);
                ans.update(kNew, this.count.get(k1) * that.count.get(k2));
            }
        return ans;
    }

    Poly evaluate(Map<String, Integer> evalMap) {
        Poly ans = new Poly();
        for (List<String> k: this.count.keySet()) {
            int c = this.count.get(k);
            List<String> free = new ArrayList();
            for (String token: k) {
                if (evalMap.containsKey(token))
                    c *= evalMap.get(token);
                else
                    free.add(token);
            }
            ans.update(free, c);
        }
        return ans;
    }

    int compareList(List<String> A, List<String> B) {
        int i = 0;
        for (String x: A) {
            String y = B.get(i++);
            if (x.compareTo(y) != 0) return x.compareTo(y);
        }
        return 0;
    }
    List<String> toList() {
        List<String> ans = new ArrayList();
        List<List<String>> keys = new ArrayList(this.count.keySet());
        Collections.sort(keys, (a, b) ->
            a.size() != b.size() ? b.size() - a.size() : compareList(a, b));

        for (List<String> key: keys) {
            int v = this.count.get(key);
            if (v == 0) continue;
            StringBuilder word = new StringBuilder();
            word.append("" + v);
            for (String token: key) {
                word.append('*');
                word.append(token);
            }
            ans.add(word.toString());
        }
        return ans;
    }
}
