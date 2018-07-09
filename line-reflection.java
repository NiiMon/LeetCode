/*

356. Line Reflection
https://leetcode.com/problems/line-reflection/description/

Given n points on a 2D plane, find if there is such a line parallel to y-axis
that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n^2)?

*/


class Solution {
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();

        for (int[] point : points) {
            max = Math.max(max, point[0]);
            min = Math.min(min, point[0]);
            String str = point[0] + "," + point[1];
            set.add(str);
        }

        int reflect = max + min;
        for (int[] point : points) {
            String str = (reflect - point[0]) + "," + point[1];
            if ( !set.contains(str)) {
                return false;
            }
        }

        return true;
    }
}
// 37 / 37 test cases passed.
// Runtime: 12 ms

