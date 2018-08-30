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
    final private static char ROOT = '0';
    public String longestWord(String[] words) {
        Trie root = buildTrie(words);
        return walkTrie(root);
    }
    private String walkTrie(Trie root) {
        String[] result = new String[]{""};
        dfs(root, new StringBuilder(), -1, result);
        return result[0];
    }
    private void dfs(Trie node, StringBuilder sb, int depth, String[] result) {
        if (node._char != ROOT) {
            // put char into sb
            if (depth == sb.length()) {
                sb.append(node._char);
            } else {
                sb.setCharAt(depth, node._char);
            }

            // if isWord
            if (depth + 1 > result[0].length()) {
                result[0] = sb.substring(0, depth + 1);
            }
        }
        
        // go down to children
        for (int i = 0; i < 26; i++) {
            if (node._children[i] != null && node._children[i]._isWord) {
                dfs(node._children[i], sb, depth + 1, result);
            }
        }

        // go up to parent
        // do nothing
    }
    private Trie buildTrie(String[] words) {
        Trie root = new Trie(ROOT, true);

        for (String word : words) {
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node._children[c - 'a'] == null) {
                    Trie child = new Trie(c, false);
                    node._children[c - 'a'] = child;
                }
                node = node._children[c - 'a'];
                if (i == word.length() - 1) {
                    node._isWord = true;
                }
            }
        }

        return root;
    }
    class Trie {
        char _char;
        boolean _isWord;
        Trie[] _children;
        public Trie(char c, boolean w) {
            _char = c;
            _isWord = w;
            _children = new Trie[26];
        }
    }
}
// 57 / 57 test cases passed.
// Runtime: 17 ms

