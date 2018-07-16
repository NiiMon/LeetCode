/*

663. Equal Tree Partition
https://leetcode.com/problems/equal-tree-partition/description/


Given a binary tree with n nodes, your task is to check if it's possible to
partition the tree to two trees which have the equal sum of values after
removing exactly one edge on the original tree.

Example 1:
Input:     
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation: 
    5
   / 
  10
      
Sum: 15

   10
  /  \
 2    3

Sum: 15


Example 2:
Input:     
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after
removing exactly one edge on the tree.

Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000

*/


/*

test cases:
[5,10,10,null,null,2,3]
[1,2,10,null,null,2,20]
[1]
[1,2,3]
[1,-1]
[-9,-3,2,null,4,4,0,-6,null,-5]

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
    public boolean checkEqualTree(TreeNode root) {
        int sum = treeSum(root);
        
        if (sum % 2 != 0) {
            return false;
        }
        return dfs(root, sum / 2)._canPartition;
    }

    private Result dfs(TreeNode node, int halfTreeSum) {
        // base case : null
        if (node == null) {
            return new Result(false, 0);
        }
        
        // general case : non-null
        Result leftResult = dfs(node.left, halfTreeSum);
        Result rightResult = dfs(node.right, halfTreeSum);

        boolean canPartition = leftResult._canPartition ||
        rightResult._canPartition || 
        (node.left != null && leftResult._sum == halfTreeSum) || 
        (node.right != null && rightResult._sum == halfTreeSum);

        int sum = leftResult._sum + rightResult._sum + node.val;

        return new Result(canPartition, sum);
    }

    private int treeSum(TreeNode node) {
        // base case : null
        if (node == null) {
            return 0;
        }

        // general case : non-null
        return treeSum(node.left) + treeSum(node.right) + node.val;
    }

    class Result {
        boolean _canPartition;
        int     _sum;

        public Result(boolean canPartition, int sum) {
            _canPartition   = canPartition;
            _sum            = sum;
        }
    }
}
// 197 / 197 test cases passed.
// Runtime: 9 ms


