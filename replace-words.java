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
        TrieNode trie = buildTrie(dict);

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String[] result = new String[]{words[i]};
            walkTrie(trie, words[i], 0, new StringBuilder(), result);
            words[i] = result[0];
        }

        return String.join(" ", words);
    }
    private void walkTrie(TrieNode node, String word, int index, StringBuilder sb, String[] result) {
        // 1. op at node
        char letter = word.charAt(index);
        if (node._children[letter - 'a'] == null) {
            return;
        }
        sb.append(letter);
        node = node._children[letter - 'a'];

        if (node._isWord) {
            result[0] = sb.toString();
            return;
        }

        if (index == word.length() - 1) {
            return;
        }

        // 2. op at leaf
        // 3. go down to children
        walkTrie(node, word, index + 1, sb, result);
        
        // 4. go up to parent
        sb.deleteCharAt(sb.length() - 1);
    }
    private TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode('0');
        for (String word : dict) {
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
// 124 / 124 test cases passed.
// Status: Accepted
// Runtime: 13 ms
// Memory Usage: 41 MB


