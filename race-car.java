/*

818. Race Car
https://leetcode.com/problems/race-car/description/

Your car starts at position 0 and speed +1 on an infinite number line. (Your car
can go into negative positions.)

Your car drives automatically according to a sequence of instructions A
(accelerate) and R (reverse).

When you get an instruction “A”, your car does the following: position += speed,
speed *= 2.

When you get an instruction “R”, your car does the following: if your speed is
positive then speed = -1 , otherwise speed = 1. (Your position stays the same.)

For example, after commands “AAR”, your car goes to positions 0->1->3->3, and
your speed goes to 1->2->4->-1.

Now for some target position, say the length of the shortest sequence of
instructions to get there.


Example 1:
Input: 
target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.


Example 2:
Input: 
target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.


Note:
1 <= target <= 10000.


*/



class Solution {
    public int racecar(int target) {
        Key root = new Key(0, 1);
        Queue<Key> queue = new LinkedList<Key>();
        queue.add(root);
        Set<Key> addedToQueue = new HashSet<Key>();
        addedToQueue.add(root);

        int level  = 0;
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                Key cur = queue.remove();

                Key nextA = new Key(cur._pos + cur._speed, cur._speed * 2);
                Key nextR = new Key(cur._pos, cur._speed > 0 ? -1 : 1);
                if (nextA._pos == target || nextR._pos == target) {
                    return level + 1;
                }
                if (Math.abs(nextA._pos - target) < target &&  // strong pruning
                    !addedToQueue.contains(nextA)) {
                    queue.add(nextA);
                    addedToQueue.add(nextA);
                }
                if (Math.abs(nextR._pos - target) < target &&  // strong pruning
                    !addedToQueue.contains(nextR)) {
                    queue.add(nextR);
                    addedToQueue.add(nextR);
                }
            }
            level++;
        }
        return -1;
    }
    class Key {
        final int _pos;
        final int _speed;
        public Key(int pos, int speed) {
            _pos = pos;
            _speed = speed;
        }

        @Override
        public boolean equals(Object that) {
            return that != null && that instanceof Key &&
                    ((Key)that)._pos == _pos &&
                    ((Key)that)._speed == _speed;
        }

        @Override
        public int hashCode() {
            return 31 * _pos + _speed;
        }
    }
}
// 53 / 53 test cases passed.
// Runtime: 149 ms

