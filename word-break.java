/*

139. Word Break
https://leetcode.com/problems/word-break/description/

Given a non-empty string s and a dictionary wordDict containing a list of non-
empty words, determine if s can be segmented into a space-separated sequence of
one or more dictionary words.


Note:

The same word in the dictionary may be reused multiple times in the
segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: 
Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false


*/


// Graph DFS Bottom-Up
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        Set<String> dict = new HashSet<>(wordDict);
        Map<String, Boolean> saved = new HashMap<>();
        return dfs(s, s.length() - 1, dict, saved);
    }
    private boolean dfs(String s, int index, Set<String> dict, 
    	Map<String, Boolean> saved) {
        String cur = s.substring(0, index + 1);
        if (saved.containsKey(cur)) {
            return saved.get(cur);
        }
        
        boolean result = false;
        
        if (dict.contains(cur)) {
            result = true;
        }
        for (int p = 1; p <= index && !result; p++) {
            String last = s.substring(p, index + 1);
            result = dict.contains(last) && dfs(s, p - 1, dict, saved);
        }
        
        saved.put(cur, result);
        return result;
    }
}
// 36 / 36 test cases passed.
// Runtime: 10 ms


