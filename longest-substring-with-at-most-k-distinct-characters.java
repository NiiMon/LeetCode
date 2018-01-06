/*

340. Longest Substring with At Most K Distinct Characters
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/


Given a string, find the length of the longest substring T that contains at
most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.

*/

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k must be greater or equal to 0.");
        }
        
        if (k == 0) {
            return 0;
        }
        
        if (s.length() <= k) {
            return s.length();
        }
        
        int[] freq = new int[256];
        int start = 0;
        int size = 0;
        int len = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // for new char, adjust size if needed
            if (freq[c] == 0) {
                while (size == k) {
                    freq[s.charAt(start)]--;
                    if (freq[s.charAt(start)] == 0) {
                        size--;
                    }
                    start++;
                }
            }
            
            // update freq
            freq[c]++;
            if (freq[c] == 1) {
                size++;
            }
            
            // update len
            len = Math.max(len, i - start + 1);
        }
        
        return len;
    }
}

