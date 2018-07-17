/*

151. Reverse Words in a String
https://leetcode.com/problems/reverse-words-in-a-string/description/


Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.


Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.

Could the input string contain leading or trailing spaces? 
Yes. However, your reversed string should not contain leading or trailing 
spaces.

How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

*/

public class Solution {
    public String reverseWords(String s) {
        // empty input
        if (s.length() == 0) {
            return s;
        }
        
        List<String> list = new LinkedList<>();
        
        // i == 0
        int start = s.charAt(0) == ' ' ? 1 : 0;
        
        // 1 <= i <= n
        for (int i = 1; i <= s.length(); i++) {
            if (i == s.length() || s.charAt(i) == ' ') {
                if (s.charAt(i - 1) != ' ') {
                    list.add(0, s.substring(start, i));
                }
                start = i + 1;
            }
        }
        
        return String.join(" ", list);
    }
}
// 23 / 23 test cases passed.
// Runtime: 5 ms


