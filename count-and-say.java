/*

38. Count and Say
https://leetcode.com/problems/count-and-say/description/


The count-and-say sequence is the sequence of integers with the first five
terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"

Example 2:

Input: 4
Output: "1211"

*/

class Solution {
    public String countAndSay(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be greater or equal to 1.");
        }
        
        String last = "1";
        
       for (int j = 1; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            int counter = 1;
            
            for (int i = 1; i <= last.length(); i++) {
                if (i == last.length() || 
                    last.charAt(i) != last.charAt(i - 1)) {
                    sb.append(counter);
                    sb.append(last.charAt(i - 1));
                    counter = 1;
                } else {
                    counter++;
                }
            }
            
            last = sb.toString();
        }
        
        return last;
    }
}

