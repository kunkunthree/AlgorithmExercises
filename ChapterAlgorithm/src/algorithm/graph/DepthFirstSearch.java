package algorithm.graph;
/*
 * 深度优先搜索：
 * 		
 */
public class DepthFirstSearch {
	public static boolean [] accessed = new boolean[8];
	public static Node [] heads = new Node[8];
	public static int time = 0;
	public static void depthFirstSearch(Node[] heads,int[] d,int[] f){
		time = 0;
		for(Node node : heads){
			if(accessed[node.adjvex] == false){
				depthFirstSearch(node,d,f);
			}
		}
	}
	public static void depthFirstSearch(Node s,int[] d,int[] f){
		Node node = heads[s.adjvex];
		time++;
		d[s.adjvex] = time;
		System.out.print(s.adjvex);
		accessed[s.adjvex] = true;
		node = s.next;
		while(node != null){
			if(accessed[node.adjvex] == false){
				depthFirstSearch(node,d,f);
			}
			node = node.next;
		}
		time++;
		f[s.adjvex] = time;
	}
}
