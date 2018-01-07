/*

32. Longest Valid Parentheses
https://leetcode.com/problems/longest-valid-parentheses/description/


Given a string containing just the characters '(' and ')', find the length of
the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length =
2.

Another example is ")()())", where the longest valid parentheses substring is
"()()", which has length = 4.

*/


class Solution {
    public int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        int len = 0;
        
        for (char c : s.toCharArray()) {
            if (c == ')') {
                int newLen = 0;
                
                if (stack.size() > 0 && stack.peek() % 2 == 0) {
                    newLen += stack.pop();
                }
                
                if (stack.size() > 0 && stack.peek() == 1) {
                    newLen += stack.pop() + 1;
                    if (stack.size() > 0 && stack.peek() % 2 == 0) {
                        newLen += stack.pop();
                    }
                    // push newLen to stack only when it finds a closing '('
                    // example: "(()))()"
                    stack.push(newLen);
                }
                
                len = Math.max(len, newLen);
            } else {
                stack.push(1);  // c == '('
            }
        }
        
        return len;
    }
}

