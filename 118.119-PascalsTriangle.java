// 118. Pascal's Triangle

// https://leetcode.com/problems/pascals-triangle/

/*
Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

// Time Complexity O(numRows^2)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList();
        if (numRows <= 0) return res;
        res.add(new ArrayList<>());
        res.get(0).add(1);
        for (int i = 1; i < numRows; i++){
            List<Integer> list = new ArrayList();
            List<Integer> last = res.get(i-1);
            // construct list
            list.add(1); // first ele
            for (int j = 1; j < i; j++){
                list.add(last.get(j-1) + last.get(j));
            }
            list.add(1); // last ele
            res.add(list);
        }
        return res;
    }
}

// 119. Pascal's Triangle 
// https://leetcode.com/problems/pascals-triangle-ii/
/*
Input: 3
Output: [1,3,3,1]
*/

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
    
        for (int i = 0; i <= rowIndex; i++){
            res.add(1);
            for (int j = i - 1; j > 0; j--){
                res.set(j, res.get(j) + res.get(j - 1));
            }
            
        }
        return res;
        
    }
}


class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList();
        for(int i = 0; i <= rowIndex; i++){
            List<Integer> temp =  new ArrayList<>(res); // clone arraylist
            res.add(1);
            for (int j = 1; j < i; j++){
                res.set(j, temp.get(j) + temp.get(j-1));
            }
        }
        return res;
    }
}