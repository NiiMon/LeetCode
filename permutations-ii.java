/*

47. Permutations II
https://leetcode.com/problems/permutations-ii/description/

Given a collection of numbers that might contain duplicates, return all possible
unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


*/


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        dfs(result, path, map);
        return result;
    }
    private void dfs(List<List<Integer>> result, Stack<Integer> path,
        Map<Integer, Integer> map) {
        if (map.isEmpty()) {
            result.add(new ArrayList<>(path));
        }
        
        Set<Integer> set = new HashSet<>(map.keySet());
        for (int child : set) {
            path.push(child);
            map.put(child, map.get(child) - 1);
            if (map.get(child) == 0) {
                map.remove(child);
            }
            dfs(result, path, map);
        }
        if (!path.isEmpty()) {
            int n = path.pop();
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
    }
}
// 30 / 30 test cases passed.
// Runtime: 14 ms

