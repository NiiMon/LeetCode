/*

403. Frog Jump
https://leetcode.com/problems/frog-jump/description/


A frog is crossing a river. The river is divided into x units and at each unit
there may or may not exist a stone. The frog can jump on a stone, but it must
not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order,
determine if the frog is able to cross the river by landing on the last stone.
Initially, the frog is on the first stone and assume the first jump must be 1
unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k,
or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.

Example 1:
[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.

Example 2:
[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.


*/

// Graph DFS Bottom-Up
class Solution {
    public boolean canCross(int[] stones) {
        if (stones.length == 0) {
            return false;
        }
        
        Set<Integer> stoneSet = new HashSet<>();
        for (int stone : stones) {
            stoneSet.add(stone);
        }
        Map<Key, Boolean> saved = new HashMap<>();
        
        return dfs(stones, 0, 0, stoneSet, saved);
    }
    private boolean dfs(int[] stones, int pos, int step, 
                        Set<Integer> stoneSet, Map<Key, Boolean> saved) {
        Key key = new Key(pos, step);
        if (saved.containsKey(key)) {
            return saved.get(key);
        }
        
        boolean result = false;
        
        if (!stoneSet.contains(pos)) {
            // base case: miss
            result = false;
        } else if (stoneSet.contains(pos) && pos == stones[stones.length - 1]) {
            // base case: hit and last
            result = true;
        } else {
            // general case: hit but not last
            for (int k = Math.max(1, step - 1); k <= step + 1 && !result; k++) {
                result = dfs(stones, pos + k, k, stoneSet, saved);
            }
        }
        
        saved.put(key, result);
        return result;
    }
    class Key {
        int _pos;
        int _step;
        public Key(int pos, int step) {
            _pos = pos;
            _step = step;
        }

        @Override
        public int hashCode() {
            return 31 * _pos + _step;
        }

        @Override
        public boolean equals(Object that) {
            return that != null && that instanceof Key &&
                    ((Key)that)._pos == _pos &&
                    ((Key)that)._step == _step;
        }
    }
}
// 39 / 39 test cases passed.
// Runtime: 34 ms


