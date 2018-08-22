/*

44. Wildcard Matching
https://leetcode.com/problems/wildcard-matching/description/

Given an input string (s) and a pattern (p), implement wildcard pattern matching
with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ?
or *.

Example 1:
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input:
s = "cb"
p = "?a"
Output: false
Explanation: 
'?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:
Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*'
matches the substring "dce".

Example 5:
Input:
s = "acdcb"
p = "a*c?b"
Output: false


*/

// dp
class Solution {
    public boolean isMatch(String s, String p) {
        final int n = s.length();
        final int m = p.length();
        
        boolean[] cur = new boolean[m + 1];
        boolean[] pre = new boolean[m + 1];
        
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                if (i == n && j == m) {
                    cur[j] = true;
                } else if (i == n) {
                    cur[j] = p.charAt(j) == '*' && cur[j + 1];
                } else if (j == m) {
                    cur[j] = false;
                } else {
                    if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
                        cur[j] = pre[j + 1];
                    } else if (p.charAt(j) == '*') {
                        cur[j] = pre[j] || cur[j + 1];
                    } else {
                        cur[j] = false;
                    }
                }
            }
            boolean[] tmp = pre;
            pre = cur;
            cur = tmp;
        }
        
        return pre[0];
    }
}
// 1808 / 1808 test cases passed.
// Runtime: 38 ms


