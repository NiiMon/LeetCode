/*

205. Isomorphic Strings
https://leetcode.com/problems/isomorphic-strings/description/


Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while
preserving the order of characters. No two characters may map to the same
character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.

*/


// hashmap version
class Solution {
    public boolean isIsomorphic(String s, String t) {
        return helper(s, t) && helper(t, s);
    }
    private boolean helper(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char k = s.charAt(i);
            char v = t.charAt(i);
            if (map.containsKey(k)) {
                if (map.get(k) != v) {
                    return false;
                }
            } else {
                map.put(k, v);
            }
        }
        
        return true;
    }
}
// 30 / 30 test cases passed.
// Runtime: 10 ms



// int array version
class Solution {
    public boolean isIsomorphic(String s, String t) {
        return helper(s, t) && helper(t, s);
    }
    private boolean helper(String s, String t) {
        // there are 256 characters in ASCII
        // null in ASCII has value of 0
        int[] map = new int[256];
        
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)] == 0) {
                map[s.charAt(i)] = t.charAt(i);
            } else if (map[s.charAt(i)] != t.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}
// 30 / 30 test cases passed.
// Runtime: 6 ms


