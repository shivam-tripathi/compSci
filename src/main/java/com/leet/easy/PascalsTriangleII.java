package com.leet.easy;

/**
 * 119. Pascal's Triangle II
 * https://leetcode.com/problems/pascals-triangle-ii/
 * Easy
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Example 1:
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 *
 * Example 2:
 * Input: rowIndex = 0
 * Output: [1]
 *
 * Example 3:
 * Input: rowIndex = 1
 * Output: [1,1]
 *
 * Constraints:
 *     0 <= rowIndex <= 33
 *
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 */

import java.util.*;

public class PascalsTriangleII {
  public List<Integer> getRow(int rowIndex) {
    var cur = new Integer[]{1};
    while (cur.length <= rowIndex) {
      // var next = new ArrayList<Integer>();
      var next = new Integer[cur.length + 1];
      int prev = 0;
      for (int i = 0; i < cur.length; i++) {
        next[i] = prev + cur[i];
        prev = cur[i];
      }
      next[next.length - 1] = cur[cur.length - 1];
      cur = next;
    }
    return Arrays.asList(cur);
  }
}
