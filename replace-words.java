/*

648. Replace Words
https://leetcode.com/problems/replace-words/description/

In English, we have a concept called root, which can be followed by some other
words to form another longer word - let's call this word successor. For example,
the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to
replace all the successor in the sentence with the root forming it. If a
successor has many roots can form it, replace it with the root with the shortest
length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

*/

// Trie implementation
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        if (dict.isEmpty()) {
            return sentence;
        }
        
        Trie root = buildTrie(dict);
        List<String> result = new ArrayList<>();
        for (int i = 0, start = 0; i <= sentence.length(); i++) {
            if (i == sentence.length() || 
                (sentence.charAt(i) == ' ' && i > start)) {
                result.add(walkTrie(root, sentence.substring(start, i)));
                start = i + 1;
            }
        }
        
        return String.join(" ", result);
    }
    private String walkTrie(Trie root, String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (root._children[c - 'a'] == null) {
                return word;
            }
            root = root._children[c - 'a'];
            if (root._isWord) {
                return word.substring(0, i + 1);
            }
        }
        return word;
    }
    private Trie buildTrie(List<String> dict) {
        Trie root = new Trie(false);
        
        for (String word : dict) {
            Trie node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node._children[c - 'a'] == null) {
                    node._children[c - 'a'] = new Trie(false);
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
        boolean _isWord;
        Trie[] _children;
        public Trie(boolean w) {
            _isWord = w;
            _children = new Trie[26];
        }
    }
}
// 124 / 124 test cases passed.
// Runtime: 28 ms


