
// 13. Roman to Integer
// https://leetcode.com/problems/roman-to-integer/
// 12. Integer to Roman
// https://leetcode.com/problems/integer-to-roman/

class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap(){{
            put('I',1);
            put('V',5);
            put('X',10);
            put('L',50);
            put('C',100);
            put('D',500);
            put('M',1000);
        }};

        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length - 1; i++){
            char c1 = chars[i];
            char c2 = chars[i+1];
            if (map.get(c1) < map.get(c2)) sum -= map.get(c1);
            else sum += map.get(c1);
        }
        return sum += map.get(chars[chars.length-1]);
            
    }
}


public class Solution {
public String intToRoman(int num) {

    int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    
    StringBuilder sb = new StringBuilder();
    
    for(int i=0;i<values.length;i++) {
        while(num >= values[i]) {
            num -= values[i];
            sb.append(strs[i]);
        }
    }
    return sb.toString();
}
}

