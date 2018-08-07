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



// graph DFS Bottom-Up
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return new ArrayList<>();
        }

        Map<Key, List<List<Integer>>> saved = new HashMap<>();
        return dfs(candidates.length - 1, target, candidates, saved);
    }

    private List<List<Integer>> dfs(int index, int target, int[] candidates,
    Map<Key, List<List<Integer>>> saved) {
    
        Key node = new Key(index, target);
        if (saved.containsKey(node)) {
            return saved.get(node);
        }

        List<List<Integer>> result = new ArrayList<>();

        // base case: leaf with answer
        if (node._index >= 0 && node._target == 0) {
            List<Integer> list = new ArrayList<>();
            result.add(list);
        }

        // general case: non-leaf node
        if (node._index >= 0 && node._target > 0) {

            List<List<Integer>> leftResult = dfs(node._index, node._target -
            candidates[node._index], candidates, saved);
            
            List<List<Integer>> rightResult = dfs(node._index - 1, node._target,
            candidates, saved);
            
            for (List<Integer> list : leftResult) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(candidates[node._index]);
                result.add(copy);
            }
            result.addAll(rightResult);
        }

        saved.put(node, result);
        return result;
    }

    class Key {
        int _index;
        int _target;
        public Key(int index, int target) {
            _index = index;
            _target = target;
        }

        @Override
        public int hashCode() {
            return 31 * _target + _index;
        }

        @Override
        public boolean equals(Object that) {
            return that != null && that instanceof Key &&
                    ((Key)that)._index == _index &&
                    ((Key)that)._target == _target;
        }
    }
}
// 168 / 168 test cases passed.
// Runtime: 27 ms


