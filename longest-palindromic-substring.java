/*

5. Longest Palindromic Substring
https://leetcode.com/problems/longest-palindromic-substring/description/


Given a string s, find the longest palindromic substring in s. You may assume
that the maximum length of s is 1000.

Example:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example:

Input: "cbbd"
Output: "bb"

*/

// Expand Around Center
class Solution {
    public String longestPalindrome(String s) {
        String result = "";
        
        for (int i = 0 ; i < s.length(); i++) {
            String s1 = helper(s, i, i);
            result = s1.length() > result.length() ? s1 : result;
            
            String s2 = helper(s, i, i + 1);
            result = s2.length() > result.length() ? s2 : result;
        }
        
        return result;
    }
    private String helper(String s, int l, int r) {
        String result = "";
        
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        l++;
        r--;
        
        if (r - l + 1 > 0) {
            result = s.substring(l, r + 1);
        }
        
        return result;
    } 
}

