/*

226. Invert Binary Tree
https://leetcode.com/problems/invert-binary-tree/description/

Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
This problem was inspired by this original tweet by Max Howell:

Google: 90% of our engineers use the software you wrote (Homebrew), but you
can’t invert a binary tree on a whiteboard so f*** off.

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

// dfs top-down
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            return root;
        }

        // invert children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // recursively call
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
    }
}
// 68 / 68 test cases passed.
// Runtime: 0 ms



// dfs bottom-up
class Solution {
    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }
        
        // general case
        TreeNode leftResult = invertTree(root.left);
        TreeNode rightResult = invertTree(root.right);

        root.left = rightResult;
        root.right = leftResult;

        return root;
    }
}
// 68 / 68 test cases passed.
// Runtime: 0 ms


