/*

308. Range Sum Query 2D - Mutable
https://leetcode.com/problems/range-sum-query-2d-mutable/

Given a 2D matrix matrix, find the sum of the elements inside the rectangle
defined by its upper left corner (row1, col1) and lower right corner (row2,
col2).

![](https://leetcode.com/static/images/courses/range_sum_query_2d.png)

The above rectangle (with the red border) is defined by (row1, col1) = (2, 1)
and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10

Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is
distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.

*/


/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */

class NumMatrix {

    class SegTree {
        int _sr;
        int _sc;
        int _er;
        int _ec;
        int _sum;
        SegTree[] _children;
        public SegTree(int sr, int sc, int er, int ec) {
            _sr = sr;
            _sc = sc;
            _er = er;
            _ec = ec;
            _sum = 0;
            _children = new SegTree[4];
        }
        public SegTree(int sr, int sc, int er, int ec, int sum) {
            this(sr, sc, er, ec);
            _sum = sum;
        }
    }

    SegTree root;

    public NumMatrix(int[][] matrix) {
        if (matrix.length > 0 && matrix[0].length > 0) {
            root = build(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
        }
    }
    private SegTree build(int[][] matrix, int sr, int sc, int er, int ec) {
        if (sr > er || sc > ec) {
            return null;
        }

        if (sr == er && sc == ec) {
            return new SegTree(sr, sc, er, ec, matrix[sr][sc]);
        }

        int mr = sr + (er - sr) / 2;
        int mc = sc + (ec - sc) / 2;

        SegTree root = new SegTree(sr, sc, er, ec);
        root._children[0] = build(matrix, sr, sc, mr, mc);
        root._children[1] = build(matrix, sr, mc + 1, mr, ec);
        root._children[2] = build(matrix, mr + 1, sc, er, mc);
        root._children[3] = build(matrix, mr + 1, mc + 1, er, ec);
        
        for (SegTree child : root._children) {
            if (child != null) {
                root._sum += child._sum;
            }
        }

        return root;
    }
    
    public void update(int row, int col, int val) {
        update(root, row, col, val);
    }
    private void update(SegTree node, int row, int col, int val) {
        if (node == null) {
            return;
        }
        if (node._sr == node._er && node._sr == row &&
            node._sc == node._ec && node._sc == col) {
            node._sum = val;
            return;
        }

        int mr = node._sr + (node._er - node._sr) / 2;
        int mc = node._sc + (node._ec - node._sc) / 2;

        if (row <= mr && col <= mc) {
            update(node._children[0], row, col, val);
        } else if (row <= mr) {
            update(node._children[1], row, col, val);
        } else if (col <= mc) {
            update(node._children[2], row, col, val);
        } else {
            update(node._children[3], row, col, val);
        }

        int sum = 0;
        for (SegTree child : node._children) {
            if (child != null) {
                sum += child._sum;
            }
        }
        node._sum = sum;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumRegion(root, row1, col1, row2, col2);
    }
    private int sumRegion(SegTree node, int row1, int col1, 
                          int row2, int col2) {
        if (node._ec < col1 ||
            node._er < row1 ||
            node._sr > row2 ||
            node._sc > col2) {
            return 0;
        } else if (node._sr >= row1 && node._sc >= col1 &&
                   node._er <= row2 && node._ec <= col2) {
            return node._sum;
        } else {
            int sum = 0;
            for (SegTree child : node._children) {
                if (child != null) {
                    sum += sumRegion(child, row1, col1, row2, col2);
                }
            }
            return sum;
        }
    }
}
// 17 / 17 test cases passed.
// Status: Accepted
// Runtime: 73 ms
// Memory Usage: 52.6 MB


