package com.leet.medium;

/**
 * 19. Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Medium
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * Follow up: Could you do this in one pass?
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 * Constraints:
 *     The number of nodes in the list is sz.
 *     1 <= sz <= 30
 *     0 <= Node.val <= 100
 *     1 <= n <= sz
 */

import com.leet.common.ListNode;

public class RemoveNthNodeFromEndOfList {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode sentinel = new ListNode(0);
    sentinel.next = head;
    ListNode fast = sentinel, slow = sentinel;
    for (int i = 1; i <= n; i++) {
      if (fast == null) {
        return head;
      }
      fast = fast.next;
    }

    ListNode prev = sentinel;
    while (fast != null) {
      fast = fast.next;
      prev = slow;
      slow = slow.next;
    }
    prev.next = slow.next;
    return sentinel.next;
  }
}
