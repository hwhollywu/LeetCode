
// https://leetcode.com/problems/count-and-say/

class Solution {
    public String countAndSay(int n) {
        String prev = "1";
        if (n == 1) return prev;
        for (int i = 2; i <= n; i++){
            int j = 0;
            StringBuilder sb = new StringBuilder();
            while(j < prev.length()){
                int num = 1;
                while (j + 1 < prev.length () && prev.charAt(j) == prev.charAt(j + 1)){
                    num++;
                    j++;
                }
                sb.append(num+"");
                sb.append(prev.charAt(j)+"");
                j++;
            }
            prev = sb.toString();
        }
        return prev; 
        
    }
}