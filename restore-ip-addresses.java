/*

93. Restore IP Addresses
https://leetcode.com/problems/restore-ip-addresses/description/

Given a string containing only digits, restore it by returning all possible 
valid IP address combinations.

Example:
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

*/

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();

        dfs(s, "", 0, 4, new Stack<>(), result);

        return result;
    }
    private void dfs(String s, String ip, int startIndex, int minLength, Stack<String> path, 
                     List<String> result) {
        
        if (minLength < 4) {
            // 1. op at node
            path.push(ip);

            // 2. op at leaf
            if (minLength == 0) {
                result.add(String.join(".", path));
            }
        }

        // 3. go down to children
        for (int i = 1; i <= 3 && startIndex + i <= s.length(); i++) {
            String childIP = s.substring(startIndex, startIndex + i);
            int childMinLength = minLength - 1;
            int childLength = s.length() - startIndex - i;
            if (isValid(childIP) && 
                childLength >= childMinLength && childLength <= childMinLength * 3) {
                dfs(s, childIP, startIndex + childIP.length(), childMinLength, path, result);
            }
        }
        // 4. to up to parent
        if (minLength < 4) {
            path.pop();
        }
    }
    private boolean isValid(String s) {
        int value = Integer.valueOf(s);
        return value >= 0 && value <= 255 && (s.length() == 1 || s.charAt(0) != '0');
    }
}
// 147 / 147 test cases passed.
// Runtime: 3 ms

