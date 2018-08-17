/*

115. Distinct Subsequences
https://leetcode.com/problems/distinct-subsequences/description/

Given a string S and a string T, count the number of distinct subsequences of S
which equals T.

A subsequence of a string is a new string which is formed from the original
string by deleting some (can be none) of the characters without disturbing the
relative positions of the remaining characters. (ie, "ACE" is a subsequence of
"ABCDE" while "AEC" is not).

Example 1:
Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^

Example 2:
Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^

*/


// dp 
class Solution {
    public int numDistinct(String s, String t) {
        final int n = s.length();
        final int m = t.length();
        
        int[] cur = new int[m + 1]; // i
        int[] pre = new int[m + 1]; // i + 1
        
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                if (i == n && j == m) {
                    cur[j] = 1;
                } else if (i == n) {
                    cur[j] = 0;
                } else if (j == m) {
                    cur[j] = pre[j];
                } else {
                    if (s.charAt(i) == t.charAt(j)) {
                        cur[j] = pre[j] + pre[j + 1];
                    } else {
                        cur[j] = pre[j];
                    }
                }
            }
            int[] tmp = cur;
            cur = pre;
            pre = tmp;
        }
        
        return pre[0];
    }
}
// 63 / 63 test cases passed.
// Runtime: 13 ms


