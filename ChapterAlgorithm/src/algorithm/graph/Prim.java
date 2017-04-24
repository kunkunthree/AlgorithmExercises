package algorithm.graph;
/*
 * Prim算法生成最小生成树：
 * 		树从任意根节点
 */
import java.util.*;
public class Prim {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < 10 ; i++){
			list.add(i);
		}
		System.out.println(list);
		list.remove(0);
		list.remove(new Integer(0));
		System.out.println(list);
	}
	public static int getWeight(Node[] heads,int u,int v){
		Node node = heads[u];
		while(node != null){
			if(node.adjvex == v){
				return node.data;
			}
			node = node.next;
		}
		return Integer.MAX_VALUE;
	}
	public static void prim(MGraph G,int[] key,int[] par,int r,Node[] heads,ArrayList<Integer> list){	//key[v]表示v与树中某一顶点相连的边中的最小值
		for(int i = 0 ; i < key.length ; i++){
			key[i] = Integer.MAX_VALUE;
			par[i] = Integer.MIN_VALUE;
		}
		key[r] = 0;
		while(!list.isEmpty()){
			int u = list.remove(0);
			int v,w;
			Node node = heads[u];
			while(node!= null){
				v = node.adjvex;
				w = getWeight(heads, u, v);
				if(list.contains(new Integer(v)) && w < key[v]){
					par[v] = u;
					key[v] = w;
				}
			}
		}
	}
}
