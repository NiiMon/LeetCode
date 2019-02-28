/*

307. Range Sum Query - Mutable
https://leetcode.com/problems/range-sum-query-mutable/

Given an integer array nums, find the sum of the elements between indices i and
j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to
val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is
distributed evenly.

*/


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

class NumArray {
    int n;
    int[] tree;
    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[n * 2];

        for (int i = 0; i < n; i++) {
            tree[n + i] = nums[i];
        }

        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }
    
    public void update(int i, int val) {
        i += n;
        tree[i] = val;

        for (int j = i / 2; j > 0; j /= 2) {
            tree[j] = tree[j * 2] + tree[j * 2 + 1];
        }
    }
    
    public int sumRange(int i, int j) {
        int sum = 0;

        for (int L = n + i, R = n + j + 1; L < R; L /= 2, R /= 2) {
            if (L % 2 == 1) {
                sum += tree[L++];
            }
            if (R % 2 == 1) {
                sum += tree[--R];
            }
        }
        return sum;
    }
}
// 10 / 10 test cases passed.
// Status: Accepted
// Runtime: 56 ms
// Memory Usage: 46.4 MB

