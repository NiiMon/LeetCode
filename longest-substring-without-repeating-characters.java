/*

3. Longest Substring Without Repeating Characters
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/


Given a string, find the length of the longest substring without repeating
characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the
answer must be a substring, "pwke" is a subsequence and not a substring.

*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                for (int j = start; j < map.get(c); j++) {
                    map.remove(s.charAt(j));
                }
                start = map.get(c) + 1;
            }
            map.put(c, i);
            maxLen = Math.max(maxLen, i - start + 1);
        }
        
        return maxLen;
    }
}


// optimize with int array
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int[] map = new int[256];
        
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            start = Math.max(start, map[s.charAt(i)]);
            maxLen = Math.max(maxLen, i - start + 1);
            map[s.charAt(i)] = i + 1;
        }
        
        return maxLen;
    }
}

