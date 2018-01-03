/*

66. Plus One
https://leetcode.com/problems/plus-one/description/


Given a non-negative integer represented as a non-empty array of digits, plus
one to the integer.

You may assume the integer do not contain any leading zero, except the number
0 itself.

The digits are stored such that the most significant digit is at the head of
the list.

*/

// version 1
class Solution {
    public int[] plusOne(int[] a) {
        int carry = 1;
        for (int i = a.length - 1; i >= 0 && carry == 1; i--) {
            int sum = a[i] + carry;
            a[i] = sum % 10;
            carry = sum / 10;
        }
        
        if (carry == 1) {
            int[] b = new int[a.length + 1];
            b[0] = 1;
            return b;
        } 
        
        return a;
    }
}

// version 2
class Solution {
    public int[] plusOne(int[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] < 9) {
                a[i]++;
                return a;
            }
            a[i] = 0;
        }
        
        int[] b = new int[a.length + 1];
        b[0] = 1;
        return b;
    }
}

