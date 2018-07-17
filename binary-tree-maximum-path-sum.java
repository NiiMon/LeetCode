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
public class Solution {
    public int maxPathSum(TreeNode root) {
        return dfs(root)._globalMax;
    }

    private Result dfs(TreeNode node) {
        // base case: null node
        if (node == null) {
            return new Result(0, Integer.MIN_VALUE);
        }
        
        // general case: non-null node
        Result leftResult = dfs(node.left);
        Result rightResult = dfs(node.right);
        
        int toRootMax = max(node.val,
                           node.val + leftResult._toRootMax,
                           node.val + rightResult._toRootMax);
        int globalMax = max(toRootMax,
                           leftResult._globalMax,
                           rightResult._globalMax,
                           leftResult._toRootMax + node.val + rightResult._toRootMax);
        
        return new Result(toRootMax, globalMax);
    }

    private int max(int... vals) {
        int result = vals[0];
        for (int val : vals) {
            result = Math.max(result, val);
        }
        return result;
    }

    class Result {
        int _toRootMax;
        int _globalMax;
        public Result(int toRootMax, int globalMax) {
            _toRootMax = toRootMax;
            _globalMax = globalMax;
        }
    }
}
// 93 / 93 test cases passed.
// Runtime: 5 ms



