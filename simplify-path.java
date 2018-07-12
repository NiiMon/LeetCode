/*

71. Simplify Path
https://leetcode.com/problems/simplify-path/description/


Given an absolute path for a file (Unix-style), simplify it.  Note that
absolute path always begin with ‘/’ ( root directory ), a dot in path
represent current directory and double dot represents parent directory.

Examples:

"/a/./" --> means stay at the current directory 'a'
"/a/b/.." --> means jump to the parent directory
from 'b' to 'a'
"////" --> consecutive multiple '/' are a valid
path, they are equivalent to single "/".

Input : /home/
Output : /home

Input : /a/./b/../../c/
Output : /c

Input : /a/..
Output : /

Input : /a/../
Ouput : /

Input : /../../../../../a
Ouput : /a

Input : /a/./b/./c/./d/
Ouput : /a/b/c/d

Input : /a/../.././../../.
Ouput : /

Input : /a//b//c//////d
Ouput : /a/b/c/d

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together,
such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".

*/


class Solution {
    public String simplifyPath(String path) {
        String[] array = path.split("/");
        List<String> list = new ArrayList<>();
        for (String s : array) {
            if (s.equals("") || s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                if (list.size() > 0) {
                    list.remove(list.size() - 1);
                }
            } else {
                list.add(s);
            }
        }

        if (list.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append("/" + s);
        }

        return sb.toString();
    }
}

// 252 / 252 test cases passed.
// Runtime: 6 ms

