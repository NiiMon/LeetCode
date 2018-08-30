/*

654. Maximum Binary Tree
https://leetcode.com/problems/maximum-binary-tree/description/

Given an integer array with no duplicates. A maximum tree building on this array
is defined as follow:

1. The root is the maximum number in the array.
2. The left subtree is the maximum tree constructed from left part subarray
divided by the maximum number.
3. The right subtree is the maximum tree constructed from right part subarray
divided by the maximum number.

Construct the maximum tree by the given array and output the root node of this
tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:
      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].

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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    private TreeNode helper(int[] a, int start, int end) {
        if (start > end) {
            return null;
        }

        int maxIndex = start;
        for (int i = maxIndex + 1; i <= end; i++) {
            if (a[i] > a[maxIndex]) {
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(a[maxIndex]);

        root.left = helper(a, start, maxIndex - 1);
        root.right = helper(a, maxIndex + 1, end);

        return root;
    }
}
// 107 / 107 test cases passed.
// Runtime: 7 ms


