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


// Approach BFS

public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.val + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (!values[++i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }
}


449. Serialize and Deserialize BST

// https://leetcode.com/problems/serialize-and-deserialize-bst/


