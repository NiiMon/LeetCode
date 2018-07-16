/*

333. Largest BST Subtree
https://leetcode.com/problems/largest-bst-subtree/description/


Given a binary tree, find the largest subtree which is a Binary Search Tree
(BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.

Follow up:
Can you figure out ways to solve it with O(n) time complexity?


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
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfs(root)._size;
    }

    private Result dfs(TreeNode node) {
        // base case : null
        if (node == null ) {
            return new Result(true, 0, 0, 0);
        }

        // general case : non-leaf node
        Result leftResult = dfs(node.left);
        Result rightResult = dfs(node.right);

        boolean isBST = leftResult._isBST && rightResult._isBST &&
        (node.left == null || leftResult._max < node.val) && 
        (node.right == null || node.val < rightResult._min);

        int size = isBST ? leftResult._size + rightResult._size + 1 :
        Math.max(leftResult._size, rightResult._size);

        int min = node.left == null ? node.val : leftResult._min;
        int max = node.right == null ? node.val : rightResult._max;

        return new Result(isBST, size, min, max);
    }

    class Result {
        boolean _isBST;
        int     _size;
        int     _min;
        int     _max;

        public Result(boolean isBST, int size, int min, int max) {
            _isBST  = isBST;
            _size   = size;
            _min    = min;
            _max    = max;
        }
    }
}
// 73 / 73 test cases passed.
// Runtime: 5 ms


