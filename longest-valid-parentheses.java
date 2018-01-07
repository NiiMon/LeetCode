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

// stack solution
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


// array solution
// use an array to record the starting index of current end.
class Solution {
    public int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }
        
        int[] array = new int[s.length() + 1];
        int len = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' &&
                i - 1 >= 0 && s.charAt(i - 1) == '(') {
                // start[i] = start[i - 2]
                array[i + 1] = array[i - 1];
                len = Math.max(len, i - array[i + 1] + 1);
            } else if (s.charAt(i) == ')' &&
                       i - 1 >= 0 && s.charAt(i - 1) == ')' &&
                       array[i] - 1 >= 0 && s.charAt(array[i] - 1) == '(') {
                // start[i] = start[start[i - 1] - 2]
                array[i + 1] = array[array[i] - 1];
                len = Math.max(len, i - array[i + 1] + 1);
            } else {
                // start[i] = i + 1;
                array[i + 1] = i + 1;
            }
        }
        
        return len;
    }
}

