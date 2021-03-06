/*

98. Validate Binary Search Tree
https://leetcode.com/problems/validate-binary-search-tree/description/


Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's
key.
The right subtree of a node contains only nodes with keys greater than the
node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input:
    2
   / \
  1   3
Output: true

Example 2:
Input:
    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value is 5 but
its right child's value is 4.

*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// DFS Top-Down
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        boolean[] result = new boolean[]{true};
        dfs(root, null, null, result);
        
        return result[0];
    }
    private void dfs(TreeNode node, Integer min, Integer max, boolean[] result) {
        // op at current node
        if ((min != null && node.val <= min) ||
            (max != null && node.val >= max)) {
            result[0] = false;
            return;
        }
        
        // go down to children
        if (result[0] && node.left != null) {
            dfs(node.left, min, new Integer(node.val), result);
        }
        if (result[0] && node.right != null) {
            dfs(node.right, new Integer(node.val), max, result);
        }
        
        // go up to parent
        // do nothing
    }
}
// 75 / 75 test cases passed.
// Runtime: 0 ms


// DFS Bottom-Up
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root)._isBST;
    }
    
    private Result dfs(TreeNode node) {
        // base case: null
        if (node == null) {
            return new Result(true, 123, 123);
        }
        
        // general case: non-null node
        Result leftResult = dfs(node.left);
        Result rightResult = dfs(node.right);
        
        // opration at non-null node
        boolean isBST = leftResult._isBST && rightResult._isBST &&
            (node.left == null || leftResult._max < node.val) &&
            (node.right == null || node.val < rightResult._min);
        int min = node.left == null ? node.val : leftResult._min;
        int max = node.right == null ? node.val : rightResult._max;
        
        return new Result(isBST, min, max);
    }
    
    class Result {
        boolean _isBST;
        int _min;
        int _max;
        public Result(boolean isBST, int min, int max) {
            _isBST = isBST;
            _min = min;
            _max = max;
        }
    }
}
// 75 / 75 test cases passed.
// Runtime: 1 ms


// BFS
class Solution {
    class MyNode {
        TreeNode _node;
        Integer _min;
        Integer _max;
        public MyNode(TreeNode node, Integer min, Integer max) {
            _node = node;
            _min = min;
            _max = max;
        }
    }
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(new MyNode(root, null, null));
        while (!queue.isEmpty()) {
            MyNode myNode = queue.remove();
            
            // op at node
            if ((myNode._min != null && myNode._min >= myNode._node.val) ||
               (myNode._max != null && myNode._node.val >= myNode._max)) {
                return false;
            }
            
            // go down to children
            if (myNode._node.left != null) {
                queue.add(new MyNode(myNode._node.left, 
                    myNode._min, new Integer(myNode._node.val)));
            }
            if (myNode._node.right != null) {
                queue.add(new MyNode(myNode._node.right, 
                    new Integer(myNode._node.val), myNode._max));
            }
        }
        
        return true;
    }
}
// 75 / 75 test cases passed.
// Runtime: 3 ms


