/*

239. Sliding Window Maximum
https://leetcode.com/problems/sliding-window-maximum/description/


Given an array nums, there is a sliding window of size k which is moving from
the very left of the array to the very right. You can only see the k numbers
in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:  You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for
non-empty array.

Follow up:
Could you solve it in linear time?

*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        
        int[] result = new int[nums.length - k + 1];
        
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // input
            while (deque.size() > 0 && nums[deque.getLast()] <= nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            
            // output
            if (i - k >= -1) {
                result[i - k + 1] = nums[deque.getFirst()];
            }
            
            // removal
            if (deque.getFirst() == i - k + 1) {
                deque.removeFirst();
            }
        }
        
        return result;
    }
}

