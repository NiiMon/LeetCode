/*

250. Count Univalue Subtrees
https://leetcode.com/problems/count-univalue-subtrees/description/


Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4

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
    public int countUnivalSubtrees(TreeNode root) {
        return helper(root)._count;
    }

    private Result helper(TreeNode node) {
        // base case : null
        if (node == null) {
            return new Result(true, 0);
        }

        // general case: all node
        Result leftResult = helper(node.left);
        Result rightResult = helper(node.right);

        boolean isUni = leftResult._isUni && rightResult._isUni && 
                    (node.left == null || node.left.val == node.val) &&
                    (node.right == null || node.right.val == node.val);
        int count = leftResult._count + rightResult._count + (isUni ? 1 : 0);

        return new Result(isUni, count);
    }

    class Result {
        boolean _isUni;
        int _count;
        public Result(boolean isUni, int count) {
            _isUni = isUni;
            _count = count;
        }
    }
}
// 197 / 197 test cases passed.
// Runtime: 1 ms



