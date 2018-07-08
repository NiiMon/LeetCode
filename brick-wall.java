/*

554. Brick Wall
https://leetcode.com/problems/brick-wall/description/

There is a brick wall in front of you. The wall is rectangular and has several
rows of bricks. The bricks have the same height but different width. You want
to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of
integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered
as crossed. You need to find out how to draw the line to cross the least
bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall,
in which case the line will obviously cross no bricks.

Example:
Input: 
[[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
Output: 2
Explanation: 
[img]https://leetcode.com/static/images/problemset/brick_wall.png

Note:
The width sum of bricks in different rows are the same and won't exceed
INT_MAX.

The number of bricks in each row is in range [1,10,000]. The height of wall is
in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.

*/

// version 1
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        List<Queue<Integer>> queue = new ArrayList<>();
        for (int i = 0; i < wall.size(); i++) {
            queue.add(new LinkedList<Integer>());
        }

        // provide cuts
        int rowSum = 0;
        Set<Integer> set = new HashSet<>();
        for (int row = 0; row < wall.size(); row++) {
            int sum = 0;
            for (int width : wall.get(row)) {
                sum += width;
                queue.get(row).offer(sum);
                set.add(sum);
            }
            rowSum = sum;
        }

        PriorityQueue<Integer> cuts = new PriorityQueue<>();
        for (int cut : set) {
            cuts.add(cut);
        }

        // test each cut
        int minCuts = wall.size();
        while (cuts.size() > 1) {
            int cut = cuts.remove();
            int cutCount = 0;
            for (int row = 0; row < wall.size(); row++) {
                if (cut == queue.get(row).peek()) {
                    queue.get(row).poll();
                } else if (cut < queue.get(row).peek()) {
                    cutCount++;
                }
            }
            minCuts = Math.min(minCuts, cutCount);
        }

        return minCuts;
    }
}
// 85 / 85 test cases passed.
// Runtime: 956 ms


// version 2
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int minCuts = wall.size();
        Map<Integer, Integer> map = new HashMap<>();

        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);

                // counter add by 1
                map.put(sum, map.getOrDefault(sum, 0) + 1);

                // keep track of the greatest count to provide minCuts
                minCuts = Math.min(minCuts, wall.size() - map.get(sum));
            }
        }

        return minCuts;
    }
}
// 85 / 85 test cases passed.
// Runtime: 20 ms
