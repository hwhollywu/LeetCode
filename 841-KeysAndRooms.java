841-KeysAndRooms

// https://leetcode.com/problems/keys-and-rooms/

class Solution {
    int numRooms = 0;
    HashSet<Integer> visited;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        numRooms = rooms.size();
        visited = new HashSet();
        visited.add(0);
        dfs(rooms, rooms.get(0));
        if (visited.size() == numRooms) return true;
        else {
            //System.out.println("numRooms:" + numRooms);
            //for (Integer i : visited) {
            // System.out.println(i);
            return false;
            }
        }
    }
    
    private void dfs(List<List<Integer>> rooms, List<Integer> r){
        // for each key in the room
        for (int i = 0; i < r.size(); i++){
            if (!visited.contains(r.get(i))){
                visited.add(r.get(i));
                dfs(rooms, rooms.get(r.get(i)));
            }
        }
    }
}


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Stack<Integer> dfs = new Stack<>(); dfs.add(0);
        HashSet<Integer> seen = new HashSet<Integer>(); seen.add(0);
        while (!dfs.isEmpty()) {
            int i = dfs.pop();
            for (int j : rooms.get(i))
                if (!seen.contains(j)) {
                    dfs.add(j);
                    seen.add(j);
                    if (rooms.size() == seen.size()) return true;
                }
        }
        return rooms.size() == seen.size();
    }