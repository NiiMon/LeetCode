/*

40. Combination Sum II
https://leetcode.com/problems/combination-sum-ii/description/

Given a collection of candidate numbers (candidates) and a target number
(target), find all unique combinations in candidates where the candidate numbers
sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]

*/


class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (target == 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        Arrays.sort(candidates);
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

        // answer node
        if (target == 0) {
            List<Integer> list = new ArrayList<>();
            result.add(list);
        }
        // keep looking
        if (index >= 0 && target > 0) {
            List<List<Integer>> leftResult = dfs(index - 1, 
                                                 target - candidates[index], 
                                                 candidates, saved);
            int nextIndex = index;
            while (nextIndex >= 0 && 
                   candidates[nextIndex] == candidates[index]) {
                nextIndex--;
            }
            List<List<Integer>> rightResult = dfs(nextIndex, target, 
                                                  candidates, saved);
            
            // op at node
            for (List<Integer> list : leftResult) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(candidates[index]);
                result.add(copy);
            }
            result.addAll(rightResult);
        }

        // save to hashmap
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
// 172 / 172 test cases passed.
// Runtime: 26 ms

