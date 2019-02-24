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
        if (s.length() == 0) {
            return 0;
        }

        int pre1 = 1;
        int pre2 = 1;

        for (int i = 0; i < s.length(); i++) {
            int cur = 0;
            int oneDigit = s.charAt(i) - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                cur += pre1;
            }
            if (i > 0) {
                int twoDigit = (s.charAt(i - 1) - '0') * 10 + oneDigit;
                if (twoDigit >= 10 && twoDigit <= 26) {
                    cur += pre2;
                }
            }
            pre2 = pre1;
            pre1 = cur;
        }

        return pre1;
    }
}
// 258 / 258 test cases passed.
// Status: Accepted
// Runtime: 0 ms
// Memory Usage: 33.5 MB

