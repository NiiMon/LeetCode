/*

22. Generate Parentheses
https://leetcode.com/problems/generate-parentheses/description/


Given n pairs of parentheses, write a function to generate all combinations of
well-formed parentheses.

For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/

/*
test cases:
-1
0
1
3
*/


// version 1
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        List<StringBuilder> helperResults = helper("", n, n);

        for (StringBuilder sb : helperResults) {
            result.add(sb.reverse().toString());
        }

        return result;
    }
    private List<StringBuilder> helper(String c, int left, int right) {
        List<StringBuilder> result = new ArrayList<>();

        // check if reach the bottom
        if (right == 0) {
            result.add(new StringBuilder(c));
        } else {
            // get all ans from left branch
            if (left > 0) {
                result.addAll(helper("(", left - 1, right));
            }

            // get all ans from right branch
            if (right > left) {
                result.addAll(helper(")", left, right - 1));
            }

            // add me
            for (StringBuilder sb : result) {
                sb.append(c);
            }
        }

        return result;
    }
}
// 8 / 8 test cases passed.
// Runtime: 4 ms



