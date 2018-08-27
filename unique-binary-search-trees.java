/*

96. Unique Binary Search Trees
https://leetcode.com/problems/unique-binary-search-trees/description/

Given n, how many structurally unique BST's (binary search trees) that store
values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


*/

// recursive: DFS Bottom-Up
class Solution {
    public int numTrees(int n) {
        if (n < 1) {
            return 1;
        }
        Integer[] saved = new Integer[n + 1];
        return dfs(n, saved);
    }
    private int dfs(int i, Integer[] saved) {
        if (saved[i] != null) {
            return saved[i];
        }
        
        int count = 0;
        
        if (i == 0) {
            count = 1;
        } else {
            for (int p = 1; p <= i; p++) {
                count += dfs(p - 1, saved) * dfs(i - p, saved);
            }
        }
        
        saved[i] = new Integer(count);
        return count;
    }
}
// 19 / 19 test cases passed.
// Runtime: 0 ms


// iterative: dp
class Solution {
    public int numTrees(int n) {
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 0; i <= n; i++) {
            for (int p = 1; p <= i; p++) {
                dp[i] += dp[p - 1] * dp[i - p];
            }
        }
        
        return dp[n];
    }
}
// 19 / 19 test cases passed.
// Runtime: 0 ms


