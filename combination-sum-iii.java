/*

216. Combination Sum III
https://leetcode.com/problems/combination-sum-iii/description/


Find all possible combinations of k numbers that add up to a number n, given 
that only numbers from 1 to 9 can be used and each combination should be a 
unique set of numbers.

Note:
All numbers will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

*/

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if ((1 + k) * k / 2 > n) {
            return result;
        }
        
        dfs(k, 0, n, new Stack<>(), result);

        return result;
    }
    private void dfs(int k, int start, int remain, Stack<Integer> path, 
                     List<List<Integer>> result) {
        // op at node
        if (start > 0) {
            path.push(start);
            remain -= start;
        }

        // op at leaf
        if (remain == 0 && path.size() == k) {
            result.add(new ArrayList<>(path));
        }

        // go down to children
        if (remain > 0 && path.size() < k) {
            for (int childStart = start + 1; 
                 childStart <= 9 && childStart <= remain; childStart++) {
                dfs(k, childStart, remain, path, result);
            }
        }

        // go up to parent
        if (path.size() > 0) {
            path.pop();
        }
    }
}
// 18 / 18 test cases passed.
// Status: Accepted
// Runtime: 1 ms
// Memory Usage: 24.1 MB

