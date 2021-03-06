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

// dfs bottom-up
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q)._LCA;
    }
    private Result dfs(TreeNode node, TreeNode p, TreeNode q) {
        // base case: null node
        if (node == null) {
            return new Result(null, false, false);
        }

        // general case: all nodes
        Result leftResult = dfs(node.left, p, q);
        Result rightResult = dfs(node.right, p, q);

        boolean hasP = leftResult._hasP || rightResult._hasP || node == p;
        boolean hasQ = leftResult._hasQ || rightResult._hasQ || node == q;
        TreeNode LCA = leftResult._LCA != null ? leftResult._LCA :
                        rightResult._LCA != null ? rightResult._LCA :
                        hasP && hasQ ? node : null;
        
        return new Result(LCA, hasP, hasQ);
    }
    class Result {
        TreeNode _LCA;
        boolean _hasP;
        boolean _hasQ;
        public Result(TreeNode LCA, boolean hasP, boolean hasQ) {
            _LCA = LCA;
            _hasP = hasP;
            _hasQ = hasQ;
        }
    }
}
// 31 / 31 test cases passed.
// Runtime: 10 ms



// dfs top-down
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        List<TreeNode> pList = new ArrayList<>();
        List<TreeNode> qList = new ArrayList<>();
        Stack<TreeNode> path = new Stack<>();
        dfs(root, p, q, pList, qList, path);

        // process pList and qList to get result
        TreeNode result = null;
        int start = 0;
        int end = Math.min(pList.size(), qList.size()) - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (pList.get(mid) == qList.get(mid)) {
                result = pList.get(mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return result;
    }
    private void dfs(TreeNode node, TreeNode p, TreeNode q, List<TreeNode> pList, List<TreeNode> qList, Stack<TreeNode> path) {
        // 1. op at node
        path.push(node);

        // 2. op at target node
        if (node == p) {
            pList.addAll(path);
        }
        if (node == q) {
            qList.addAll(path);
        }

        // 3. go down to children
        if (node.left != null) {
            dfs(node.left, p, q, pList, qList, path);
        }
        if (node.right != null) {
            dfs(node.right, p, q, pList, qList, path);
        }

        // 4. go up to parent
        path.pop();
    }
}
// 31 / 31 test cases passed.
// Runtime: 12 ms


