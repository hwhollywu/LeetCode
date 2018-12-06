// 412.FizzBuzz
// https://leetcode.com/problems/fizz-buzz/solution/


// Naive approach 

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> output = new ArrayList<String>();
        
        for (int i = 1; i<= n;i++){
            if (i%15==0){
                output.add("FizzBuzz");
            }else if (i%3==0){
                output.add("Fizz");  
            }else if (i%5==0){
                output.add("Buzz");
            }else{
                output.add(""+i);
            }
        }
        return output;
        
    }
}


// String cancatenation

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> output = new ArrayList<String>();
        
        for (int i = 1; i<= n;i++){
            String str = "";
            if (i%3==0){
                str+="Fizz";
            }if (i%5==0){
                str+="Buzz";
            }if (str==""){
                str+=Integer.toString(i);
            }
            output.add(str);
        }
        return output;
        
    }
}

// Hash

class Solution {
  public List<String> fizzBuzz(int n) {

    // ans list
    List<String> ans = new ArrayList<String>();

    // Hash map to store all fizzbuzz mappings.
    HashMap<Integer, String> fizzBizzDict =
        new HashMap<Integer, String>() {
          {
            put(3, "Fizz");
            put(5, "Buzz");
          }
        };

    for (int num = 1; num <= n; num++) {

      String numAnsStr = "";

      for (Integer key : fizzBizzDict.keySet()) {

        // If the num is divisible by key,
        // then add the corresponding string mapping to current numAnsStr
        if (num % key == 0) {
          numAnsStr += fizzBizzDict.get(key);
        }
      }

      if (numAnsStr.equals("")) {
        // Not divisible by 3 or 5, add the number
        numAnsStr += Integer.toString(num);
      }

      // Append the current answer str to the ans list
      ans.add(numAnsStr);
    }

    return ans;
  }
}