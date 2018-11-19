
// 387. First Unique Character in a String

// https://leetcode.com/problems/first-unique-character-in-a-string/

class Solution {
    public int firstUniqChar(String s) {
        // boolean indicate if character is repeating, initialized to be false,
        // update it to be true, ignore true ones
        Map<Character,Boolean> map = new HashMap<Character,Boolean>();
        for (int i = 0; i < s.length(); i++){
            Character ch = s.charAt(i);
             if (map.get(ch) == null){
                map.put(ch, false);
            }else if (map.get(ch) == false){
                map.put(ch, true);
             }
        }
        // traverse again, get false character
        for (int i = 0; i < s.length(); i++){
            Character ch = s.charAt(i);
            if (map.get(ch) == false){
                return i;
            }
        }
        // if not found
        return -1;
    }
}

// 47 ms 35%

class Solution {
    public int firstUniqChar(String s) {
		char[] a = s.toCharArray();
		
		for(int i=0; i<a.length;i++){
			if(s.indexOf(a[i])==s.lastIndexOf(a[i])){return i;}
		}
		return -1;
    }
}

// beat 50%