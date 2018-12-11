// 929. Unique Email Addresses

// https://leetcode.com/problems/unique-email-addresses/

// use functions: indexOf(), substring(), replaceAll(), contains()

class Solution {
    public int numUniqueEmails(String[] emails) {
        HashMap<String, Integer> map = new HashMap();
        int count = 0;
        for (String email: emails){
            // seperate each email into 2 substrings
            int i = email.indexOf('@');
            String account = email.substring(0,i);
            String site = email.substring(i);
            // apply rule1
            account = account.replaceAll(".","");
            // apply rule2
            if (account.contains("+")){
                account = account.substring(0,account.indexOf("+"));
            }
            map.put(account+site, 1);
        }
        return map.size();
        
    }
}