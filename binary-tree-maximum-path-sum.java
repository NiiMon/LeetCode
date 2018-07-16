/*

124. Binary Tree Maximum Path Sum
https://leetcode.com/problems/binary-tree-maximum-path-sum/description/


Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting
node to any node in the tree along the parent-child connections. The path must
contain at least one node and does not need to go through the root.

Example 1:
Input: [1,2,3]

       1
      / \
     2   3
Output: 6

Example 2:
Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7
Output: 42

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
    public int maxPathSum(TreeNode root) {
        return dfs(root)._maxSumPath;
    }

    private Result dfs(TreeNode node) {
        // base case : null
        if (node == null) {
            return new Result(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }

        // general case : non-null node
        Result leftResult = dfs(node.left);
        Result rightResult = dfs(node.right);
        
        int maxSumToRoot = node.val + max(0, leftResult._maxSumToRoot, 
            rightResult._maxSumToRoot);

        int maxSumPath = max(maxSumToRoot, 
            leftResult._maxSumPath, rightResult._maxSumPath, 
            Math.max(0, leftResult._maxSumToRoot) + 
            Math.max(0, rightResult._maxSumToRoot) + node.val);

        

        return new Result(maxSumToRoot, maxSumPath);
    }

    private int max(int... input) {
        int result = input[0];

        for (int i : input) {
            if (i > result) {
                result = i;
            }
        }

        return result;
    }

    class Result {
        int _maxSumToRoot;
        int _maxSumPath;

        public Result(int maxSumToRoot, int maxSumPath) {
            _maxSumToRoot   = maxSumToRoot;
            _maxSumPath     = maxSumPath;
        }
    }
}
// 93 / 93 test cases passed.
// Runtime: 5 ms



