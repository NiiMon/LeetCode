/*

106. Construct Binary Tree from Inorder and Postorder Traversal
https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/


Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given:
    inorder = [9,3,15,20,7]
    postorder = [9,15,7,20,3]

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
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return dfs(inorder, 0, inorder.length - 1,
                    postorder, 0, postorder.length - 1);
    }
    private TreeNode dfs(int[] inorder, int inStart, int inEnd,
                        int[] postorder, int postStart, int postEnd) {
        if (inEnd < inStart || postEnd < postStart) {
            return null;
        }

        int pivot = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == postorder[postEnd]) {
                pivot = i;
                break;
            }
        }

        TreeNode root = new TreeNode(postorder[postEnd]);

        root.left = dfs(inorder, inStart, pivot - 1,
                        postorder, postStart, postStart + (pivot - inStart) - 1);
        root.right = dfs(inorder, pivot + 1, inEnd,
                        postorder, postStart + (pivot - inStart), postEnd - 1);
        
        return root;
    }
}
// 203 / 203 test cases passed.
// Runtime: 4 ms
