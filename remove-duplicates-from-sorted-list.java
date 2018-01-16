/*

83. Remove Duplicates from Sorted List
https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/


Given a sorted linked list, delete all duplicates such that each element
appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// iterative
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            if (pre != null && pre.val == cur.val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}

