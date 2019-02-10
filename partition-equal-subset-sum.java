/*

416. Partition Equal Subset Sum
https://leetcode.com/problems/partition-equal-subset-sum/description/

Given a non-empty array containing only positive integers, find if the array 
can be partitioned into two subsets such that the sum of elements in both 
subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.

Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


*/


class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 == 1) {
            return false;
        }

        return dfs(nums, nums.length - 1, sum / 2, new HashMap<>());
    }
    private boolean dfs(int[] nums, int index, int remain, 
                        Map<Key, Boolean> saved) {
        Key key = new Key(index, remain);
        if (saved.containsKey(key)) {
            return saved.get(key);
        }

        boolean result = false;

        if (remain == 0) {
            result = true;
        }

        if (remain > 0 && index >= 0) {
            result = dfs(nums, index - 1, remain - nums[index], saved) ||
                     !result && dfs(nums, index - 1, remain, saved);
        }

        saved.put(key, result);
        return result;
    }
    class Key {
        int _index;
        int _remain;
        public Key(int index, int remain) {
            _index = index;
            _remain = remain;
        }

        @Override
        public int hashCode() {
            return 31 * _index + _remain;
        }

        @Override
        public boolean equals(Object that) {
            return that != null && that instanceof Key &&
                    ((Key)that)._index == _index &&
                    ((Key)that)._remain == _remain;
        }
    }
}
// 104 / 104 test cases passed.
// Status: Accepted
// Runtime: 7 ms
// Memory Usage: 26.9 MB

