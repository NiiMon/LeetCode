/*

254. Factor Combinations
https://leetcode.com/problems/factor-combinations/description/

Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.

Write a function that takes an integer n and return all possible combinations
of its factors.

Note:
You may assume that n is always positive.
Factors should be greater than 1 and less than n.

Example 1:
Input: 1
Output: []

Example 2:
Input: 37
Output:[]

Example 3:
Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]

Example 4:
Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

*/


class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, 2, new Stack<Integer>(), result);

        return result;
    }
    private void dfs(int n, int minFactor, Stack<Integer> path, 
                    List<List<Integer>> result) {
        for (int a = minFactor; a <= Math.sqrt(n); a++) {
            if (n % a == 0) {
                int divide = n / a;
                path.push(a);
                List<Integer> sub = new ArrayList(path);
                sub.add(divide);
                result.add(sub);

                dfs(divide, a, path, result);

                path.pop();
            }
        }
    }
}
// 21 / 21 test cases passed.
// Status: Accepted
// Runtime: 1 ms
// Memory Usage: 24.4 MB

