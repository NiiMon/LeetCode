/*

132. Palindrome Partitioning II
https://leetcode.com/problems/palindrome-partitioning-ii/description/

Given a string s, partition s such that every substring of the partition is a
palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:
Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1
cut.

*/

// Graph DFS Bottom-Up
class Solution {
    public int minCut(String s) {
        if (s.length() == 0) {
            return 0;
        }
        boolean[][] isPal = getPal(s);
        int[] saved = new int[s.length()];
        Arrays.fill(saved, -1);
        return dfs(s, s.length() - 1, isPal, saved);
    }
    private int dfs(String s, int end, boolean[][] isPal, int[] saved) {
        if (saved[end] > -1) {
            return saved[end];
        }
        
        int cut = Integer.MAX_VALUE;
        
        // if (a[0->i] is Pal) : 0,
        if (isPal[0][end]) {
            cut = 0;
        }
        // if (a[p->i] is Pal) : f(a[0->p-1]) + 1, (1 <= p <= i)
        else {
            for (int p = 1; p <= end; p++) {
                if (isPal[p][end]){
                    cut = Math.min(cut, dfs(s, p - 1, isPal, saved) + 1);
                }
            }
        }
        
        saved[end] = cut;
        return cut;
    }
    private boolean[][] getPal(String s) {
        final int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    isPal[i][j] = true;
                } else if (i + 1 == j) {
                    isPal[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    isPal[i][j] = isPal[i+1][j-1] && 
                        s.charAt(i) == s.charAt(j);
                }
            }
        }
        return isPal;
    }
}
// 29 / 29 test cases passed.
// Runtime: 18 ms


