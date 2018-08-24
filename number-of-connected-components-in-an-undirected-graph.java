/*

323. Number of Connected Components in an Undirected Graph
https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/


Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge
is a pair of nodes), write a function to find the number of connected components
in an undirected graph.


Example 1:
Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2


Example 2:
Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are
undirected, [0, 1] is the same as [1, 0] and thus will not appear together in
edges.


*/

class Solution {
    public int countComponents(int n, int[][] edges) {
        Node[] graph = deserialize(n, edges);
        return walk(graph);
    }
    private int walk(Node[] graph) {
        boolean[] visited = new boolean[graph.length];
        int group = 0;
        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                group++;
                dfs(node, graph, visited);
            }
        }
        return group;
    }
    private void dfs(int node, Node[] graph, boolean[] visited) {
        visited[node] = true;
        for (int next : graph[node]._neighbor) {
            if (!visited[next]) {
                dfs(next, graph, visited);
            }
        }
    }
    private Node[] deserialize(int n, int[][] edges) {
        Node[] graph = new Node[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Node(i);
        }
        for (int[] edge : edges) {
            graph[edge[0]]._neighbor.add(edge[1]);
            graph[edge[1]]._neighbor.add(edge[0]);
        }
        return graph;
    }
    class Node {
        int _id;
        List<Integer> _neighbor;
        public Node(int id) {
            _id = id;
            _neighbor = new ArrayList<>();
        }
    }
}
// 45 / 45 test cases passed.
// Runtime: 4 ms


