/*

823. Binary Trees With Factors
https://leetcode.com/problems/binary-trees-with-factors/description/

Given an array of unique integers, each integer is strictly greater than 1.

We make a binary tree using these integers and each number may be used for any
number of times.

Each non-leaf node's value should be equal to the product of the values of it's
children.

How many binary trees can we make?  Return the answer modulo 10 ** 9 + 7.

Example 1:
Input: A = [2, 4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]

Example 2:
Input: A = [2, 4, 5, 10]
Output: 7
Explanation: 
We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].

*/


// Graph DFS Bottom-Up
class Solution {
    public int numFactoredBinaryTrees(int[] a) {
        if (a.length == 0) {
            return 0;
        }

        long[] saved = new long[a.length];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }

        long sum = 0;
        final int mod = (int)Math.pow(10, 9) + 7;
        for (int i = 0; i < a.length; i++) {
            sum += dfs(a, i, map, saved);
        }

        return (int)(sum % mod);
    }
    private long dfs(int[] a, int index, Map<Integer, Integer> map, 
        long[] saved) {
        if (saved[index] > 0) {
            return saved[index];
        }

        long count = 1;
        for (int i = 0; i < a.length; i++) {
            if (i == index) {
                continue;
            }
            if (a[index] % a[i] == 0 && map.containsKey(a[index] / a[i])) {
                int j = map.get(a[index] / a[i]);
                count += dfs(a, i, map, saved) * dfs(a, j, map, saved);
            }
        }

        saved[index] = count;
        return count;
    }
}
// 47 / 47 test cases passed.
// Runtime: 49 ms


// dp: iterative
class Solution {
    public int numFactoredBinaryTrees(int[] a) {
        Arrays.sort(a);
        Map<Integer, Long> saved = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            long num = 1;
            for (int leftIndex = 0; leftIndex < i && 
                 a[leftIndex] * a[leftIndex] <= a[i]; leftIndex++) {
                int rightNum = a[i] / a[leftIndex];
                if (a[i] % a[leftIndex] == 0 && saved.containsKey(rightNum)) {
                    num += saved.get(a[leftIndex]) * saved.get(rightNum) *
                            (a[leftIndex] != rightNum ? 2 : 1);
                }
            }
            saved.put(a[i], num);
        }

        long sum = 0;
        for (int n : saved.keySet()) {
            sum += saved.get(n);
        }
        return (int)(sum % (Math.pow(10, 9) + 7));
    }
}
// 47 / 47 test cases passed.
// Runtime: 43 ms

