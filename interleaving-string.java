/*

97. Interleaving String
https://leetcode.com/problems/interleaving-string/description/


Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false

*/


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        final int n = s1.length();
        final int m = s2.length();
        
        boolean[] cur = new boolean[m + 1];
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                if (i == n && j == m) {
                    cur[j] = true;
                } else if (i == n) {
                    cur[j] = s2.charAt(j) == s3.charAt(i + j) && cur[j + 1];
                } else if (j == m) {
                    cur[j] = s1.charAt(i) == s3.charAt(i + j) && cur[j];
                } else {
                    cur[j] = (s1.charAt(i) == s3.charAt(i + j) && cur[j]) ||
                        (s2.charAt(j) == s3.charAt(i + j) && cur[j + 1]);
                }
            }
        }
        return cur[0];
    }
}
// 101 / 101 test cases passed.
// Runtime: 5 ms


