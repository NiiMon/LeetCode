/*

684. Redundant Connection
https://leetcode.com/problems/redundant-connection/description/

In this problem, a tree is an undirected graph that is connected and has no
cycles.

The given input is a graph that started as a tree with N nodes (with distinct
values 1, 2, ..., N), with one additional edge added. The added edge has two
different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a
pair [u, v] with u < v, that represents an undirected edge connecting nodes u
and v.

Return an edge that can be removed so that the resulting graph is a tree of N
nodes. If there are multiple answers, return the answer that occurs last in the
given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3

Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3

Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is
the size of the input array.

*/


// Union Find
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            int p = edge[0] - 1;
            int q = edge[1] - 1;
            if (uf.connected(p, q)) {
                return edge;
            }
            uf.union(p, q);
        }
        return new int[]{};
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

        public int getSize() {
            return _size;
        }

        public int getGroups() {
            return _groups;
        }

        public void union(int p, int q) {
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
// 39 / 39 test cases passed.
// Runtime: 3 ms

