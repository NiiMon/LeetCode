/*

236. Lowest Common Ancestor of a Binary Tree
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/


Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in
the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is
defined between two nodes p and q as the lowest node in T that has both p and q
as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of of nodes 5 and 1 is 3.


Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of
itself according to the LCA definition.

Note:
All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q)._ancestor;
    }

    private Result helper(TreeNode node, TreeNode p, TreeNode q) {
        // base case: null
        if (node == null) {
            return new Result(null, false, false);
        }
        
        // general case: non-null
        Result leftResult = helper(node.left, p, q);
        Result rightResult = helper(node.right, p, q);
        
        boolean hasP = leftResult._hasP || rightResult._hasP || node == p;
        boolean hasQ = leftResult._hasQ || rightResult._hasQ || node == q;
        
        TreeNode ancestor = node;
        if (leftResult._hasP && leftResult._hasQ) {
            ancestor = leftResult._ancestor;
        }
        if (rightResult._hasP && rightResult._hasQ) {
            ancestor = rightResult._ancestor;
        }

        return new Result(ancestor, hasP, hasQ);
    }

    class Result {
        TreeNode _ancestor;
        boolean _hasP;
        boolean _hasQ;
        public Result(TreeNode ancestor, boolean hasP, boolean hasQ) {
            _ancestor = ancestor;
            _hasP = hasP;
            _hasQ = hasQ;
        }
    }
}
// 31 / 31 test cases passed.
// Runtime: 10 ms


