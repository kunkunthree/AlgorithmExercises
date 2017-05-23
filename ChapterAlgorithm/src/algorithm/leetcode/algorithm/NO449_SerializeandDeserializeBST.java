package algorithm.leetcode.algorithm;

import java.util.Arrays;

/*
 * medium
 * 449. Serialize and Deserialize BST
 * Serialization is the process of converting a data structure or object into a sequence of bits 
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection link 
 * to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary search tree can be serialized to a string 
and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless. 
 */
public class NO449_SerializeandDeserializeBST {
	public static void main(String[] args) {
		TreeNode root = TreeNode.getTree(new Object[]{41,6,8469,0,22,6336,9177,null});
//		System.out.println(serialize(root));
//		System.out.println(deserialize(serialize(root)));
		StringBuilder sb = new StringBuilder();
		sb.append("123456");
		sb.delete(5, 6);
		System.out.println(sb.toString());
		String s = "";
		String[] ss = s.split(",");
		System.out.println(ss.length);
		System.out.println(Arrays.toString(ss));
	}
	//方法1：
	//利用数组存储，缺点，节点过多会导致数组过大超出范围
	// Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
    	if(root == null){
            return "null";
        }
        int depth = getDepth(root);
        TreeNode[] nodes = new TreeNode[1<<depth];
        nodes[1] = root;
        StringBuilder result = new StringBuilder();
        for(int i = 1 ; i < nodes.length ; i++){
            if(nodes[i] != null){
                result.append(nodes[i].val);
                if(i*2+1 < nodes.length){
                    nodes[i*2] = nodes[i].left;
                    nodes[i*2+1] = nodes[i].right;
                }
            }else{
                result.append("null");
            }
            if(i < nodes.length-1){
                result.append(",");
            }
        }
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data == null || data.length() == 0){
            return null;
        }
        String[] ss = data.split(",");
        int n = ss.length,num = 0;
        TreeNode[] nodes = new TreeNode[n+1];
        for(int i = 0 ; i < n ; i++){
            if(!ss[i].equals("null")){
                num = Integer.valueOf(ss[i]);
                nodes[i+1] = new TreeNode(num);
                if(nodes[(i+1)/2] != null){
                    if((i&1) == 1){
                        nodes[(i+1)/2].left = nodes[i+1];
                    }else{
                        nodes[(i+1)/2].right = nodes[i+1];
                    }
                }
            }
        }
        return nodes[1];
    }
    
    private static int getDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        return  1+Math.max(getDepth(root.left),getDepth(root.right));
    }
    
    //方法2：
    //利用先根遍历所有非空节点得到用","分隔的字符串，再用上下界分隔的方法来确定子树的范围
    //O(n)time,O(1)space
    public class Codec {
        public static final int RADIX = 16;	//存储数字所用进制
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root,sb);
            if(sb.length() > 0)sb.delete(sb.length()-1,sb.length());
            return sb.toString();
        }
        private void serialize(TreeNode root,StringBuilder sb){
            if(root == null)return;
            sb.append(Integer.toString(root.val,RADIX)).append(",");
            serialize(root.left,sb);
            serialize(root.right,sb);
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == null || data.length() == 0){
                return null;
            }
            String[] ss = data.split(",");
            int[] pos = new int[]{0};
            return deserialize(ss,Integer.MIN_VALUE,Integer.MAX_VALUE,pos);
        }
        
        private TreeNode deserialize(String[] ss,int lower,int upper,int[] pos){
            if(pos[0] == ss.length){
                return null;
            }
            int num = Integer.valueOf(ss[pos[0]],RADIX);
            if(num < lower || num > upper){
                return null;
            }
            TreeNode root = new TreeNode(num);
            pos[0]++;
            root.left = deserialize(ss,lower,num,pos);
            root.right = deserialize(ss,num,upper,pos);
            return  root;
        }
    }
    
    //方法3：
    //用1个char的第1位和第2位作为flag表示左右子树是否为非空
    //紧接着用4个char表示节点大小
    //同样时用先根遍历所有非空节点
    public class Codec2 {  
    	  
        private void code(TreeNode node, StringBuilder coded) {  
            if (node == null) return;  
            char flag = (char)0;  
            if (node.left != null) flag |= (char)1;  
            if (node.right != null) flag |= (char)2;  
            coded.append(flag);  
            for(int i = 0, val = node.val; i < 4; i++, val >>>= 8) {  
                coded.append((char)(val & 0xff));  
            }  
            code(node.left, coded);  
            code(node.right, coded);  
        }  
      
        private TreeNode decode(String data, int[] pos) {  
            if (pos[0] >= data.length()) return null;  
            char flag = data.charAt(pos[0]++);  
            int val = 0;  
            for(int i = 0; i < 4; i++) {  
                val |= (data.charAt(pos[0]++) << (i * 8));  
            }  
            TreeNode node = new TreeNode(val);  
            if ((flag & 1) != 0) {  
                node.left = decode(data, pos);  
            }  
            if ((flag & 2) != 0) {  
                node.right = decode(data, pos);  
            }  
            return node;  
        }  
      
        // Encodes a tree to a single string.  
        public String serialize(TreeNode root) {  
            if (root == null) return "";  
            StringBuilder coded = new StringBuilder();  
            code(root, coded);  
            return coded.toString();  
        }  
      
        // Decodes your encoded data to tree.  
        public TreeNode deserialize(String data) {  
            return decode(data, new int[] {0});  
        }  
    }
    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
}
