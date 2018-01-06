/*

53. Maximum Subarray
https://leetcode.com/problems/maximum-subarray/description/


Find the contiguous subarray within an array (containing at least one number)
which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

click to show more practice.

More practice: If you have figured out the O(n) solution, try coding another
solution using the divide and conquer approach, which is more subtle.

*/

// Mathmatical Induction approach
class Solution {
    public int maxSubArray(int[] a) {
        int max = Integer.MIN_VALUE;
        int leftMax = 0;
        
        for (int i : a) {
            leftMax = Math.max(leftMax + i, i);
            max = Math.max(max, leftMax);
        }
        
        return max;
    }
}


// Divide and Conquer approach
class Solution {
    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1)._max;
    }
    private MyResult helper(int[] a, int start, int end) {
        if (start > end) {
            return new MyResult(Integer.MIN_VALUE,
                                Integer.MIN_VALUE,
                                Integer.MIN_VALUE,
                                Integer.MIN_VALUE);
        }
        
        if (start == end) {
            return new MyResult(a[start], 
                                a[start], 
                                a[start], 
                                a[start]);
        }
        
        int mid = start + (end - start) / 2;
        MyResult lResult = helper(a, start, mid);
        MyResult rResult = helper(a, mid + 1, end);
        
        int left = Math.max(lResult._left, lResult._sum + rResult._left);
        int right = Math.max(rResult._right, rResult._sum + lResult._right);
        int max = Math.max(Math.max(lResult._max, rResult._max), 
                            lResult._right + rResult._left);
        int sum = lResult._sum + rResult._sum;
        
        return new MyResult(left, right, max, sum);
    }
    class MyResult {
        int _left;  // max sum starting from left-most element
        int _right; // max sum ending at right-most element
        int _max;   // max sum in sub-array
        int _sum;   // sum of sub-array
        public MyResult(int left, int right, int max, int sum) {
            _left = left;
            _right = right;
            _max = max;
            _sum = sum;
        }
    }
}

