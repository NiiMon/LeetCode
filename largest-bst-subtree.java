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
        // base case : leaf node
        if (node.left == null && node.right == null) {
            return new Result(true, 1, node.val, node.val);
        }

        // general case : non-leaf node
        Result leftResult = node.left == null ? 
        new Result(true, 0, Integer.MIN_VALUE, Integer.MIN_VALUE) :
        dfs(node.left);

        Result rightResult = node.right == null ?
        new Result(true, 0, Integer.MAX_VALUE, Integer.MAX_VALUE) :
        dfs(node.right);

        boolean isBST = leftResult._isBST && rightResult._isBST &&
        leftResult._max < node.val && node.val < rightResult._min;

        int size = max(leftResult._size, rightResult._size, 
        isBST ? leftResult._size + rightResult._size + 1 : 0);

        int min = node.left == null ? node.val : leftResult._min;
        int max = node.right == null ? node.val : rightResult._max;

        return new Result(isBST, size, min, max);
    }

    private int max(int... input) {
        int result = input[0];
        
        for (int i : input) {
            if (i > result) {
                result = i;
            }
        }

        return result;
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


