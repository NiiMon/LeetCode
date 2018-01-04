/*

46. Permutations
https://leetcode.com/problems/permutations/description/


Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/

class Solution {
    public List<List<Integer>> permute(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        
        if (a.length == 0) {
            return result;
        }
        
        for (int i = 0; i < a.length; i++) {
            List<List<Integer>> temp = new ArrayList<>();
            
            for (int j = 0; j < result.size(); j++) {
                for (int k = 0; k <= result.get(j).size(); k++) {
                    List<Integer> list = new ArrayList<>(result.get(j));
                    list.add(k, a[i]);
                    temp.add(list);
                }
            }
            
            result = temp;
        }
        
        return result;
    }
}

