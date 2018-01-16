/*

156. Binary Tree Upside Down
https://leetcode.com/problems/binary-tree-upside-down/description/


Given a binary tree where all the right nodes are either leaf nodes with a
sibling (a left node that shares the same parent node) or empty, flip it
upside down and turn it into a tree where the original right nodes turned into
left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized
on OJ(https://leetcode.com/problems/binary-tree-upside-down/description/#).

*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// iterative, top-down
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        
        TreeNode parent = null;
        TreeNode preRight = null;
        
        while (root != null) {
            TreeNode curLeft = root.left;
            TreeNode curRight = root.right;
            
            root.left = preRight;
            root.right = parent;
            
            parent = root;            
            preRight = curRight;
            root = curLeft;
        }
        
        return parent;
    }
}


// recursive, bottom-up
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        
        TreeNode result = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        
        return result;
    }
}

