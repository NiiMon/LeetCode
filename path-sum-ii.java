/*

113. Path Sum II
https://leetcode.com/problems/path-sum-ii/description/


Given a binary tree and a sum, find all root-to-leaf paths where each path's sum
equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]


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

// dfs bottom-up
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        // base case: leaf nodes
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                List<Integer> list = new LinkedList<>();
                list.add(root.val);
                result.add(list);
            }
            return result;
        }
        
        // general case: non-leaf nodes
        result.addAll(root.left == null ? new ArrayList<>() : 
                                        pathSum(root.left, sum - root.val));
        result.addAll(root.right == null ? new ArrayList<>() : 
                                        pathSum(root.right, sum - root.val));
        
        for (List<Integer> path : result) {
            path.add(0, root.val);
        }
        
        return result;
    }
}
// 114 / 114 test cases passed.
// Runtime: 4 ms



// dfs top-down
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList();
        if (root == null) {
            return result;
        }
        Stack<Integer> path = new Stack<>();
        dfs(root, sum, path, result);
        return result;
    }
    private void dfs(TreeNode node, int sum, Stack<Integer> path, List<List<Integer>> result) {
        // 1. op at node
        path.push(node.val);

        // 2. op at leaf node
        if (node.left == null && node.right == null) {
            if (sum == node.val) {
                result.add(new ArrayList<>(path));
            }
        }

        // 3. go down to children
        if (node.left != null) {
            dfs(node.left, sum - node.val, path, result);
        }
        if (node.right != null) {
            dfs(node.right, sum - node.val, path, result);
        }

        // 4. go up to parent
        path.pop();
    }
}
// 114 / 114 test cases passed.
// Runtime: 2 ms


