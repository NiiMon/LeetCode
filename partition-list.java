/*

86. Partition List
https://leetcode.com/problems/partition-list/description/


Given a linked list and a value x, partition it such that all nodes less than
x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the
two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

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
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode less = new ListNode(0);
        ListNode pl = less;     // pointer for less
        ListNode GE = new ListNode(0);
        ListNode pg = GE;       // pointer for GE
        
        while (head != null) {
            if (head.val < x) {
                pl.next = head;
                pl = pl.next;
            } else {
                pg.next = head;
                pg = pg.next;
            }
            head = head.next;
        }
        
        pl.next = GE.next;
        pg.next = null;
        
        return less.next;
    }
}

