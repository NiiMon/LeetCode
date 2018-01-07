/*

134. Gas Station
https://leetcode.com/problems/gas-station/description/


There are N gas stations along a circular route, where the amount of gas at
station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to
travel from station i to its next station (i+1). You begin the journey with an
empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit
once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 0 || cost.length == 0) {
            return -1;
        }
        
        int start = 0;
        int left = 0;
        int need = 0;
        
        for (int i = 0; i < gas.length; i++) {
            left += gas[i] - cost[i];
            if (left < 0) {
                need -= left;
                left = 0;
                start = i + 1;
            }
        }
        
        return left >= need ? start : -1;
    }
}

