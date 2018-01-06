/*

76. Minimum Window Substring
https://leetcode.com/problems/minimum-window-substring/description/


Given a string S and a string T, find the minimum window in S which will
contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note: If there is no such window in S that covers all characters in T, return
the empty string "".

If there are multiple such windows, you are guaranteed that there will always
be only one unique minimum window in S.

*/

class Solution {
    public String minWindow(String s, String t) {
        String result = "";
        
        if (s.length() == 0 || t.length() == 0) {
            return result;
        }
        
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        int need = t.length();
        
        // pre-processing
        for (char c : t.toCharArray()) {
            mapT[c]++;
        }
        
        // processing
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (mapT[s.charAt(i)] == 0) {
                continue;
            }
            
            // update mapS
            mapS[s.charAt(i)]++;
            
            // update need
            if (mapS[s.charAt(i)] <= mapT[s.charAt(i)]) {
                need--;
            }
            
            if (need == 0) {
                // move start pointer to its valid position
                while (!(mapT[s.charAt(start)] > 0 && 
                        mapS[s.charAt(start)] == mapT[s.charAt(start)])) {
                    if (mapT[s.charAt(start)] > 0) {
                        mapS[s.charAt(start)]--;
                    }
                    start++;
                }
                
                if (result.equals("") || i - start + 1 < result.length()) {
                    result = s.substring(start, i + 1);
                }
            }
        }
        
        return result;
    }
}

