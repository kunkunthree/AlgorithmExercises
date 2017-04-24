package algorithm.graph;
/*
 * lingjiebiao
 */
public class Node {
	public int data;
	public Node next;
	public int adjvex;
	public Node() {}
	public Node(int data,Node next) {
		this.data = data;
		this.next = next;
	}
}
