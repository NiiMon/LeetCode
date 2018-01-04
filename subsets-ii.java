/*

90. Subsets II
https://leetcode.com/problems/subsets-ii/description/


Given a collection of integers that might contain duplicates, nums, return all
possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        
        if (a.length == 0) {
            return result;
        }
        
        Arrays.sort(a);
        
        int s1 = 0;
        for (int i = 0; i < a.length; i++) {
            int size = result.size();
            int j = 0;
            if (i >= 1 && a[i - 1] == a[i]) {
                j = s1;
            }
            for (; j < size; j++) {
                List<Integer> list = new ArrayList<>(result.get(j));
                list.add(a[i]);
                result.add(list);
            }
            s1 = size;
        }
        
        return result;
    }
}

