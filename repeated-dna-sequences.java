/*

187. Repeated DNA Sequences
https://leetcode.com/problems/repeated-dna-sequences/description/


All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that
occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

*/

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        
        if (s.length() < 11) {
            return result;
        }
        
        Set<String> sequences = new HashSet<>();
        Set<String> repeat = new HashSet<>();
        
        for (int i = 0; i + 10 <= s.length(); i++) {
            String cur = s.substring(i, i + 10);
            if (!sequences.add(cur)) {
                repeat.add(cur);
            }
        }
        
        for (String ans : repeat) {
            result.add(ans);
        }
        
        return result;
    }
}

