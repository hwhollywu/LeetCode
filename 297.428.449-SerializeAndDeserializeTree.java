297. Serialize and Deserialize Binary Tree

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
// http://mishadoff.com/blog/dfs-on-binary-tree-array/

// Approach DFS
// Time O(N), Space O(N)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializeRec(root, "");
    }
    
    public String serializeRec(TreeNode root, String s){
        // base case
        if(root == null) s += "null,";
        else{
            s += String.valueOf(root.val) + ",";
            s = serializeRec(root.left, s);
            s = serializeRec(root.right, s);
        }
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // parse into array
        String[] arr = data.split(",");
        //array to list
        List<String> list = new LinkedList<String>(Arrays.asList(arr));
        return deserializeRec(list);
    }
    
    public TreeNode deserializeRec(List<String> l){
        //base case - null node
        if(l.get(0).equals("null")){
            l.remove(0);
            return null;
        }
        // construct tree node
        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = deserializeRec(l);
        root.right = deserializeRec(l);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


// Approach BFS (faster)

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // base
        if(root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            //base case
            if(node == null){
                sb.append("null,");
                continue;
            }
            sb.append(node.val + ",");
            q.add(node.left);
            q.add(node.right);
        }
        return sb.toString();
    }
    

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // base 
        if(data == "") return null;
        // parse into array
        String[] arr = data.split(",");
        // use a queue to store data
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        q.add(root);
        // traverse the arr and setup treenode 
        for(int i = 1; i < arr.length; i++){
            TreeNode parent = q.poll();
            if(!arr[i].equals("null")){ // node not null
                TreeNode left = new TreeNode(Integer.valueOf(arr[i]));
                parent.left = left;
                q.add(left);
            }
            // BFS - right = next node in i+1
            i += 1;
            if(!arr[i].equals("null")){ 
                TreeNode right = new TreeNode(Integer.valueOf(arr[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }

}

449. Serialize and Deserialize BST

// https://leetcode.com/problems/serialize-and-deserialize-bst/




428. Serialize and Deserialize N-ary Tree
// https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/

class Codec {
     public static String spliter=",";

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        return serializeRec(root, "");
    }
    
    public String serializeRec(Node root, String s){
        // base case
        if(root == null) s += "null,";
        else{
            s += root.val + spliter;
            s += root.children.size() + spliter;
            for(Node child : root.children){
                s = serializeRec(child, s);
            }
        }
        return s;
    }


    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.equals("")) return null;
        String[] arr = data.split(spliter);
        //System.out.println(Arrays.toString(arr));
        List<String> list = new LinkedList<String>(Arrays.asList(arr));
        return deserializeRec(list);
    }
    
    public Node deserializeRec(List<String> l){
        //base case - null node
        if(l.get(0).equals("null")){
            l.remove(0);
            return null;
        }
        // construct tree node
        Node root = new Node(Integer.valueOf(l.get(0)), new LinkedList<Node>());
        l.remove(0);
        int childsize = Integer.valueOf(l.get(0));
        l.remove(0);
        if(childsize != 0){
            for(int i = 0; i < childsize; i++){
                Node child = deserializeRec(l);
                root.children.add(child);
            }
        }
        return root;
    }
}
