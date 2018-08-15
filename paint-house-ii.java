/*

265. Paint House II
https://leetcode.com/problems/paint-house-ii/description/


There are a row of n houses, each house can be painted with one of the k colors.
The cost of painting each house with a certain color is different. You have to
paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k
cost matrix. For example, costs[0][0] is the cost of painting house 0 with color
0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find
the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:
Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: 
Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 =
5.

Follow up:
Could you solve it in O(nk) runtime?

*/


// dp, O(nk^2) runtime
class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        if (costs[0].length == 1) {
            return costs[0][0];
        }

        final int n = costs.length;
        final int k = costs[0].length;

        int[] pre = new int[k];
        int[] cur = new int[k];

        for (int i = 0; i < n; i++) {
            for (int p = 0; p < k; p++) {
                // g(i-1, p)
                int preMin = Integer.MAX_VALUE;
                for (int x = 0; x < k; x++) {
                    if (x != p) {
                        preMin = Math.min(preMin, pre[x]);
                    }
                }
                cur[p] = costs[i][p] + preMin;
            }
            int[] tmp = pre;
            pre = cur;
            cur = tmp;
        }

        int result = Integer.MAX_VALUE;
        for (int val : pre) {
            result = Math.min(result, val);
        }

        return result;
    }
}
// 105 / 105 test cases passed.
// Runtime: 7 ms



// dp, O(nk) runtime
class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        if (costs[0].length == 1) {
            return costs[0][0];
        }

        final int n = costs.length;
        final int k = costs[0].length;

        int[] pre = new int[k];
        int[] cur = new int[k];

        int pre1 = 0;
        int pre1Index = 0;
        int pre2 = 0;

        for (int i = 0; i < n; i++) {
            int cur1 = Integer.MAX_VALUE;
            int cur1Index = 0;
            int cur2 = Integer.MAX_VALUE;
            for (int p = 0; p < k; p++) {
                // g(i-1, p)
                cur[p] = costs[i][p] + (p == pre1Index ? pre2 : pre1);
                if (cur[p] < cur1) {
                    cur2 = cur1;
                    cur1 = cur[p];
                    cur1Index = p;
                } else if (cur[p] < cur2) {
                    cur2 = cur[p];
                }
            }
            int[] tmp = pre;
            pre = cur;
            cur = tmp;
            pre1 = cur1;
            pre1Index = cur1Index;
            pre2 = cur2;
        }

        int result = Integer.MAX_VALUE;
        for (int val : pre) {
            result = Math.min(result, val);
        }

        return result;
    }
}
// 105 / 105 test cases passed.
// Runtime: 2 ms

