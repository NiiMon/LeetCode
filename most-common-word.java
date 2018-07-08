/*

819. Most Common Word
https://leetcode.com/problems/most-common-word/description/


Given a paragraph and a list of banned words, return the most frequent word
that is not in the list of banned words.  It is guaranteed there is at least
one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of
punctuation.  Words in the paragraph are not case sensitive.  The answer is in
lowercase.

Example:
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation:  "hit" occurs 3 times, but it is a banned word. "ball" occurs
twice (and no other word does), so it is the most frequent non-banned word in
the paragraph. Note that words in the paragraph are not case sensitive, that
punctuation is ignored (even if adjacent to words, such as "ball,"), and that
"hit" isn't the answer even though it occurs more because it is banned.

Note:
1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in 
paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
Different words in paragraph are always separated by a space.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.

*/

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();

        // prepare ban set for easy lookup
        Set<String> ban = new HashSet<>();
        for (String word : banned) {
            ban.add(word);
        }

        List<String> list = new ArrayList<>();
        // i == 0
        int start = isLetter(paragraph.charAt(0)) ? 0 : 1;

        // 1 <= i <= n
        // producing words list
        for (int i = 1; i <= paragraph.length(); i++) {
            if (i == paragraph.length() || !isLetter(paragraph.charAt(i))) {
                if (isLetter(paragraph.charAt(i - 1))) {
                    list.add(paragraph.substring(start, i));
                }
                start = i + 1;
            }
        }

        String result = "";
        int max = 0;
        Map<String, Integer> map = new HashMap<>();

        // count and record most-common-word
        for (String word : list) {
            if (!ban.contains(word)) {
                if (!map.containsKey(word)) {
                    map.put(word, 0);
                }
                map.put(word, map.get(word) + 1);
                if (map.get(word) > max) {
                    max = map.get(word);
                    result = word;
                }
            }
        }

        return result;
    }
    private boolean isLetter(char c) {
        return c >= 'a' && c <= 'z' ||
                c >= 'A' && c <= 'Z';
    }
}

// 46 / 46 test cases passed.
// Runtime: 10 ms

