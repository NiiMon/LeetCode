/*

207. Course Schedule
https://leetcode.com/problems/course-schedule/

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to
first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it
possible for you to finish all courses?

Example 1:
Input: 2, [[1,0]] 
Output: true
Explanation: 
There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: 
There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you
should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency
matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

*/


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pre : prerequisites) {
            if (!graph.containsKey(pre[1])) {
                graph.put(pre[1], new ArrayList<>());
            }
            graph.get(pre[1]).add(pre[0]);
        }
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                boolean[] hasCycle = new boolean[1];
                dfs(graph, visited, i, new HashSet<>(), hasCycle);
                if (hasCycle[0]) {
                    return false;
                }
            }
        }
        return true;
    }
    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited,
                     int n, Set<Integer> path, boolean[] hasCycle) {
        if (!path.add(n)) {
            hasCycle[0] = true;
            return;
        }

        if (graph.containsKey(n)) {
            for (int child : graph.get(n)) {
                if (!visited[child]) {
                    dfs(graph, visited, child, path, hasCycle);
                }
            }
        }

        visited[n] = true;
    }
}
// 42 / 42 test cases passed.
// Status: Accepted
// Runtime: 8 ms
// Memory Usage: 45.6 MB
