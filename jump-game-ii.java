/*

45. Jump Game II
https://leetcode.com/problems/jump-game-ii/description/

Given an array of non-negative integers, you are initially positioned at the
first index of the array.

Each element in the array represents your maximum jump length at that
position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.


*/
class Solution {
    public int jump(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> added = new HashSet<>();
        queue.add(0);
        added.add(0);
        int steps = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.remove();
                if (cur == nums.length - 1) {
                    return steps;
                }

                for (int j = 1; j <= nums[cur] && cur + j < nums.length; j++) {
                    if (added.add(cur + j)) {
                        queue.add(cur + j);
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
// 92 / 92 test cases passed.
// Status: Accepted
// Runtime: 3163 ms
// Memory Usage: 38.4 MB

