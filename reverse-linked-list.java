/*

206. Reverse Linked List
https://leetcode.com/problems/reverse-linked-list/description/


Reverse a singly linked list.

click to show more hints.

Hint: 
A linked list can be reversed either iteratively or recursively. Could
you implement both?

*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


class Solution {
    public ListNode reverseList(ListNode head) {
        
        ListNode pre = null;
        ListNode cur = head;
        
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        
        return pre;
    }
}

