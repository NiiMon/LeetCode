/*

78. Subsets
https://leetcode.com/problems/subsets/description/


Given a set of distinct integers, nums, return all possible subsets (the power
set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

*/

class Solution {
    public List<List<Integer>> subsets(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        
        if (a.length == 0) {
            return result;
        }
        
        for (int i = 0; i < a.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<Integer> list = new ArrayList<>(result.get(j));
                list.add(a[i]);
                result.add(list);
            }
        }
        
        return result;
    }
}

