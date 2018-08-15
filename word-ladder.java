/*

127. Word Ladder
https://leetcode.com/problems/word-ladder/description/

Given two words (beginWord and endWord), and a dictionary's word list, find the
length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.

Each transformed word must exist in the word list. Note that beginWord is not a
transformed word.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" 
-> "cog", return its length 5.


Example 2:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible
transformation.

*/

class Solution {
    public int ladderLength(String beginWord, String endWord, 
    	List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        Set<String> added = new HashSet<>();
        queue.add(beginWord);
        added.add(beginWord);
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
        
            while (size-- > 0) {
                String word = queue.remove();
                
                for (int i = 0; i < word.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String childWord = word.substring(0, i) + c 
                        + word.substring(i + 1);
                        if (dict.contains(childWord) &&
                         !added.contains(childWord)) {
                            if (childWord.equals(endWord)) {
                                return level + 1;
                            }
                            queue.add(childWord);
                            added.add(childWord);
                        }
                    }
                }
            }
            level++;
        }
        
        return 0;
    }
}
// 39 / 39 test cases passed.
// Runtime: 150 ms

