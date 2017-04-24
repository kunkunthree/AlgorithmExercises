package algorithm.microsoft.beauty.of.programming.chapter3;
/*
 * 3.6	变成判断两个链表是否相交：
 * 		给出两个单项链表的头指针，比如h1,h2，判断这两个链表是否相交。假设两个链表不带环。
 */
import xie.util.*;
public class JudgingLinkedListIntersected {
	public static boolean isLinkedListsIntersected(Node list1,Node list2){
		while(list1.next != null){
			list1 = list1.next;
		}
		while(list2.next != null){
			list2 = list2.next;
		}
		if(list1 == list2){
			return true;
		}else{
			return false;
		}
	}
}
