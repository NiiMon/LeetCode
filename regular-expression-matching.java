/*

10. Regular Expression Matching
https://leetcode.com/problems/regular-expression-matching/description/


Given an input string (s) and a pattern (p), implement regular expression
matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:
s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like .
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
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by
repeating 'a' once, it becomes "aa".

Example 3:
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it
matches "aab".

Example 5:
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false

*/


// dp
class Solution {
    public boolean isMatch(String s, String p) {
        final int n = s.length();
        final int m = p.length();
        
        boolean[] cur = new boolean[m+1];   // i
        boolean[] pre = new boolean[m+1];   // i + 1
        
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                if (i == n && j == m) {
                    cur[j] = true;
                } else if (i == n) {
                    cur[j] = j + 1 < m && p.charAt(j+1) == '*' && cur[j+2];
                } else if (j == m) {
                    cur[j] = false;
                } else {
                    if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                        if (j == m - 1 || p.charAt(j+1) != '*') {
                            cur[j] = pre[j+1];
                        } else {
                            cur[j] = pre[j] || cur[j+2];
                        }
                    } else {
                        if (j == m - 1 || p.charAt(j+1) != '*') {
                            cur[j] = false;
                        } else {
                            cur[j] = cur[j+2];
                        }
                    }
                }
            }
            boolean[] tmp = cur;
            cur = pre;
            pre = tmp;
        }
        
        return pre[0];
    }
}
// 447 / 447 test cases passed.
// Runtime: 17 ms


