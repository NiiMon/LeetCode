/*

720. Longest Word in Dictionary
https://leetcode.com/problems/longest-word-in-dictionary/description/

Given a list of strings words representing an English Dictionary, find the
longest word in words that can be built one character at a time by other words
in words. If there is more than one possible answer, return the longest word
with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and
"worl".

Example 2:
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary.
However, "apple" is lexicographically smaller than "apply".

Note:
All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].


*/

// Set implementation
class Solution {
    public String longestWord(String[] words) {
        Set<String> dict = new HashSet<>();
        for (String word : words) {
            dict.add(word);
        }
        String result = "";
        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                if (i == word.length()) {
                    if (word.length() > result.length() ||
                        word.length() == result.length() && 
                        word.compareTo(result) < 0) {
                        result = word;
                    }
                } else {
                    if (!dict.contains(word.substring(0, i + 1))) {
                        break;
                    }
                }
            }
        }
        return result;
    }
}
// 57 / 57 test cases passed.
// Runtime: 39 ms



// Trie implementation
class Solution {
    public String longestWord(String[] words) {
        TrieNode trie = buildTrie(words);

        String[] answer = new String[]{""};
        walkTrie(trie, new StringBuilder(), answer, 0);

        return answer[0];
    }
    private void walkTrie(TrieNode node, StringBuilder path, String[] max, int depth) {
        for (TrieNode child : node._children) {
            if (child != null && child._isWord) {
                // 1. op at node
                path.append(child._letter);
                
                if (path.length() > max[0].length()) {
                    max[0] = path.toString();
                }

                // 2. op at leaf
                // do nothing

                // 3. go down to children
                walkTrie(child, path, max, depth + 1);

                // 4. go up to parent
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode('0');
        for (String word : words) {
            dfs(root, word, 0);
        }
        return root;
    }
    private void dfs(TrieNode node, String word, int index) {
        // 1. op at node
        char letter = word.charAt(index);
        if (node._children[letter - 'a'] == null) {
            node._children[letter - 'a'] = new TrieNode(letter);
        }
        node = node._children[letter - 'a'];

        // 2. op at leaf
        if (index == word.length() - 1) {
            node._isWord = true;
            return;
        }

        // 3. go down to children
        dfs(node, word, index + 1);

        // 4. go up to parent
        // do nothing
    }
    class TrieNode {
        char _letter;
        boolean _isWord;
        TrieNode[] _children;
        public TrieNode(char letter) {
            _letter = letter;
            _isWord = false;
            _children = new TrieNode[26];
        }
    }
}
// 57 / 57 test cases passed.
// Status: Accepted
// Runtime: 9 ms
// Memory Usage: 28.6 MB
