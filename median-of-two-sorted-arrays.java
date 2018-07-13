/*

4. Median of Two Sorted Arrays
https://leetcode.com/problems/median-of-two-sorted-arrays/description/


There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity
should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]
The median is 2.0

Example 2:
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5

*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int size = (m + n + 1) / 2;
        int start = 0;
        int end = m;

        while (start <= end) {
            int mid1 = start + (end - start) / 2;
            int mid2 = size - mid1;

            int maxLeftX = mid1 >= 1 ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int maxLeftY = mid2 >= 1 ? nums2[mid2 - 1] : Integer.MIN_VALUE;

            int minRightX = mid1 < m ? nums1[mid1] : Integer.MAX_VALUE;
            int minRightY = mid2 < n ? nums2[mid2] : Integer.MAX_VALUE;

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // perfect mid1
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftX, maxLeftY) * 1.0;
                } else {

                    return (Math.max(maxLeftX, maxLeftY) + 
                            Math.min(minRightX, minRightY)) / 2.0;
                
                }
            }
            if (maxLeftX > minRightY) {
                end = mid1 - 1;
            } else {
                start = mid1 + 1;
            }
        }
        throw new IllegalArgumentException();
    }
}
// 2080 / 2080 test cases passed.
// Runtime: 32 ms

