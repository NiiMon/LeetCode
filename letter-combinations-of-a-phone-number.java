/*

17. Letter Combinations of a Phone Number
https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

Given a string containing digits from 2-9 inclusive, return all possible letter
combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given
below. Note that 1 does not map to any letters.

[img]http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png[/img]

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
Although the above answer is in lexicographical order, your answer could be in
any order you want.


*/


// Graph DFS Bottom-Up
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        // digit to string
        String[] d2s = new String[]{
            "", "", "abc", "def",
             "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
        };
        Map<String, List<String>> saved = new HashMap<>();
        return dfs(digits, 0, d2s, saved);
    }
    private List<String> dfs(String digits, int index, String[] d2s,
                             Map<String, List<String>> saved) {
        List<String> allResults = new ArrayList<>();

        for (char c : d2s[digits.charAt(index) - '0'].toCharArray()) {
            String key = index + "," + c;
            if (saved.containsKey(key)) {
                allResults.addAll(saved.get(key));
            } else {
                List<String> result = new ArrayList<>();

                if (index == digits.length() - 1) {
                    // base case: the last digits
                    result.add(c + "");
                } else {
                    // general case
                    for (String sub : dfs(digits, index + 1, d2s, saved)) {
                        result.add(c + sub);
                    }
                }

                saved.put(key, result);
                allResults.addAll(result);
            }
        }

        return allResults;
    }

// 25 / 25 test cases passed.
// Runtime: 4 ms


