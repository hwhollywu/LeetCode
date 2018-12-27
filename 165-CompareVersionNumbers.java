165-CompareVersionNumbers

// https://leetcode.com/problems/compare-version-numbers/

class Solution {
    public int compareVersion(String version1, String version2) {
        
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        int length = Math.max(v1.length, v2.length);
        for (int i = 0; i < length ; i ++){
            Integer i1 = (i < v1.length ? Integer.valueOf(v1[i]) : 0);
            Integer i2 = (i < v2.length ? Integer.valueOf(v2[i]) : 0);
            if (i1 > i2) return 1;
            else if (i1 < i2) return -1;
        }
        return 0;
    }
}