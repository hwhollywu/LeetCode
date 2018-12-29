394-DecodeString

// https://leetcode.com/problems/decode-string/

class Solution {
    public String decodeString(String s) {
        Stack<Integer> numberstack = new Stack();
        Stack<String> wordstack = new Stack();
        String res = "";
        
        int i = 0;
        while(i<s.length()){
            if (Character.isDigit(s.charAt(i))){
                int num = 0;
                while(Character.isDigit(s.charAt(i))){
                    num = num*10 + (s.charAt(i) - '0');
                    i++;
                }
                numberstack.push(num);
                
            }else if (s.charAt(i) == '['){
                wordstack.push(res);
                // reset res
                res = "";
                i++;
                
            }else if (s.charAt(i) == ']'){
                StringBuilder sb = new StringBuilder(wordstack.pop()); // pop ""
                int count = numberstack.pop();
                for (int k = 0; k < count; k ++){
                    sb.append(res);
                }
                res = sb.toString();
                i++;
                
            }else{
                // letters
                res += s.charAt(i);
                i++;
            }
        }
        return res;
    }
}