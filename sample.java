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
    Queue<TreeNode> q = new LinkedList<>();
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder("");
        if(root != null) {
            q.add(root);
        }
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if(curr == null) {
                result.append("null");
            } else if(curr != null){
                q.add(curr.left);
                q.add(curr.right);
                //char c = (char) (curr.val + '0');
                result.append(curr.val);
            }
            result.append(",");
        }
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        Queue<TreeNode> q = new LinkedList<>();
        System.out.println(data);
        String[] starray = data.split(",");
        int i=1;
        TreeNode root = new TreeNode(Integer.parseInt(starray[0]));
        q.add(root);
        while(!q.isEmpty())  {
            TreeNode curr = q.poll();
            if(!starray[i].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(starray[i]));
                q.add(curr.left);
            }
            i++;
            if(!starray[i].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(starray[i]));
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    Queue<Integer> queuedist = new LinkedList<>();
    Queue<TreeNode> queuetreenode = new LinkedList<>();
    Map<Integer, List<Integer>> distnode = new HashMap<>();
    int min=Integer.MAX_VALUE;
    int max=Integer.MIN_VALUE;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        int dist=0;
        if (root != null) {
            queuetreenode.add(root);
            queuedist.add(0);
        }
        while(!queuetreenode.isEmpty()) {
            TreeNode curr = queuetreenode.poll();
            int currdist = queuedist.poll();
            min = Math.min(min, currdist);
            max = Math.max(max, currdist);
            if(!distnode.containsKey(currdist)) {
                distnode.put(currdist, new ArrayList<>());
            }
            distnode.get(currdist).add(curr.val);

            if (curr.left != null) {
                queuetreenode.add(curr.left);
                queuedist.add(currdist-1);
            }
            if (curr.right != null) {
                queuetreenode.add(curr.right);
                queuedist.add(currdist+1);
            }
            
        }
        for (int i=min;i<max+1;i++){
            result.add(new ArrayList<>(distnode.get(i)));
        }
        return result;
        
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int sum=0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return -1;
        }
        dfs(root, low, high);
        return sum;
    }

    private void dfs(TreeNode root, int low, int high) {
        if(root == null) {
            return;
        }
        if(root.val >= low && root.val <= high) {
            sum = sum + root.val;
            dfs(root.left, low, high);
            dfs(root.right, low, high);
        } else if(root.left != null && root.left.val >= low) {
            dfs(root.left, low, high);
        } else if(root.right != null && root.right.val <= high) {
            dfs(root.right, low, high);
        }

    }
}
