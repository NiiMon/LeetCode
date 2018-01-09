/*

84. Largest Rectangle in Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/description/


Given n non-negative integers representing the histogram's bar height where
the width of each bar is 1, find the area of largest rectangle in the
histogram.


Above is a histogram where width of each bar is 1, given height =
[2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.

*/


class Solution {
    public int largestRectangleArea(int[] h) {
        // left edge
        int[] left = new int[h.length];
        for (int i = 0; i < h.length; i++) {
            int lEdge = i;
            while (lEdge - 1 >= 0 && h[lEdge - 1] >= h[i]) {
                lEdge = left[lEdge - 1];
            }
            left[i] = lEdge;
        }
        
        // right edge
        int[] right = new int[h.length];
        for (int i = h.length - 1; i >= 0; i--) {
            int rEdge = i;
            while (rEdge + 1 <= h.length - 1 && h[rEdge + 1] >= h[i]) {
                rEdge = right[rEdge + 1];
            }
            right[i] = rEdge;
        }
        
        // calculate max area
        int max = 0;
        for (int i = 0; i < h.length; i++) {
            max = Math.max(max, h[i] * (right[i] - left[i] + 1));
        }
        
        return max;
    }
}

