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

/*
return true if the event can be added to the calendar successfully 
without causing a triple booking.

MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
Explanation: 
The first two events can be booked.  The third event can be double booked.
The fourth event (5, 15) can't be booked, because it would result in a triple booking.
The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
*/

// Approach BruteForce, Time O(N^2), Space O(N)
// HashMap有test case不能pass

class MyCalendarTwo {
    HashMap<Integer, Integer> singles;
    HashMap<Integer, Integer> doubles; // store overlaps
    
    public MyCalendarTwo() {
        singles = new HashMap();
        doubles = new HashMap();
    }
    
    public boolean book(int start, int end) {
        if(start < 0 || end < 0 || start > end) return true; // base case
        //check if there is conflict with doubles
        for(Map.Entry<Integer, Integer> entry : doubles.entrySet()){
            if(start < entry.getValue() && end > entry.getKey()){
                return false;
            } 
        }
        // check singles and add to doubles if necessary
        for(Map.Entry<Integer, Integer> entry : singles.entrySet()){
            if(start < entry.getValue() && end > entry.getKey()){
                doubles.put(Math.max(start, entry.getKey()), Math.min(end, entry.getValue()));
            }
        }
        singles.put(start, end);
        return true;
    }
}


class MyCalendarTwo {
    List<int[]> singles;
    List<int[]> doubles; // store overlaps
    
    public MyCalendarTwo() {
        singles = new ArrayList();
        doubles = new ArrayList();
    }
    
    public boolean book(int start, int end) {
        if(start < 0 || end < 0 || start > end) return true; // base case
        //check if there is conflict with doubles
        for(int[] entry : doubles){
            if(end > entry[0] && start < entry[1]){
                return false;
            } 
        }
        // check singles and add to doubles if necessary
         for(int[] entry : singles){
            if(end > entry[0] && start < entry[1]){
                doubles.add(new int[]{Math.max(start, entry[0]), Math.min(end, entry[1])});
            }
        }
        singles.add(new int[]{start, end});
        return true;
    }
}

// Approach TreeMap

class MyCalendarTwo {
    TreeMap<Integer,Integer> map;
    
    public MyCalendarTwo() {
        map = new TreeMap();
    }
    
    public boolean book(int start, int end) {
        // add to map
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        // check #active bookings
        int count = 0;
        for(int b : map.values()){
            count += b;
            if (count >= 3){
                // undo booking
                map.put(start, map.getOrDefault(start, 0) - 1);
                map.put(end, map.getOrDefault(end, 0) + 1);
                if(map.get(start) == 0){
                    map.remove(start);
                }
                return false;
            }
        }
        return true;
    }
}



// https://leetcode.com/problems/my-calendar-iii/

/*
k-bookings

MyCalendarThree();
MyCalendarThree.book(10, 20); // returns 1
MyCalendarThree.book(50, 60); // returns 1
MyCalendarThree.book(10, 40); // returns 2
MyCalendarThree.book(5, 15); // returns 3
MyCalendarThree.book(5, 10); // returns 3
MyCalendarThree.book(25, 55); // returns 3
Explanation: 
The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.
The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.
The remaining events cause the maximum K-booking to be only a 3-booking.
Note that the last event locally causes a 2-booking, but the answer is still 3 because
eg. [10, 20), [10, 40), and [5, 15) are still triple booked.

*/

class MyCalendarThree {
    
    TreeMap<Integer,Integer> map;

    public MyCalendarThree() {
        map = new TreeMap();
        
    }
    
    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0;
        int max = 0;
        for(int b : map.values()){
            count += b;
            if(count > max) max = count;
        }
        return max;    
    }
}

