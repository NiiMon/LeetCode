/*

131. Palindrome Partitioning
https://leetcode.com/problems/palindrome-partitioning/description/

Given a string s, partition s such that every substring of the partition is a
palindrome.

Return all possible palindrome partitioning of s.

Example:
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

*/


// Graph DFS Bottom-Up
class Solution {
    public List<List<String>> partition(String s) {
        if (s.length() == 0) {
            List<List<String>> empty = new ArrayList<>();
            empty.add(new ArrayList<>());
            return empty;
        }
        boolean[][] isPal = getPal(s);
        Map<Integer, List<List<String>>> saved = new HashMap<>();
        return dfs(s, s.length() - 1, isPal, saved);
    }
    private List<List<String>> dfs(String s, int end, boolean[][] isPal, 
        Map<Integer, List<List<String>>> saved) {
        if (saved.containsKey(end)) {
            return saved.get(end);
        }
        
        List<List<String>> result = new ArrayList<>();
        
        // get all answers, copy, then add me
        // a[0->i] is Pal
        if (isPal[0][end]) {
            List<String> ans = new ArrayList<>();
            ans.add(s.substring(0, end + 1));
            result.add(ans);
        }
        // a[p->i] is Pal, 1 <= p <= i
        for (int p = 1; p <= end; p++) {
            if (isPal[p][end]) {
                for (List<String> pre : dfs(s, p - 1, isPal, saved)) {
                    List<String> ans = new ArrayList<>(pre);
                    ans.add(s.substring(p, end + 1));
                    result.add(ans);
                }
            }
        }
        
        saved.put(end, result);
        return result;
    }
    private boolean[][] getPal(String s) {
        final int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    isPal[i][j] = true;
                } else if (i + 1 == j) {
                    isPal[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    isPal[i][j] = isPal[i+1][j-1] && 
                        s.charAt(i) == s.charAt(j);
                }
            }
        }
        return isPal;
    }
}
// 22 / 22 test cases passed.
// Runtime: 6 ms


// Graph iterative
class Solution {
    public List<List<String>> partition(String s) {
        if (s.length() == 0) {
            List<List<String>> empty = new ArrayList<>();
            empty.add(new ArrayList<>());
            return empty;
        }
        boolean[][] isPal = getPal(s);
        List<List<List<String>>> allResults = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            List<List<String>> result = new ArrayList<>();
            if (isPal[0][i]) {
                List<String> list = new ArrayList<>();
                list.add(s.substring(0, i + 1));
                result.add(list);
            }
            for (int p = 1; p <= i; p++) {
                if (isPal[p][i]) {
                    List<List<String>> preResult = allResults.get(p - 1);
                    for (List<String> pre : preResult) {
                        List<String> cur = new ArrayList<>(pre);
                        cur.add(s.substring(p, i + 1));
                        result.add(cur);
                    }
                }
            }
            allResults.add(result);
        }
        return allResults.get(s.length() - 1);
    }
    private boolean[][] getPal(String s) {
        final int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    isPal[i][j] = true;
                } else if (i + 1 == j) {
                    isPal[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    isPal[i][j] = isPal[i+1][j-1] && 
                        s.charAt(i) == s.charAt(j);
                }
            }
        }
        return isPal;
    }
}
// 22 / 22 test cases passed.
// Runtime: 7 ms


