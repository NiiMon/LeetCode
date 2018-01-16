/*

143. Reorder List
https://leetcode.com/problems/reorder-list/description/


Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // find mid-point
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        // reverse second half
        ListNode secList = reverseList(slow.next);
        slow.next = null;
        
        // build reordered list
        ListNode result = new ListNode(0);
        ListNode p = result;
        ListNode p1 = head;
        ListNode p2 = secList;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            }
            if (p2 != null) {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
        }
        
        head = result.next;
    }
    private ListNode reverseList(ListNode head) {
        
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

