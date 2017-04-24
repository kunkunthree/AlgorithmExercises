package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 3.4	从无头单链表中删除节点：
 * 		假设有一个没有头指针的单链表，一个指针指向此单链表中的一个节点（不是第一个也不是最后一个），
 * 请将该节点从单链表中删除。
 */
import java.util.*;
public class DeleteNodeFromNoHeadSinglyLinkedList {
	public class Node{
		Node next;
		int data;
		public Node(Node next,int data) {
			this.next = next;
			this.data = data;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		DeleteNodeFromNoHeadSinglyLinkedList list = new DeleteNodeFromNoHeadSinglyLinkedList();
		Node first = list.new Node(null,-1);
		Node node = first; 
		for(int i = 0 ; i < n ; i++){
			node.next = list.new Node(null,i);
			node = node.next;
		}
		printList(first);
		Node tmp = first.next.next.next;
		System.out.println(tmp.data);
		deleteNode(tmp);
		printList(first);
	}
	public static void deleteNode(Node node){
		Node next = node.next;
		node.next = next.next;
		node.data = next.data;
		next.next = null;
	}
	public static void printList(Node head){
		while(head!=null){
			System.out.print(head.data + " , ");
			head = head.next;
		}
		System.out.println();
	}
}
