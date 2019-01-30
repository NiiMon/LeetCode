/*

113. Path Sum II
https://leetcode.com/problems/path-sum-ii/description/


Given a binary tree and a sum, find all root-to-leaf paths where each path's sum
equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]


*/




/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// dfs bottom-up
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        // base case: leaf nodes
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                List<Integer> list = new LinkedList<>();
                list.add(root.val);
                result.add(list);
            }
            return result;
        }
        
        // general case: non-leaf nodes
        result.addAll(root.left == null ? new ArrayList<>() : 
                                        pathSum(root.left, sum - root.val));
        result.addAll(root.right == null ? new ArrayList<>() : 
                                        pathSum(root.right, sum - root.val));
        
        for (List<Integer> path : result) {
            path.add(0, root.val);
        }
        
        return result;
    }
}
// 114 / 114 test cases passed.
// Runtime: 4 ms



// dfs top-down
// tree height fits in cache, so call stack won't overflow
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList();
        if (root == null) {
            return result;
        }
        Stack<Integer> path = new Stack<>();
        dfs(root, sum, path, result);
        return result;
    }
    private void dfs(TreeNode node, int sum, Stack<Integer> path, List<List<Integer>> result) {
        // 1. op at node
        path.push(node.val);

        // 2. op at leaf node
        if (node.left == null && node.right == null && sum == node.val) {
            result.add(new ArrayList<>(path));
        }

        // 3. go down to children
        if (node.left != null) {
            dfs(node.left, sum - node.val, path, result);
        }
        if (node.right != null) {
            dfs(node.right, sum - node.val, path, result);
        }

        // 4. go up to parent
        path.pop();
    }
}
// 114 / 114 test cases passed.
// Runtime: 2 ms


// dfs top-down
// tree height won't fit in cache but fits in memory
class Solution {
    class MyNode {
        TreeNode _node;
        int _height;
        int _need;
        public MyNode(TreeNode node, int height, int need) {
            _node = node;
            _height = height;
            _need = need;
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        Stack<MyNode> stack = new Stack<>();
        stack.push(new MyNode(root, 0, sum));
        while (!stack.isEmpty()) {
            MyNode node = stack.pop();
            
            // add to path
            if (node._height == path.size()) {
                path.add(node._node.val);
            } else {
                path.set(node._height, node._node.val);
            }
            
            // op at leaf
            if (node._node.left == null && node._node.right == null) {
                if (node._need == node._node.val) {
                    result.add(new ArrayList<>(path.subList(0, node._height + 1)));
                }
            }
            
            // generate children
            if (node._node.left != null) {
                stack.push(new MyNode(node._node.left, node._height + 1, node._need - node._node.val));
            }
            if (node._node.right != null) {
                stack.push(new MyNode(node._node.right, node._height + 1, node._need - node._node.val));
            }
        }
        
        return result;
    }
}
// 114 / 114 test cases passed.
// Runtime: 6 ms


// BFS
class Solution {
    class MyNode {
        TreeNode _node;
        List<Integer> _path;
        int _need;
        public MyNode(TreeNode node, List<Integer> path, int need) {
            _node = node;
            _path = path;
            _need = need;
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(new MyNode(root, new ArrayList<>(), sum));
        
        while (!queue.isEmpty()) {
            MyNode node = queue.remove();
            
            // op at node
            node._path.add(node._node.val);
            
            // op at leaf node
            if (node._node.left == null && node._node.right == null) {
                if (node._node.val == node._need) {
                    result.add(node._path);
                }
            }
            
            // generate children
            if (node._node.left != null) {
                queue.add(new MyNode(node._node.left, new ArrayList<>(node._path), node._need - node._node.val));
            }
            if (node._node.right != null) {
                queue.add(new MyNode(node._node.right, new ArrayList<>(node._path), node._need - node._node.val));
            }
        }
        return result;
    }
}
// 114 / 114 test cases passed.
// Runtime: 8 ms
// every node saves root-to-node path, even if it won't be an answer
// waste of space


// BFS
// for each node, remember its parent
// when found an answer, walk backwards to generate root-to-node path
class Solution {
    class MyNode {
        TreeNode _node;
        MyNode _parent;
        int _need;
        public MyNode(TreeNode node, MyNode parent, int need) {
            _node = node;
            _parent = parent;
            _need = need;
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<MyNode> queue = new LinkedList<>();
        queue.add(new MyNode(root, null, sum));
        
        while (!queue.isEmpty()) {
            MyNode node = queue.remove();
            
            // op at node
            // do nothing
            
            // op at leaf node
            if (node._node.left == null && node._node.right == null) {
                if (node._node.val == node._need) {
                    result.add(walker(node));
                }
            }
            
            // generate children
            if (node._node.left != null) {
                queue.add(new MyNode(node._node.left, node, node._need - node._node.val));
            }
            if (node._node.right != null) {
                queue.add(new MyNode(node._node.right, node, node._need - node._node.val));
            }
        }
        return result;
    }
    private List<Integer> walker(MyNode end) {
        List<Integer> result = new LinkedList<>();
        if (end == null) {
            return result;
        }
        
        while (end != null) {
            result.add(0, end._node.val);
            end = end._parent;
        }
        
        return result;
    } 
}
// 114 / 114 test cases passed.
// Runtime: 3 ms


