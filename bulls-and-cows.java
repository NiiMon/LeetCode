/*

299. Bulls and Cows
https://leetcode.com/problems/bulls-and-cows/description/


You are playing the following Bulls and Cows game with your friend: You write
down a number and ask your friend to guess what the number is. Each time your
friend makes a guess, you provide a hint that indicates how many digits in
said guess match your secret number exactly in both digit and position (called
"bulls") and how many digits match the secret number but locate in the wrong
position (called "cows"). Your friend will use successive guesses and hints to
eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's
guess, use A to indicate the bulls and B to indicate the cows.

Please note that both secret number and friend's guess may contain duplicate
digits.

Example 1:
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

Example 2:
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.

Note: You may assume that the secret number and your friend's guess only
contain digits, and their lengths are always equal. 

*/

// version 1
class Solution {
    public String getHint(String secret, String guess) {
        int a = 0;
        int b = 0;
        StringBuilder sbSecret = new StringBuilder();
        StringBuilder sbGuess = new StringBuilder();

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                a++;
            } else {
                sbSecret.append(secret.charAt(i));
                sbGuess.append(guess.charAt(i));
            }
        }

        secret = sbSecret.toString();
        guess = sbGuess.toString();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : secret.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (map.containsKey(c) && map.get(c) > 0) {
                b++;
                map.put(c, map.get(c) - 1);
            }
        }

        return a + "A" + b + "B";
    }
}
// 152 / 152 test cases passed.
// Runtime: 8 ms


// version 2
/*
The idea is to count the un-matched numbers for secret and guess.  
Then minus paired counts.
 */
class Solution {
    public String getHint(String secret, String guess) {
        int a = 0;
        int b = 0;
        int[] countS = new int[10];
        int[] countG = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char cs = secret.charAt(i);
            char cg = guess.charAt(i);
            if (cs == cg) {
                a++;
            } else {
                countS[cs - '0']++;
                b += helper(countS, countG, cs - '0');

                countG[cg - '0']++;
                b += helper(countS, countG, cg - '0');
            }
        }

        return a + "A" + b + "B";
    }
    private int helper(int[] countS, int[] countG, int index) {
        int result = 0;
        if (countS[index] > 0 && countG[index] > 0) {
            result++;
            countS[index]--;
            countG[index]--;
        }
        return result;
    }
}
// 152 / 152 test cases passed.
// Runtime: 2 ms


// version 3
/*
Same idea as version 2, but combine two counter arrays into one.
Use +/- to represent two directions.
In version 2, we add one count in each counter array.
But in version 3, we use plus for secret, minus for guess.
 */
class Solution {
    public String getHint(String secret, String guess) {
        int a = 0;
        int b = 0;
        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                a++;
            } else {
                // for s, we look for a negative number to make a pair
                // then add one to cross off the pair
                if (count[s - '0']++ < 0) {
                    b++;
                }
                // for g, we look for a positive number to make a pair
                // if pair is found, minus one to pair
                if (count[g - '0']-- > 0) {
                    b++;
                }
            }
        }

        return a + "A" + b + "B";
    }
}
// 152 / 152 test cases passed.
// Runtime: 1 ms

