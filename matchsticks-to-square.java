/*

473. Matchsticks to Square
https://leetcode.com/problems/matchsticks-to-square/description/

Remember the story of Little Match Girl? By now, you know exactly what
matchsticks the little match girl has, please find out a way you can make one
square by using up all those matchsticks. You should not break any stick, but
you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their
stick length. Your output will either be true or false, to represent whether you
could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came
two sticks with length 1.

Example 2:
Input: [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.

Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.

*/

class Solution {
    public boolean makesquare(int[] nums) {
        if (nums.length < 4) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            sum += num;
            if (num > max) {
                max = num;
            }
        }
        if (sum % 4 != 0 || max > sum / 4) {
            return false;
        }
        
        Arrays.sort(nums);  // this really boost up
        boolean[] result = new boolean[1];
        dfs(nums, nums.length - 1, new int[4], sum / 4, result);
        return result[0];
    }
    private void dfs(int[] nums, int index, int[] slots, int limit,
    				 boolean[] result) {
        if (index == -1) {
            result[0] = true;
        }
        for (int i = 0; !result[0] && i < 4; i++) {
            if (slots[i] + nums[index] <= limit) {
                slots[i] += nums[index];
                dfs(nums, index - 1, slots, limit, result);
                slots[i] -= nums[index];
            }
        }
    }
}
// 174 / 174 test cases passed.
// Status: Accepted
// Runtime: 27 ms
// Memory Usage: 37.4 MB

