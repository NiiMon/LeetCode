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


class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return new ArrayList<>();
        }
        
        Arrays.sort(candidates);

        return dfs(candidates, candidates.length - 1, target, new HashMap<>());
    }
    private List<List<Integer>> dfs(int[] candidates, int index, int val, Map<Key, List<List<Integer>>> saved) {
        Key key = new Key(index, val);
        if (saved.containsKey(key)) {
            return saved.get(key);
        }

        List<List<Integer>> result = new ArrayList<>();
        
        if (index < 0 || val < 0 || val != 0 && val < candidates[0]) {
            // branch pruning: do nothing
        } else if (val == 0) {
            // base case: leaf node with answer
            result.add(new ArrayList<>());
        } else {
            // general case: non-leaf node
            // left: use current element once
            for (List<Integer> sub : dfs(candidates, index, val - candidates[index], saved)) {
                List<Integer> copy = new ArrayList<>(sub);
                copy.add(candidates[index]);
                result.add(copy);
            }

            // right: skipping current element
            result.addAll(dfs(candidates, index - 1, val, saved));
        }

        saved.put(key, result);

        return result;
    }
    class Key {
        int _index;
        int _val;
        public Key(int index, int val) {
            _index = index;
            _val = val;
        }
        public int hashCode() {
            return _index * 31 + _val;
        }
        public boolean equals(Object that) {
            return that instanceof Key &&
                    ((Key)that)._index == _index &&
                    ((Key)that)._val == _val;
        }
    }
}
// 168 / 168 test cases passed.
// Status: Accepted
// Runtime: 17 ms
// Memory Usage: 30.6 MB

