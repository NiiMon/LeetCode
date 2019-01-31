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
        return dfs(root)._globalMax;
    }
    private Result dfs(TreeNode node) {
        // base case: null nodes
        if (node == null) {
            return new Result(Integer.MIN_VALUE, 0);
        }
        // Integer.MIN_VALUE
        // general case: non-null nodes
        Result leftResult = dfs(node.left);
        Result rightResult = dfs(node.right);

        int localMax = node.val + max(0, leftResult._localMax, rightResult._localMax);
        int globalMax = max(leftResult._globalMax, 
                            rightResult._globalMax, 
                            leftResult._localMax + node.val + rightResult._localMax,
                            localMax);
        
        return new Result(globalMax, localMax);
    }
    private int max(int... vals) {
        int result = vals[0];
        for (int val : vals) {
            if (val > result) {
                result = val;
            }
        }
        return result;
    }
    class Result {
        int _globalMax;
        int _localMax;
        public Result(int globalMax, int localMax) {
            _globalMax = globalMax;
            _localMax = localMax;
        }
    }
}
// 93 / 93 test cases passed.
// Runtime: 5 ms



