/*

286. Walls and Gates
https://leetcode.com/problems/walls-and-gates/description/

You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to
represent INF as you may assume that the distance to a gate is less than
2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible
to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*/

class Solution {
    
    final static int GATE = 0;
    final static int ROOM = Integer.MAX_VALUE;
    
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        
        final int n = rooms.length;
        final int m = rooms[0].length;
        
        Queue<Cell> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == GATE) {
                    queue.add(new Cell(i, j, 0));
                }
            }
        }
        
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            Cell cur = queue.remove();
            for (int k = 0; k < dx.length; k++) {
                int x = cur._x + dx[k];
                int y = cur._y + dy[k];
                int d = cur._distance + 1;
                if (x >= 0 && x < n &&
                    y >= 0 && y < m &&
                    rooms[x][y] == ROOM) {
                    rooms[x][y] = d;
                    queue.add(new Cell(x, y, d));
                }
            }
        }
    }
    
    class Cell {
        int _x;
        int _y;
        int _distance;
        public Cell(int x, int y, int distance) {
            _x = x;
            _y = y;
            _distance = distance;
        }
        
        @Override
        public int hashCode() {
            return 31 * (31 * _x + _y) + _distance;
        }
        
        @Override
        public boolean equals(Object that) {
            return that != null && that instanceof Cell &&
                ((Cell)that)._x == _x &&
                ((Cell)that)._y == _y &&
                ((Cell)that)._distance == _distance;
        }
    }
}
// 62 / 62 test cases passed.
// Runtime: 12 ms


