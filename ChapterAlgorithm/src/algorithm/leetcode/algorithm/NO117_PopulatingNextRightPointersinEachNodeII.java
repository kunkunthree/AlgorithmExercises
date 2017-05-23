package algorithm.leetcode.algorithm;
/*
 * medium
 * 117. Populating Next Right Pointers in Each Node II
 * Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

    You may only use constant extra space.

For example,
Given the following binary tree,

         1
       /  \
      2    3
     / \    \
    4   5    7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \     	\
    4-> 5 -> 7 -> NULL

 */
public class NO117_PopulatingNextRightPointersinEachNodeII {
	//方法1：
	//递归DFS，先遍历右子树节点，再遍历左子树节点
	//因为需要先把右子树的节点连接起来，再把左子树节点和右子树节点连接在一起时才不会断裂
	//在连接时，
	//如果存在子节点，那么把最右边的子节点指向下一个该层的节点，不断遍历根节点的next，直到存在子节点为止
	//如果左右子节点都存在，那么就把左子节点的next指向右子节点
	//递归遍历右子树节点，递归遍历左子树节点
	public void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }
        if(root.left != null && root.right != null){
            root.left.next = root.right;
        }
        if(root.left != null || root.right != null){
            TreeLinkNode tmp = root.next;
            while(tmp != null){
                if(tmp.right != null || tmp.left != null){
                    break;
                }
                tmp = tmp.next;
            }
            if(tmp != null){
                if(root.right != null){
                    root.right.next = tmp.left == null ? tmp.right : tmp.left;
                }else{
                    root.left.next = tmp.left == null ? tmp.right : tmp.left;
                }
            }
        }
        connect(root.right);
        connect(root.left);
    }
	
	//方法2：
	//迭代形式
	//对每一层进行迭代，对每一层的节点从左往右，只对存在子节点的节点进行连接操作
	//用一个指针head保存下一层存在第一子节点的节点，用指针nextNode保存每一层遍历的下一个存在子节点的节点
	public void connect2(TreeLinkNode root) {
        TreeLinkNode head,nextNode;
        while(root != null){
            head = null;
            //遍历一层
            while(root != null){
                //只对该行存在子节点的节点进行操作
                if(root.left != null || root.right != null){
                    //记录第一个非空节点
                    if(head == null){
                        head = root.left != null ? root.left : root.right;
                    }
                    //如果left和right都存在，则进行相连
                    if(root.left != null && root.right != null){
                         root.left.next = root.right;
                    }
                    //tmp用于指向该层下一个存在子节点的节点，如果tmp为null，则说明没有这样的节点
                    nextNode = root.next;
                    while(nextNode != null){
                        if(nextNode.left != null || nextNode.right != null){
                            break;
                        }
                        nextNode = nextNode.next;
                    }
                    //如果存在下一个有子节点节点，则进行连接
                    if(nextNode != null){
                        if(root.right != null){
                            root.right.next = nextNode.left == null ? nextNode.right : nextNode.left;
                        }else{
                            root.left.next = nextNode.left == null ? nextNode.right : nextNode.left;
                        }
                    }
                    //指向下一个可能有子节点的节点
                    root = nextNode;
                }else{
                    //当还没找到存在子节点的节点时，一直在该层往下找
                    root = root.next;
                }
            }
            //指向下一层的第一个可能存在子节点的节点
            root = head;
        }
    }
}
