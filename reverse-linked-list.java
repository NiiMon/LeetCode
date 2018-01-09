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

// iterative solution
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


// recursive solution 1
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode next = head.next;
        head.next = null;
        
        return helper(head, next);
    }
    private ListNode helper(ListNode prev, ListNode curr) {
        if (curr == null) {
            return prev;
        }
        
        ListNode next = curr.next;
        curr.next = prev;
        
        return helper(curr, next);
    }
}


// recursive solution 2
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        
        return result;
    }
}

