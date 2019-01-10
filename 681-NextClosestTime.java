681-NextClosestTime

// https://leetcode.com/problems/next-closest-time/


/*
Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.

*/


// Complexity O(1), O(1) constant times

/* 
Idea: 

// 4 ^ 4 = 256 times 

get each character in time,
4 loops for each index, form a new time
check if the new time is valid 
(if 0 <= hour <= 24 &  0 <= min <= 59), 
add to result set/list
sort the string list
indexOf(time), get time index, return index+1 in list.

*/



class Solution {
    public String nextClosestTime(String time) {
        char[] chars = new char[4];
        // "19:34"
        chars[0] = time.charAt(0);
        chars[1] = time.charAt(1);
        chars[2] = time.charAt(3);
        chars[3] = time.charAt(4);
        
        HashSet<String> timeset = new HashSet();
        
        for (int a = 0; a < 4; a++){
            for (int b = 0; b < 4; b++){
                for (int c = 0; c < 4; c++){
                    for (int d = 0; d < 4; d++){
                        String newtime = ""+chars[a]+chars[b]+":"+chars[c]+chars[d];
                        if(validTime(newtime)){
                            //System.out.println(newtime);
                            timeset.add(newtime);
                        }
                    }
                }
            }
        }
        List<String> timelist = new ArrayList();
        timelist.addAll(timeset);
        Collections.sort(timelist);
        int cur = timelist.indexOf(time);
        //System.out.println(timelist);
        return cur==timelist.size()-1 ? timelist.get(0) : timelist.get(cur+1);    
    }
    
    private boolean validTime(String time){
        int hr = Integer.valueOf(time.substring(0,2));
        int min = Integer.valueOf(time.substring(3));
        return (0 <= hr && hr <= 23 && 0 <= min && min <= 59); 
        //no need to check 1,9 single digit - time must be 4 digits long
    }
}
