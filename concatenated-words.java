/*

472. Concatenated Words
https://leetcode.com/problems/concatenated-words/description/


Given a list of words (without duplicates), please write a program that returns
all concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at
least two shorter words in the given array.

Example:

Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat",
"ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: 
"catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.

*/


// Graph DFS Bottom-Up
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> allResults = new ArrayList<>();
        if (words.length == 0) {
            return allResults;
        }
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        Set<String> shorterWords = new HashSet<>();

        for (String word : words) {
            if (word.length() > 0 && dfs(word, word.length() - 1, shorterWords, 
                new int[word.length()]) >= 2) {
                allResults.add(word);
            }
            shorterWords.add(word);
        }

        return allResults;
    }
    private int dfs(String s, int end, Set<String> shorterWords, int[] saved) {
        if (saved[end] != 0) {
            return saved[end];
        }

        int count = 0;

        // f(a[0->end])
        if (shorterWords.contains(s.substring(0, end + 1))) {
            count++;
        }
        // f(a[p->end]), 1 <= p <= i
        for (int p = 1; p <= end; p++) {
            if (shorterWords.contains(s.substring(p, end + 1))) {
                int pre = dfs(s, p - 1, shorterWords, saved);
                if (pre > 0) {
                    count += pre + 1;
                    break;
                }
            }
        }

        if (count == 0) {
            count--;
        }
        saved[end] = count;
        return count;
    }
}
// 44 / 44 test cases passed.
// Runtime: 142 ms

