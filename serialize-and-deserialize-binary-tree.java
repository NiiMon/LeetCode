/*

297. Serialize and Deserialize Binary Tree
https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/



Serialization is the process of converting a data structure or object into a
sequence of bits so that it can be stored in a file or memory buffer, or
transmitted across a network connection link to be reconstructed later in the
same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no
restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and
this string can be deserialized to the original tree structure.

Example: 
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

Clarification: The above format is the same as how LeetCode serializes a
binary tree. You do not necessarily need to follow this format, so please be
creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your
serialize and deserialize algorithms should be stateless.

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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> result = new ArrayList<>();
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node == null) {
                    result.add("#");
                } else {
                    result.add(node.val + "");
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }

        return String.join(",", result);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] input = data.split(",");

        int childIndex = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(input[0]));
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode node = queue.remove();
            if (!input[childIndex].equals("#")) {
                node.left = new TreeNode(Integer.valueOf(input[childIndex]));
                queue.add(node.left);
            }
            if (!input[childIndex + 1].equals("#")) {
                node.right = new TreeNode(Integer.valueOf(input[childIndex + 1]));
                queue.add(node.right);
            }
            childIndex += 2;
        }
        return root;
    }
}
// 48 / 48 test cases passed.
// Status: Accepted
// Runtime: 17 ms
// Memory Usage: 23.4 MB


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


