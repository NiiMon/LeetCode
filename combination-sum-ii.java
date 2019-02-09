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

        return dfs(candidates, candidates.length - 1, target, new HashMap<>());
    }
    private List<List<Integer>> dfs(int[] candidates, int index, int target,
                                    Map<Key, List<List<Integer>>> saved) {
        Key key = new Key(index, target);
        if (saved.containsKey(key)) {
            return saved.get(key);
        }

        List<List<Integer>> result = new ArrayList<>();

        // answer node
        if (target == 0) {
            result.add(new ArrayList<>());
        }
        // keep looking
        if (index >= 0 && target >= candidates[0]) {
            // left
            for (List<Integer> list : dfs(candidates, index - 1, 
                                          target - candidates[index], saved)) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(candidates[index]);
                result.add(copy);
            }

            // right
            int nextIndex = index;
            while (nextIndex >= 0 && 
                   candidates[nextIndex] == candidates[index]) {
                nextIndex--;
            }
            result.addAll(dfs(candidates, nextIndex, target, saved));
        }

        // save to hashmap
        saved.put(key, result);
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
// Status: Accepted
// Runtime: 12 ms
// Memory Usage: 31.2 MB

