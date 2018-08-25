/*

210. Course Schedule II
https://leetcode.com/problems/course-schedule-ii/description/


There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to
first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the
ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it
is impossible to finish all courses, return an empty array.

Example 1:
Input: 2, [[1,0]] 
Output: [0,1]
Explanation: 
There are a total of 2 courses to take. To take course 1 you should have
finished course 0. So the correct course order is [0,1] .

Example 2:
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: 
There are a total of 4 courses to take. To take course 3 you should have
finished both courses 1 and 2. Both courses 1 and 2 should be taken after you
finished course 0. So one correct course order is [0,1,2,3]. Another correct
ordering is [0,2,1,3].

Note:

The input prerequisites is a graph represented by a list of edges, not adjacency
matrices. Read more about how a graph is represented.

You may assume that there are no duplicate edges in the input prerequisites.


*/


// boolean array for path
// judge taken courses first
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Node[] graph = deserialize(numCourses, prerequisites);
        return walk(graph);
    }
    private int[] walk(Node[] graph) {
        boolean[] taken = new boolean[graph.length];
        boolean[] path = new boolean[graph.length];
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            try {
                dfs(i, graph, taken, order, path);
            } catch (RuntimeException e) {
                return new int[]{};
            }
        }
        int[] result = new int[graph.length];
        for (int i = 0; i < order.size(); i++) {
            result[i] = order.get(i);
        }
        return result;
    }
    private void dfs(int node, Node[] graph, boolean[] taken, 
        List<Integer> order, boolean[] path) {
        if (taken[node]) {
            return;
        }
        if (path[node]) {
            throw new RuntimeException();
        }
        path[node] = true;
        // prerequisites
        for (int pre : graph[node]._preList) {
            dfs(pre, graph, taken, order, path);
        }
        
        // itself
        taken[node] = true;
        order.add(node);
    }
    private Node[] deserialize(int n, int[][] prerequisites) {
        Node[] graph = new Node[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Node(i);
        }
        for (int[] pre : prerequisites) {
            graph[pre[0]]._preList.add(pre[1]);
        }
        return graph;
    }
    class Node {
        final int _id;
        List<Integer> _preList;
        public Node(int id) {
            _id = id;
            _preList = new ArrayList<>();
        }
    }
}
// 44 / 44 test cases passed.
// Runtime: 11 ms



// Set<Integer> for path
// judge cycle first
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Node[] graph = deserialize(numCourses, prerequisites);
        return walk(graph);
    }
    private int[] walk(Node[] graph) {
        Set<Integer> taken = new HashSet<>();
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            try {
                dfs(i, graph, taken, order, new HashSet<>());
            } catch (HasCycleException e) {
                return new int[]{};
            }
        }
        int[] result = new int[graph.length];
        for (int i = 0; i < order.size(); i++) {
            result[i] = order.get(i);
        }
        return result;
    }
    private void dfs(int node, Node[] graph, Set<Integer> taken, 
        List<Integer> order, Set<Integer> path) throws HasCycleException {
        if (path.contains(node)) {
            throw new HasCycleException();
        }
        if (taken.contains(node)) {
            return;
        }
        path.add(node);
        // prerequisites
        for (int pre : graph[node]._preList) {
            dfs(pre, graph, taken, order, path);
        }
        
        // itself
        taken.add(node);
        order.add(node);
        path.remove(node);  // can be removed if judge taken first in dfs
    }
    private Node[] deserialize(int n, int[][] prerequisites) {
        Node[] graph = new Node[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Node(i);
        }
        for (int[] pre : prerequisites) {
            graph[pre[0]]._preList.add(pre[1]);
        }
        return graph;
    }
    class Node {
        final int _id;
        List<Integer> _preList;
        public Node(int id) {
            _id = id;
            _preList = new ArrayList<>();
        }
    }
    class HasCycleException extends Exception {}
}
// 44 / 44 test cases passed.
// Runtime: 11 ms



