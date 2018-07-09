/*

84. Largest Rectangle in Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/description/


Given n non-negative integers representing the histogram's bar height where
the width of each bar is 1, find the area of largest rectangle in the
histogram.

[img] https://leetcode.com/static/images/problemset/histogram.png
Above is a histogram where width of each bar is 1, 
given height = [2,1,5,6,2,3].

[img] https://leetcode.com/static/images/problemset/histogram_area.png
The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:
Input: [2,1,5,6,2,3]
Output: 10

*/

// version 1
class Solution {
    public int largestRectangleArea(int[] h) {
        if (h == null || h.length == 0) {
            return 0;
        }

        int[] left  = new int[h.length];
        int[] right = new int[h.length];

        // fill in left array
        // left[0] = 0;
        for (int i = 1; i < h.length; i++) {
            if (h[i] > h[i - 1]) {
                left[i] = i;
            } else {
                int index = i;
                while (index - 1 >= 0 && h[i] <= h[index - 1]) {
                    index = left[index - 1];
                }
                left[i] = index;
            }
        }

        // fill in right array
        right[right.length - 1] = right.length - 1;
        for (int i = right.length - 2; i >= 0; i--) {
            if (h[i] > h[i + 1]) {
                right[i] = i;
            } else {
                int index = i;
                while (index + 1 < right.length && h[i] <= h[index + 1]) {
                    index = right[index + 1];
                }
                right[i] = index;
            }
        }

        // calculate
        int max = 0;
        for (int i = 0; i < left.length; i++) {
            max = Math.max(max, h[i] * (right[i] - left[i] + 1));
        }

        return max;
    }
}
// 96 / 96 test cases passed.
// Runtime: 2 ms


// version 2
// more concised version
class Solution {
    public int largestRectangleArea(int[] h) {
        int[] left = new int[h.length];
        for (int i = 0; i < h.length; i++) {
            left[i] = i;
            while (left[i] - 1 >= 0 && h[i] <= h[left[i] - 1]) {
                left[i] = left[left[i] - 1];
            }
        }

        int[] right = new int[h.length];
        for (int i = h.length - 1; i >= 0; i--) {
            right[i] = i;
            while (right[i] + 1 < h.length && h[i] <= h[right[i] + 1]) {
                right[i] = right[right[i] + 1];
            }
        }

        int max = 0;
        for (int i = 0; i < left.length; i++) {
            max = Math.max(max, h[i] * (right[i] - left[i] + 1));
        }

        return max;
    }
}
// 96 / 96 test cases passed.
// Runtime: 2 ms

