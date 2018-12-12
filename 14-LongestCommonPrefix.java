// 14. Longest Common Prefix

// https://leetcode.com/problems/longest-common-prefix/

// Horizontal Time O(S) s: sum of characters in a string, Space O(1)

 public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String prefix = strs[0];
    // for each string in strs
    for (int i = 1; i < strs.length; i++)
        while (strs[i].indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) return "";
        }        
    return prefix;
}


// Vertical scanning 

public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    // loop through each char in first string 
    for (int i = 0; i < strs[0].length() ; i++){
        char c = strs[0].charAt(i);
        // for each next string, check if c matches && index i < str length
        for (int j = 1; j < strs.length; j ++) {
            if (i == strs[j].length() || strs[j].charAt(i) != c)
                return strs[0].substring(0, i);             
        }
    }
    return strs[0];
}


// Divide and Conquer Time O(S), Space  O(m⋅log(n))

public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";    
        return longestCommonPrefix(strs, 0 , strs.length - 1);
}

private String longestCommonPrefix(String[] strs, int l, int r) {
    if (l == r) {
        return strs[l];
    }
    else {
        int mid = (l + r)/2;
        String lcpLeft =   longestCommonPrefix(strs, l , mid);
        String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
        return commonPrefix(lcpLeft, lcpRight);
   }
}

String commonPrefix(String left,String right) {
	// find shorter string between the two
    int min = Math.min(left.length(), right.length());   
    // check matching characters, if not match, return the substring    
    for (int i = 0; i < min; i++) {
        if ( left.charAt(i) != right.charAt(i) )
            return left.substring(0, i);
    }
    return left.substring(0, min);
}




// Binary Search Time O(S⋅log(n)), Space O(1)

public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0)return "";
    int minLen = Integer.MAX_VALUE;
    // find min length among strings 
    for (String str : strs)
        minLen = Math.min(minLen, str.length());
    int low = 1;
    int high = minLen;
    while (low <= high) {
        int middle = (low + high) / 2;
        if (isCommonPrefix(strs, middle))
            low = middle + 1;
        else
            high = middle - 1;
    }
    return strs[0].substring(0, (low + high) / 2);
}

private boolean isCommonPrefix(String[] strs, int len){
    String str1 = strs[0].substring(0,len);
    for (int i = 1; i < strs.length; i++)
    	// startsWith function
        if (!strs[i].startsWith(str1))
            return false;
    return true;
}
