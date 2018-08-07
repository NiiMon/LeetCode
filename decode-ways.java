/*

91. Decode Ways
https://leetcode.com/problems/decode-ways/description/

A message containing letters from A-Z is being encoded to numbers using the
following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits, determine the total number of
ways to decode it.

Example 1:
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

*/


class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        // number of decode ways at a[i - 1]
        int pre1 = 1;
        // number of decode ways at a[i - 2]
        int pre2 = 1;
        for (int i = 1; i < s.length(); i++) {
            int cur = 0;
            if (s.charAt(i) != '0') {
                cur += pre1;
            }
            int two = Integer.valueOf(s.substring(i - 1, i + 1));
            if (10 <= two && two <= 26) {
                cur += pre2;
            }
            // early exit
            if (cur == 0) {
                return 0;
            }
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
// 258 / 258 test cases passed.
// Runtime: 3 ms

