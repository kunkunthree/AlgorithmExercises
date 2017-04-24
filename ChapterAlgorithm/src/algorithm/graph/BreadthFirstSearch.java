package algorithm.graph;
/*
 * 广度优先算法：
 * 		
 */
import java.util.*;
public class BreadthFirstSearch {
	public static boolean [] accessed = new boolean[8];
	public static Node [] heads = new Node[8];
	public static void breadthFirstSearch(Node[] heads){
		breadthFirstSearch(heads[0]);
	}
	public static void breadthFirstSearch(Node s){
		accessed[s.adjvex] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(s.adjvex);
		int vex;
		Node node;
		while(!queue.isEmpty()){
			vex = queue.poll();
			node = heads[vex].next;
			while(node!=null){
				if(!accessed[node.adjvex]){
					queue.offer(node.adjvex);
					accessed[node.adjvex] = true;
				}
				node = node.next;
			}
			System.out.print(vex);
			if(queue.isEmpty()){
				System.out.println();
			}else{
				System.out.print(" --> ");
			}
		}
	}
}
