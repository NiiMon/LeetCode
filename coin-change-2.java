/*

518. Coin Change 2
https://leetcode.com/problems/coin-change-2/description/

You are given coins of different denominations and a total amount of money.
Write a function to compute the number of combinations that make up that amount.
You may assume that you have infinite number of each kind of coin.

Note: You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
 

Example 1:
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
 

Example 3:
Input: amount = 10, coins = [10] 
Output: 1

*/


// Graph DFS Bottom-Up
class Solution {
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        Map<Key, Integer> saved = new HashMap<>();
        return dfs(coins.length - 1, amount, coins, saved);
    }

    private int dfs(int index, int amount, int[] coins, Map<Key, Integer> saved) {

        Key node = new Key(index, amount);
        if (saved.containsKey(node)) {
            return saved.get(node);
        }

        // base case: leaf
        if (index >= 0 && amount == 0) {
            return 1;
        }
        if (index < 0 || amount < 0) {
            return 0;
        }

        // general case: non-leaf
        int leftResult = dfs(index, amount - coins[index], coins, saved);
        int rightResult = dfs(index - 1, amount, coins, saved);
        int num = leftResult + rightResult;

        saved.put(node, num);
        return num;
    }
    class Key {
        int _index;
        int _amount;
        public Key(int index, int amount) {
            _index = index;
            _amount = amount;
        }

        @Override
        public int hashCode() {
            return 31 *_amount + _index;
        }

        @Override
        public boolean equals(Object that) {
            return that != null && that instanceof Key &&
                    ((Key)that)._index == _index &&
                    ((Key)that)._amount == _amount;
        }
    }
}
// 27 / 27 test cases passed.
// Runtime: 47 ms

