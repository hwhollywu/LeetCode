93-RestoreIPAddresses

// https://leetcode.com/problems/restore-ip-addresses/description/

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList();
        if (s.length() == 0) return res;
        restoreIpAddressesRes(0, new StringBuilder(), res, s);
        return res;
    }
    
    private void restoreIpAddressesRes(int parts, StringBuilder sb, List<String> address, String input){
        // base case, return
        if (parts == 4 || input.length() == 0){
            if (parts == 4 && input.length() == 0){
                address.add(sb.toString());
            }
            return;
        }

        // recursive case
        // get current string, each part (between dots) need to be at most 3 digits
        for (int i = 0; i < input.length() && i <= 2; i++){
            // check middle zeros
            if (i!= 0 && input.charAt(0) == '0'){
                break;
            }
            String temp = input.substring(0, i+1);
            // check value between 0-255
            if(Integer.valueOf(temp) <= 255){
                // adding dots before this part
                if (sb.length() != 0){
                    temp = "."+temp;
                }
                sb.append(temp);
                restoreIpAddressesRes(parts + 1, sb, address, input.substring(i + 1));
                sb.delete(sb.length()-temp.length(), sb.length());
            }
        }
    
    }
}