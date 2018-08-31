/*

31. Next Permutation
https://leetcode.com/problems/next-permutation/description/

Implement next permutation, which rearranges numbers into the lexicographically
next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible
order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding
outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

*/


class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        
        // find the left boundary of the non-descending sequence
        int left = nums.length - 1;
        while (left - 1 >= 0 && nums[left - 1] >= nums[left]) {
            left--;
        }
        // reverse them
        for (int i = left, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
        
        // if there is another number on the left
        // swap it with the next greater number in the sequence.
        if (left - 1 >= 0) {
            for (int i = left; i < nums.length; i++) {
                if (nums[i] > nums[left - 1]) {
                    swap(nums, i, left - 1);
                    break;
                }
            }
        }
    }
    private void swap(int[] a, int l, int r) {
        int tmp = a[l];
        a[l] = a[r];
        a[r] = tmp;
    }
}
// 265 / 265 test cases passed.
// Runtime: 12 ms


