/*

735. Asteroid Collision
https://leetcode.com/problems/asteroid-collision/description/


We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign
represents its direction (positive meaning right, negative meaning left). Each
asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids
meet, the smaller one will explode. If both are the same size, both will
explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: 
asteroids = [5, 10, -5]
Output: [5, 10]
Explanation: 
The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.

Example 2:
Input: 
asteroids = [8, -8]
Output: []
Explanation: 
The 8 and -8 collide exploding each other.

Example 3:
Input: 
asteroids = [10, 2, -5]
Output: [10]
Explanation: 
The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.

Example 4:
Input: 
asteroids = [-2, -1, 1, 2]
Output: [-2, -1, 1, 2]
Explanation: 
The -2 and -1 are moving left, while the 1 and 2 are moving right.
Asteroids moving the same direction never meet, so no asteroids will meet each
other.

Note:
The length of asteroids will be at most 10000.
Each asteroid will be a non-zero integer in the range [-1000, 1000]..

*/


// version 1
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length == 0) {
            return asteroids;
        }

        List<Integer> list = new ArrayList<>();
        int i = 0;

        // step 1
        // save left-onlys
        for (; i < asteroids.length && asteroids[i] < 0; i++) {
            list.add(asteroids[i]);
        }

        // step 2
        // process right-left subset
        while (i < asteroids.length) {
            Deque<Integer> pos = new LinkedList<>();
            Queue<Integer> neg = new LinkedList<>();

            // positive numbers
            for (; i < asteroids.length && asteroids[i] > 0; i++) {
                pos.addLast(asteroids[i]);
            }
            // negative numbers
            for (; i < asteroids.length && asteroids[i] < 0; i++) {
                neg.add(asteroids[i]);
            }

            // rock and roll
            while (pos.size() > 0 && neg.size() > 0) {
                if (pos.getLast() < -neg.peek()) {
                    pos.removeLast();
                } else if (pos.getLast() > -neg.peek()) {
                    neg.remove();
                } else {
                    pos.removeLast();
                    neg.remove();
                }
            }

            // write done
            while (pos.size() > 0) {
                list.add(pos.removeFirst());
            }
            while (neg.size() > 0) {
                list.add(neg.remove());
            }
        }

        // step 3
        // repair results
        int[] result = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
            result[j] = list.get(j);
        }
        
        if (result.length < asteroids.length) {
            return asteroidCollision(result);
        }
        return result;
    }
}
// 275 / 275 test cases passed.
// Runtime: 23 ms


// version 2
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int ball : asteroids) {
            if (ball > 0) {
                deque.addLast(ball);
            } else {
                while(true) {
                    if (deque.peekLast() ==  null) {
                        list.add(ball);
                        break;
                    } else if (deque.peekLast() + ball < 0) {
                        deque.removeLast();
                    } else if (deque.peekLast() + ball > 0) {
                        break;
                    } else {
                        deque.removeLast();
                        break;
                    }
                }
            }
        }

        while (deque.size() > 0) {
            list.add(deque.removeFirst());
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
// 275 / 275 test cases passed.
// Runtime: 16 ms

