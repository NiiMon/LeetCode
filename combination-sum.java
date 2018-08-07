/*

39. Combination Sum
https://leetcode.com/problems/combination-sum/description/

Given a set of candidate numbers (candidates) (without duplicates) and a target
number (target), find all unique combinations in candidates where the candidate
numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of
times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

*/


// DFS Top-Down
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates.length == 0) {
            return results;
        }

        Stack<Integer> path = new Stack<>();
        dfs(candidates.length - 1, target, candidates, path, results);

        return results;
    }

    private void dfs(int index, int target, int[] candidates, Stack<Integer>
    path, List<List<Integer>> results) {
        
        // op at node
        if (index >= 0 && target == 0) {
            results.add(new ArrayList(path));
        }

        // go down to children
        // left child: use candidates[index] once
        if (index >= 0 && target - candidates[index] >= 0) {
            path.push(candidates[index]);
            dfs(index, target - candidates[index], candidates, path, results);
            path.pop();
        }

        // right child: discard candidates[index]
        if (index >= 1 && target > 0) {
            dfs(index - 1, target, candidates, path, results);
        }
    }
}
// 168 / 168 test cases passed.
// Runtime: 13 ms


