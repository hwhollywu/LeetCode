729. My Calendar
// https://leetcode.com/problems/my-calendar-i/

/*
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation: 
The first event can be booked.  The second can't because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.

*/

// Approach BruteForce, Time O(N^2), Space O(N)
// use arraylist of array or hashmap 
// List<int[]> calendar;

class MyCalendar {
    HashMap<Integer, Integer> map;

    public MyCalendar() {
        map = new HashMap();
    }
    
    public boolean book(int start, int end) {
        // when inserting new calender, check each item in map
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            Integer es = entry.getKey();
            Integer ee = entry.getValue();
            if (es < end && ee > start) return false;
        }
        map.put(start,end);
        return true;
    }
}



// Approach TreeMap
// Red-Black Tree: to store unique elements in a sorted order. O(nLogn)
// floorKey(key): Returns the greatest key less than or equal to the given key, or null if there is no such key.
// ceilingKey(key) : Returns the least key greater than or equal to the given key, or null if there is no such key.

// Time Complexity O(NlogN), Space O(N)

class MyCalendar {
    TreeMap<Integer, Integer> map;

    public MyCalendar() {
        map = new TreeMap();
    }
    
    public boolean book(int start, int end) {
        // greatest key <=  given key
        Integer floor = map.floorKey(start);
        // smallest key >= given key
        Integer ceiling = map.ceilingKey(start);
        // compare with both floor and ceiling
        if((floor != null && start < map.get(floor)) || (ceiling != null && end > ceiling)) return false;
        map.put(start,end);
        return true;
    }
}

// https://leetcode.com/problems/my-calendar-ii/




// https://leetcode.com/problems/my-calendar-iii/




