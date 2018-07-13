/*

152. Maximum Product Subarray
https://leetcode.com/problems/maximum-product-subarray/description/


Given an integer array nums, find the contiguous subarray within an array
(containing at least one number) which has the largest product.

Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


*/

/**

Test cases:
[2,3,-2,4]
[-1,0,-2]
[1,2,-3,4,-5,0,1,-10000, 5, 8,-1]
[]
[-2]
[2,-5,-2,-4,3]

*/

class Solution {
    public int maxProduct(int[] a) {
        if (a.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int prevProd = 1;
        int firstNeg = 0;

        for (int i = 0; i < a.length; i++) {
            int curProd = a[i] * prevProd;
            int prodWithoutFirstNeg = curProd;
            if (firstNeg < 0) {
                prodWithoutFirstNeg = curProd / firstNeg;
            }

            // update max
            max = maxx(max, a[i], curProd, prodWithoutFirstNeg);

            // update firstNeg
            if (firstNeg == 0 && a[i] < 0) {
                firstNeg = curProd;
            }
            if (a[i] == 0) {
                firstNeg = 0;
                curProd = 1;
            }

            // update prevProd
            prevProd = curProd;
        }

        return max;
    }
    private int maxx(int... input) {
        int max = input[0];
        for (int i = 1; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        return max;
    }
}
// 184 / 184 test cases passed.
// Runtime: 2 ms


