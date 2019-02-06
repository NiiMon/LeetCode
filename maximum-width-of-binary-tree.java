/*

662. Maximum Width of Binary Tree
https://leetcode.com/problems/maximum-width-of-binary-tree/description/

Given a binary tree, write a function to get the maximum width of the given tree. 
The width of a tree is the maximum width among all levels. The binary tree has the 
same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost
and right most non-null nodes in the level, where the null nodes between the 
end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: 
The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: 
The maximum width existing in the third level with the length 2 (5,3).

Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: 
The maximum width existing in the second level with the length 2 (3,2).

Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:
The maximum width existing in the fourth level with the length 8 
(6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.

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
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<MyNode> deque = new LinkedList<>();
        deque.add(new MyNode(root, 0));
        int max = 0;
        while (deque.size() > 0) {
            max = Math.max(max, deque.peekLast()._index - deque.peekFirst()._index + 1);
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                MyNode node = deque.removeFirst();
                if (node._treeNode.left != null) {
                    deque.addLast(new MyNode(node._treeNode.left, node._index * 2 + 1));
                }
                if (node._treeNode.right != null) {
                    deque.addLast(new MyNode(node._treeNode.right, node._index * 2 + 2));
                }
            }
        }

        return max;
    }
    class MyNode {
        TreeNode _treeNode;
        int _index;
        public MyNode(TreeNode node, int index) {
            _treeNode = node;
            _index = index;
        }
    }
}
// 108 / 108 test cases passed.
// Status: Accepted
// Runtime: 5 ms
// Memory Usage: 26.4 MB

