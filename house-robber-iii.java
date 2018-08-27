/*

337. House Robber III
https://leetcode.com/problems/house-robber-iii/description/

The thief has found himself a new place for his thievery again. There is only
one entrance to this area, called the "root." Besides the root, each house has
one and only one parent house. After a tour, the smart thief realized that "all
houses in this place forms a binary tree". It will automatically contact the
police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting
the police.

Example 1:
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.


*/

// DFS Bottom-Up
class Solution {
    public int rob(TreeNode root) {
        return helper(root)._curMax;
    }
    private Result helper(TreeNode node) {
        if (node == null) {
            return new Result(0, 0);
        }
        
        Result leftResult = helper(node.left);
        Result rightResult = helper(node.right);
        
        int stealHere = leftResult._preMax + rightResult._preMax + node.val;
        int notHere = leftResult._curMax + rightResult._curMax;
        
        return new Result(Math.max(stealHere, notHere), notHere);
    }
    class Result {
        int _curMax;
        int _preMax;
        public Result(int curMax, int preMax) {
            _curMax = curMax;
            _preMax = preMax;
        }
    }
}
// 124 / 124 test cases passed.
// Runtime: 1 ms

