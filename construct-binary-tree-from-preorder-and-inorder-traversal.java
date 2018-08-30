/*

105. Construct Binary Tree from Preorder and Inorder Traversal
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/


Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7


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

// DFS Bottom-Up
class Solution {
    public TreeNode buildTree(int[] pre, int[] in) {
        return helper(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    private TreeNode helper(int[] pre, int preStart, int preEnd, 
                            int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(pre[preStart]);
        
        int index = inStart;
        while (index <= inEnd && in[index] != root.val) {
            index++;
        }
        
        root.left = helper(pre, preStart + 1, preStart + (index - inStart),
                           in, inStart, index - 1);
        root.right = helper(pre, preStart + (index - inStart) + 1, preEnd, 
                            in, index + 1, inEnd);
        
        return root;
    }
}
// 32 / 32 test cases passed.
// Runtime: 0 ms


