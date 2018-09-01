/*

737. Sentence Similarity II
https://leetcode.com/problems/sentence-similarity-ii/description/

Given two sentences words1, words2 (each represented as an array of strings),
and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine",
"drama", "talent"] are similar, if the similar word pairs are pairs = [["great",
"good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and
"good" are similar, and "fine" and "good" are similar, then "great" and "fine"
are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is
the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 =
["great"], words2 = ["great"], pairs = [] are similar, even though there are no
specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So
a sentence like words1 = ["great"] can never be similar to words2 =
["doubleplus","good"].

Note:
The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

*/


// Union Find
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, 
                                          String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }


        Map<String, Integer> map = new HashMap<>();
        int id = 0;
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], id++);
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], id++);
            }
        }
        for (int i = 0; i < words1.length; i++) {
            if (!map.containsKey(words1[i])) {
                map.put(words1[i], id++);
            }
            if (!map.containsKey(words2[i])) {
                map.put(words2[i], id++);
            }
        }

        UnionFind uf = new UnionFind(map.size());
        for (String[] pair : pairs) {
            uf.union(map.get(pair[0]), map.get(pair[1]));
        }


        for (int i = 0; i < words1.length; i++) {
            if (!uf.connected(map.get(words1[i]), map.get(words2[i]))) {
                return false;
            }
        }

        return true;
    }
    class UnionFind {
        final int _size;
        int[] _parent;
        int[] _rank;
        int _groups;

        public UnionFind(int size) {
            _size = size;
            _parent = new int[size];
            _rank = new int[size];
            for (int i = 0; i < size; i++) {
                _parent[i] = i;
                _rank[i] = 1;
            }
            _groups = size;
        }

        public int find(int p) {
            // find parent
            int root = _parent[p];
            while (_parent[root] != root) {
                root = _parent[root];
            }
            // path compression
            while (_parent[p] != root) {
                int next = _parent[p];
                _parent[p] = root;
                p = next;
            }

            return root;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            if (connected(p, q)) {
                return;
            }

            int root1 = find(p);
            int root2 = find(q);

            if (_rank[root1] > _rank[root2]) {
                _parent[root2] = root1;
            } else if (_rank[root1] < _rank[root2]) {
                _parent[root1] = root2;
            } else {
                _parent[root2] = root1;
                _rank[root1]++;
            }

            _groups--;
        }
    }
}
// 117 / 117 test cases passed.
// Runtime: 30 ms

