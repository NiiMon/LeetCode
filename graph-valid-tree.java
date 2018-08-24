/*

261. Graph Valid Tree
https://leetcode.com/problems/graph-valid-tree/description/


Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is
a pair of nodes), write a function to check whether these edges make up a valid
tree.

Example 1:
Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true

Example 2:
Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]] 
Output: false

Note: you can assume that no duplicate edges will appear in edges. Since all
edges are undirected, [0,1] is the same as [1,0] and thus will not appear
together in edges.


*/

class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        Node[] graph = deserialize(n, edges);
        return walk(graph);
    }
    private Node[] deserialize(int n, int[][] edges) {
        Node[] graph = new Node[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Node(i);
        }
        for (int[] edge : edges) {
            graph[edge[0]]._neighborIds.add(edge[1]);
            graph[edge[1]]._neighborIds.add(edge[0]);
        }
        return graph;
    }
    private boolean walk(Node[] graph) {
        boolean[] visited = new boolean[graph.length];
        dfs(graph, 0, visited);
        for(boolean val : visited) {
            if (!val) {
                return false;
            }
        }
        return true;
    }
    private void dfs(Node[] graph, int id, boolean[] visited) {
        if (visited[id]) {
            return;
        }
        visited[id] = true;

        for (int neighborId : graph[id]._neighborIds) {
            dfs(graph, neighborId, visited);
        }
    }
    class Node {
        final int _id;
        List<Integer> _neighborIds = new ArrayList<>();
        public Node (int id) {
            _id = id;
        }
    }
}
// 44 / 44 test cases passed.
// Runtime: 1 ms

