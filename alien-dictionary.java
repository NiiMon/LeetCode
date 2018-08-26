/*

269. Alien Dictionary
https://leetcode.com/problems/alien-dictionary/description/

There is a new alien language which uses the latin alphabet. However, the order
among letters are unknown to you. You receive a list of non-empty words from the
dictionary, where words are sorted lexicographically by the rules of this new
language. Derive the order of letters in this language.

Example 1:
Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
Output: "wertf"

Example 2:
Input:
[
  "z",
  "x"
]
Output: "zx"

Example 3:
Input:
[
  "z",
  "x",
  "z"
] 
Output: "" 
Explanation: The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the
given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

*/


public class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = deserialize(words);
        return walk(graph);
    }
    private String walk(Map<Character, Set<Character>> graph) {
        Set<Character> visited = new HashSet<>();
        Set<Character> path = new HashSet<>();
        StringBuilder order = new StringBuilder();

        for (char key : graph.keySet()) {
            try {
                dfs(key, graph, visited, path, order);
            } catch (RuntimeException re) {
                return "";
            }
        }

        return order.toString();
    }
    private void dfs(char node, Map<Character, Set<Character>> graph, 
                     Set<Character> visited, Set<Character> path, 
                     StringBuilder order) {
        if (visited.contains(node)) {
            return;
        }
        if (path.contains(node)) {
            throw new RuntimeException("cycle detected");
        }

        path.add(node);

        for (char pre : graph.get(node)) {
            dfs(pre, graph, visited, path, order);
        }

        visited.add(node);
        order.append(node);
    }
    private Map<Character, Set<Character>> deserialize(String[] words) {
        // vertex
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<>());
                }
            }
        }

        // edge
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < words[i].length() && j < words[i - 1].length();
                 j++) {
                char pre = words[i - 1].charAt(j);
                char cur = words[i].charAt(j);
                if (pre != cur) {
                    graph.get(cur).add(pre);
                    break;
                }
            }
        }
        return graph;
    }
}
// 117 / 117 test cases passed.
// Runtime: 4 ms


