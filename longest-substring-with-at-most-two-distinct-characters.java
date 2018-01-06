/*

159. Longest Substring with At Most Two Distinct Characters
https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/


Given a string, find the length of the longest substring T that contains at
most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.

*/

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() == 0) {
            return 0;
        }
        
        int len = 1;
        int s1 = 0;
        int s2 = 0;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                if (s2 >= 1 && s.charAt(i) != s.charAt(s2 - 1)) {
                    s1 = s2;
                }
                s2 = i;
            }
            len = Math.max(len, i - s1 + 1);
        }
        
        return len;
    }
}

