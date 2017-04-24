package algorithm.leetcode.algorithm;
/*
 * easy
 * 160. Intersection of Two Linked Lists
 * 求两个链表相交的第一个公共节点
 * Write a program to find the node at which the intersection of two singly linked lists begins.
For example, the following two linked lists:
A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3

begin to intersect at node c1.

Notes:

    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
    Your code should preferably run in O(n) time and use only O(1) memory.

 */
public class NO160_IntersectionofTwoLinkedLists {
	//算法的空间复杂度O(1)，时间复杂度O(m+n)
	//我们定义节点的距离为节点到链表开始所经过的节点数。
	//如果两个链表长度相同，则相交节点其在两个链表上的距离一定相等。
	//对于长度不同的两个链表，我们可以采用对齐的方式，使其向长度短的链表对齐。
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        int len = lenA - lenB;
        if(len < 0){    //lenA < lenB
            while(len < 0){
                headB = headB.next;
                len++;
            }
        }else{          //lenA > lenB
            while(len > 0){
                headA = headA.next;
                len--;
            }
        }
        while(headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
    public int getLength(ListNode node){
        int i = 0;
        while(node != null){
            i++;
            node = node.next;
        }
        return i;
    }
    //还有更优的办法，不需要知道两个链表的长度，只需要让两个指针遍历a+b+c个节点就可以
    //假设c是单向链表A和B公共链的部分的长度，那么a是属于链表A而不属于链表B的长度，
    //b是是属于链表B而不属于链表A的长度，那么让指针1从a头部开始遍历，指针2从b头部开始遍历
    //当A和B长度不一样时，两个指针都会遍历到尾部的null，当访问到尾部时，就让该指针从另一个链表头部开始，
    //那么最终会在第一个公共节点处汇合，因为两个指针遍历的长度都是a+b+c。
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;
        
        ListNode a = headA;
        ListNode b = headB;
        
        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
        	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;    
        }
        
        return a;
    }
}
