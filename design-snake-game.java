/*

353. Design Snake Game
https://leetcode.com/problems/design-snake-game/description/


Design a Snake game that is played on a device with screen size = width x
height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1
unit.

You are given a list of food's positions in row-column order. When a snake
eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will
not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not
appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that,
the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

*/

/*
Test cases:
["SnakeGame","move","move","move","move","move","move"]
[[33,33,[[1,2],[0,1],[11,11]]],["R"],["D"],["R"],["U"],["L"],["U"]]
["SnakeGame","move"]
[[1,1,[]],["R"]]
["SnakeGame","move","move"]
[[2,2,[[0,1]]],["R"],["D"]]

corner case:
["SnakeGame","move","move","move","move","move","move","move","move","move","move","move","move"]
[[3,3,[[2,0],[0,0],[0,2],[2,2]]],["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["D"]]

*/

class SnakeGame {

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    Deque<Position> body;
    Set<Position> bodySet;
    boolean alive;
    int foodIndex;
    int _width;
    int _height;
    int[][] _food;
    public SnakeGame(int width, int height, int[][] food) {
        body = new LinkedList<>();
        bodySet = new HashSet<>();
        alive = true;
        foodIndex = 0;
        _width = width;
        _height = height;
        _food = food;

        Position pos = new Position(0, 0);
        body.addFirst(pos);
        bodySet.add(pos);
    }

    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
        @return The game's score after the move. Return -1 if game over.
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if (!alive) {
            return 0;
        }

        Position head = body.peekFirst();
        int dx = 0;
        int dy = 0;
        if (direction.equals("U")) {
            dx--;
        }
        if (direction.equals("D")) {
            dx++;
        }
        if (direction.equals("L")) {
            dy--;
        }
        if (direction.equals("R")) {
            dy++;
        }
        Position newP = new Position(head._x + dx, head._y + dy);

        if (!isValid(newP)) {
            alive = false;
            return -1;
        }

        // food
        if (foodIndex < _food.length &&
            newP._x == _food[foodIndex][0] && newP._y == _food[foodIndex][1]) {
            foodIndex++;
        } else {
            bodySet.remove(body.removeLast());
        }

        // add new head
        body.addFirst(newP);
        bodySet.add(newP);

        return body.size() - 1;
    }

    private boolean isValid(Position pos) {
        return pos._x >= 0 && pos._x < _height &&
                pos._y >= 0 && pos._y < _width &&
                (pos.equals(body.peekLast()) || !bodySet.contains(pos));
    }

    class Position {
        int _x;
        int _y;
        public Position(int x, int y) {
            _x = x;
            _y = y;
        }

        @Override
        public int hashCode() {
            return 31 * _x + _y;
        }

        @Override
        public boolean equals(Object that) {
            return that != null && that instanceof Position &&
                ((Position)that)._x == _x && ((Position)that)._y == _y;
        }

        @Override
        public String toString() {
            return "(" + _x + "," + _y + ")";
        }
    }
}
// 539 / 539 test cases passed.
// Runtime: 158 ms


/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */


