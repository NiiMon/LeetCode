/*

34. Search for a Range
https://leetcode.com/problems/search-for-a-range/description/


Given an array of integers nums sorted in ascending order, find the starting
and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];

        int start = 0;
        int end = nums.length - 1;
        int pos = -1;

        // find pos1, s.t f(a[pos1 - 1]) < target <= f(a[pos1])
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target <= nums[mid]) {
                if (target == nums[mid]) {
                    pos = mid;
                }
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        result[0] = pos;

        start = 0;
        end = nums.length - 1;

        // find pos2, s.t f(a[pos2]) <= target < f(a[pos2 + 1])
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target) {
                    pos = mid;
                }
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        result[1] = pos;

        return result;
    }
}
// 88 / 88 test cases passed.
// Runtime: 4 ms

