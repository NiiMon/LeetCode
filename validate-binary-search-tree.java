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



