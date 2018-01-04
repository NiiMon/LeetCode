/*

163. Missing Ranges
https://leetcode.com/problems/missing-ranges/description/


Given a sorted integer array where the range of elements are in the inclusive
range [lower, upper], return its missing ranges.

For example:
given [0, 1, 3, 50, 75], lower = 0 and upper = 99
return ["2", "4->49", "51->74", "76->99"].

*/

class Solution {
    public List<String> findMissingRanges(int[] a, int lower, int upper) {
        List<String> result = new ArrayList<>();
        
        if (a.length == 0) {
            result.add(group(lower, upper));
            return result;
        }
        
        // i == 0
        if (lower < a[0]) {
            result.add(group(lower, a[0] - 1));
        }
        
        // 1 <= i <= n-1
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i - 1] && a[i] > a[i - 1] + 1) {
                result.add(group(a[i - 1] + 1, a[i] - 1));
            }
        }
        
        // i == n
        if (a[a.length - 1] < upper) {
            result.add(group(a[a.length - 1] + 1, upper));
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

/*
笔记：
1. 有可能输入空array.
2. 有可能有duplicate.
3. 有可能输入有min, max 极值.
*/

