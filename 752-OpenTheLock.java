// 752-OpenTheLock
// https://leetcode.com/problems/open-the-lock/



class Solution {
    public int openLock(String[] deadends, String target) {
        String start = "0000";
        int res = 0;
        Queue<String> q = new LinkedList();
        Set<String> set = new HashSet();
        set.add(start);
        Set<String> dead = new HashSet();
        for (String d: deadends) dead.add(d);
        
        // add root
        q.add(start);
        // use null inputs to represent a layer change
        q.add(null);
        while(!q.isEmpty()){
            String s = q.poll();
            if (s == null){
                res++;
                if (q.peek() != null) q.offer(null);
            }
            else if (s.equals(target)) return res;
            else if (!dead.contains(s)){
                
                List<String> neighbors = nextString(s);
                for (String nei : neighbors){
                  if (!set.contains(nei)){
                      set.add(nei);
                      q.add(nei);
                   }
              }
                
            }

                
        }
        return -1;
    }
    
    // produce 4-int neighboring strings for the input string 
    private List<String> nextString(String s){
        List<String> list = new ArrayList<String>();
        for (int i = 0 ; i < 4; i++){
            String change = ("" + nextInt(Character.getNumericValue(s.charAt(i)))[0]);
            String change2 = ("" + nextInt(Character.getNumericValue(s.charAt(i)))[1]);
            String one =  s.substring(0,i) + change + s.substring(i+1);
            String two =  s.substring(0,i) + change2 + s.substring(i+1);
            list.add(one);
            list.add(two);
        }
        //System.out.println(list);
        return list;
    }
    
    private int[] nextInt(Integer i){
        if (i == 0) return new int[]{9,1};
        if (i == 9) return new int[]{8,0};
        else return new int[]{i-1,i+1};
    }
}