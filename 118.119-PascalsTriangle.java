// 118. Pascal's Triangle

// https://leetcode.com/problems/pascals-triangle/

// 119. Pascal's Triangle 

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



// Time Complexity O(numRows^2)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // base case
        if (numRows == 0) return res;
        // base case 2
        res.add(new ArrayList<>());
        res.get(0).add(1);
        if (numRows == 1) return res;
        // recursive case
        for (int i = 1; i < numRows; i++){
            List<Integer> row = new ArrayList<>();
            List<Integer> prev = res.get(i-1);
            row.add(1); // first element of all rows
            // calculate each element in the new row
            // = sum of above and to the left
            // + above and to the right
            for (int j = 1; j < i ; j ++){
                row.add(prev.get(j-1) + prev.get(j));
            }
            row.add(1); // last element
            res.add(row);
        }
        return res;
            
        
    }
}

