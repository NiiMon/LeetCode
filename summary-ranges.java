/*

228. Summary Ranges
https://leetcode.com/problems/summary-ranges/description/


Given a sorted integer array without duplicates, return the summary of its
ranges.

Example 1:
Input: [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]

Example 2:
Input: [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]

*/

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        
        int start = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (i == nums.length || nums[i] != nums[i - 1] + 1) {
                result.add(group(nums[start], nums[i - 1]));
                start = i;
            }
        }
        
        return result;
    }
    private String group(int a, int b) {
        if (a == b) {
            return a + "";
        } else {
            return a + "->" + b;
        }
    }
}

