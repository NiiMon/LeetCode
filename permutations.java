/*

46. Permutations
https://leetcode.com/problems/permutations/description/


Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/

// insert
class Solution {
    public List<List<Integer>> permute(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        
        if (a.length == 0) {
            return result;
        }
        
        for (int i = 0; i < a.length; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            
            for (int j = 0; j < result.size(); j++) {
                for (int k = 0; k <= result.get(j).size(); k++) {
                    List<Integer> list = new ArrayList<>(result.get(j));
                    list.add(k, a[i]);
                    temp.add(list);
                }
            }
            
            result = temp;
        }
        
        return result;
    }
}
// 25 / 25 test cases passed.
// Status: Accepted
// Runtime: 2 ms
// Memory Usage: 38 MB


// select then swap to front
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        dfs(nums, 0, result);
        return result;
    }
    private void dfs(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                sub.add(nums[i]);
            }
            result.add(sub);
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            dfs(nums, index + 1, result);
            swap(nums, i, index);
        }
    }
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
// 25 / 25 test cases passed.
// Status: Accepted
// Runtime: 1 ms
// Memory Usage: 37.7 MB
