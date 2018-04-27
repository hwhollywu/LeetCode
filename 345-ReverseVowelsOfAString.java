// 345. Reverse Vowels of a String (Easy)
// https://leetcode.com/problems/reverse-vowels-of-a-string/description/

class Solution {
    public String reverseVowels(String s) {
        // base case
        if (s.length() <= 1) return s;
        // initialize vowels list 
        Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int left = 0, right = s.length() - 1;
        char[] result = new char[s.length()];
        while (left <= right){ // need to be <= because otherwise the middle char won't be set
            char i = s.charAt(left);
            char j = s.charAt(right);
            if (!vowels.contains(i)){
                result[left] = i;
                left++;
            }else if (!vowels.contains(j)){
                result[right] = j;
                right--;
            }else{
                // find both vowels and swap
                result[left] = j;
                result[right] = i;
                // continue setting new character array
                left++;
                right--;
            }
        }
        return new String(result); // char array to string
    }
}