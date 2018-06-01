/*

239. Sliding Window Maximum
https://leetcode.com/problems/sliding-window-maximum/description/


Given an array nums, there is a sliding window of size k which is moving from
the very left of the array to the very right. You can only see the k numbers
in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:  You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for
non-empty array.

Follow up:
Could you solve it in linear time?

*/

// Binary search tree solution
// O(nlogn)
class Solution {
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a.length == 0) {
            return new int[]{};
        }
        
        int[] result = new int[a.length - k + 1];

        TreeMap<Integer, Integer> tm = new TreeMap<>((k1, k2) -> {
            // for different values
            if (a[k1] != a[k2]) {
                return a[k1] - a[k2];
            }
            // for same value, different indexes
            else {
                return k1 - k2;
            }
        });

        for (int i = 0; i < a.length; i++) {
            // remove old numbers
            if (i >= k) {
                tm.remove(i - k);
            }

            // add new number
            tm.put(i, a[i]);

            // write out result
            if (i >= k - 1) {
                result[i - k + 1] = tm.lastEntry().getValue();
            }            
        }

        return result;
    }
}

// Deque version
// O(n)
class Solution {
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a.length == 0) {
            return new int[]{};
        }
        
        int[] result = new int[a.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < a.length; i++) {
            // remove old numbers
            if (i >= k && deque.size() > 0 && deque.peekFirst() == i - k) {
                deque.removeFirst();
            }

            // add new number
            while (deque.size() > 0 && a[deque.peekLast()] <= a[i]) {
                deque.removeLast();
            }
            deque.addLast(i);

            // write out result
            if (i >= k - 1) {
                result[i - k + 1] = a[deque.peekFirst()];
            }            
        }

        return result;
    }
}

