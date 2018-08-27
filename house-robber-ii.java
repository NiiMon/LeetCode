/*

213. House Robber II
https://leetcode.com/problems/house-robber-ii/description/

You are a professional robber planning to rob houses along a street. Each house
has a certain amount of money stashed. All houses at this place are arranged in
a circle. That means the first house is the neighbor of the last one. Meanwhile,
adjacent houses have security system connected and it will automatically contact
the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each
house, determine the maximum amount of money you can rob tonight without
alerting the police.

Example 1:
Input: [2,3,2]
Output: 3
Explanation: 
You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because
they are adjacent houses.

Example 2:
Input: [1,2,3,1]
Output: 4
Explanation: 
Rob house 1 (money = 1) and then rob house 3 (money = 3). 
Total amount you can rob = 1 + 3 = 4.

*/


// iterative: dp
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob1(nums, 0, nums.length - 2), 
                        rob1(nums, 1, nums.length - 1));
    }
    private int rob1(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        
        int prev2 = 0;
        int prev1 = nums[start];
        for (int i = start + 1; i <= end; i++) {
            int cur = Math.max(prev2 + nums[i], prev1);
            prev2 = prev1;
            prev1 = cur;
        }
        
        return prev1;
    }
}
// 74 / 74 test cases passed.
// Runtime: 3 ms

