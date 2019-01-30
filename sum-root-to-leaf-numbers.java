/*

129. Sum Root to Leaf Numbers
https://leetcode.com/problems/sum-root-to-leaf-numbers/description/


Given a binary tree containing digits from 0-9 only, each root-to-leaf path
could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.


Example:
Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.

Example 2:
Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

*/


// dfs top-down
class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] result = new int[1];
        dfs(root, 0, result);
        return result[0];
    }
    private void dfs(TreeNode node, int sum, int[] result) {
        // 1. op at node
        sum = sum * 10 + node.val;

        // 2. op at leaf
        if (node.left == null && node.right == null) {
            result[0] += sum;
        }

        // 3. go down to children
        if (node.left != null) {
            dfs(node.left, sum, result);
        }
        if (node.right != null) {
            dfs(node.right, sum, result);
        }

        // 4. go up to parent
        // do nothing
    }
}

// 110 / 110 test cases passed.
// Runtime: 0 ms


