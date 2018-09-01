/*

547. Friend Circles
https://leetcode.com/problems/friend-circles/description/

There are N students in a class. Some of them are friends, while some are not.
Their friendship is transitive in nature. For example, if A is a direct friend
of B, and B is a direct friend of C, then A is an indirect friend of C. And we
defined a friend circle is a group of students who are direct or indirect
friends.

Given a N*N matrix M representing the friend relationship between students in
the class. If M[i][j] = 1, then the ith and jth students are direct friends with
each other, otherwise not. And you have to output the total number of friend
circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend
circle. The 2nd student himself is in a friend circle. So return 2.

Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd
students are direct friends, so the 0th and 2nd students are indirect friends.
All of them are in the same friend circle, so return 1.

Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.

*/

// Undirected Graph DFS
class Solution {
    public int findCircleNum(int[][] M) {
        final int n = M.length;
        if (n == 0) {
            return 0;
        }
        
        boolean[] visited = new boolean[n];
        int group = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                group++;
                dfs(i, M, visited);
            }
        }
        
        return group;
    }
    private void dfs(int i, int[][] M, boolean[] visited) {
        visited[i] = true;
        
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                dfs(j, M, visited);
            }
        }
    }
}
// 113 / 113 test cases passed.
// Runtime: 6 ms



// Union Find
class Solution {
    public int findCircleNum(int[][] M) {
        if (M.length == 0 || M[0].length == 0) {
            return 0;
        }
        
        UnionFind uf = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        
        return uf._groups;
    }
    class UnionFind {
        private final int _size;
        private int[] _parent;
        private int[] _rank;
        private int _groups;
        
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
// 113 / 113 test cases passed.
// Runtime: 7 ms
