/*

377. Combination Sum IV
https://leetcode.com/problems/combination-sum-iv/description/

Given an integer array with all positive numbers and no duplicates, find the 
number of possible combinations that add up to a positive integer target.

Example:
nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
 

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

*/


// dfs top-down with memorization
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        
        Arrays.sort(nums);

        return dfs(nums, target, new HashMap<>());
    }
    private int dfs(int[] nums, int remain, Map<Integer, Integer> saved) {
        if (saved.containsKey(remain)) {
            return saved.get(remain);
        }

        int result = 0;

        if (remain == 0) {
            result = 1;
        }

        if (remain > 0) {
            for (int i = 0; i < nums.length && nums[i] <= remain; i++) {
                result += dfs(nums, remain - nums[i], saved);
            }
        }
        
        saved.put(remain, result);
        return result;
    }
}
// 17 / 17 test cases passed.
// Status: Accepted
// Runtime: 5 ms
// Memory Usage: 27.4 MB


