281-ZigzagIterator

// https://leetcode.com/problems/zigzag-iterator/
/*
Input:
v1 = [1,2]
v2 = [3,4,5,6] 

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,3,2,4,5,6].

*/

public class ZigzagIterator {
    
    List<Integer> v1;
    List<Integer> v2;
    int i = 0;
    int j = 0;
    boolean isv1 = true;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        // base cases: if one list depleted
        if(i == v1.size()){
            j++;
            return v2.get(j - 1);
        }
        if(j == v2.size()){
            i++;
            return v1.get(i - 1);
        }
        // flip v1
        if(isv1){
            i++;
            isv1 = false;
            return v1.get(i - 1);
        }
        else{
            j++;
            isv1 = true;
            return v2.get(j - 1);
        }
        
    }

    public boolean hasNext() {
        return (i + j < v1.size() + v2.size() && i <= v1.size() && j <= v2.size());
    }
}



// use iterator


 public class ZigzagIterator {

    private Iterator<Integer> i, j, tmp;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v2.iterator();
        j = v1.iterator();
    }

    public int next() {
        if (j.hasNext()) { tmp = j; j = i; i = tmp; }
        return i.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
}



public class ZigzagIterator {
    LinkedList<Iterator> list;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<Iterator>();
        if(!v1.isEmpty()) list.add(v1.iterator());
        if(!v2.isEmpty()) list.add(v2.iterator());
    }

    public int next() {
        Iterator poll = list.remove();
        int result = (Integer)poll.next();
        if(poll.hasNext()) list.add(poll);
        return result;
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }
}