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
        if (coins.length == 0) {
            return 0;
        }

        Arrays.sort(coins);

        return dfs(coins, coins.length - 1, amount, new HashMap<>());
    }
    private int dfs(int[] coins, int index, int amount, 
                    Map<Key, Integer> saved) {
        Key key = new Key(index, amount);
        if (saved.containsKey(key)) {
            return saved.get(key);
        }

        int result = 0;

        if (amount != 0 && amount < coins[0] || index < 0) {
            // do nothing
        } else if (amount == 0) {
            result = 1;
        } else {
            result += dfs(coins, index, amount - coins[index], saved) +
                      dfs(coins, index - 1, amount, saved);
        }

        saved.put(key, result);
        return result;
    }
    class Key {
        int _amount;
        int _index;
        public Key(int amount, int index) {
            _amount = amount;
            _index = index;
        }
        public int hashCode() {
            return _index * 31 + _amount;
        }
        public boolean equals(Object that) {
            return that instanceof Key &&
                    ((Key)that)._index == _index &&
                    ((Key)that)._amount == _amount;
        }
    }
}
// 27 / 27 test cases passed.
// Status: Accepted
// Runtime: 35 ms
// Memory Usage: 37.1 MB

