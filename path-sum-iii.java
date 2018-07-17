/*

437. Path Sum III
https://leetcode.com/problems/path-sum-iii/description/


You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go
downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000
to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11


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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs(root, sum)._num;
    }

    private Result dfs(TreeNode node, int sum) {
        List<Integer> pathSums = new ArrayList<>();
        // base case : null
        if (node == null) {
            return new Result(0, pathSums);
        }

        // general case : non-leaf node
        Result leftResult = dfs(node.left, sum);
        Result rightResult = dfs(node.right, sum);

        pathSums.addAll(leftResult._pathSums);
        pathSums.addAll(rightResult._pathSums);

        int num = leftResult._num + rightResult._num;
        pathSums.add(0);
        for (int i = 0; i < pathSums.size(); i++) {
            pathSums.set(i, pathSums.get(i) + node.val);
            if (pathSums.get(i) == sum) {
                num++;
            }
        }
        
        return new Result(num, pathSums);
    }

    class Result {
        int _num;
        List<Integer> _pathSums;
        public Result(int num, List<Integer> pathSums) {
            _num = num;
            _pathSums = pathSums;
        }
    }
}
// 126 / 126 test cases passed.
// Runtime: 77 ms


