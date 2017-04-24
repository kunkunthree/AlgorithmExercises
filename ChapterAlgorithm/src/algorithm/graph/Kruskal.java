package algorithm.graph;

/*
 * Kruskal最小生成树算法：
 * 		Kruskal的算法实现类似于就算连通分支的算法。它采用了一种不相交集合数据结构，
 * 以维护几个互相不相交的元素集合。每一个集合包含了当前森林中某棵树的顶点，操作FIND-SET(u)
 * 返回包含u的集合中的一个代表元素。于是，通过测试FIND-SET(u)是否等同于FIND-SET(v)，
 * 就可以确定顶点u和v是否属于同一棵树。通过过程UNION，可以实现树与树的合并。
 */
import java.util.*;
public class Kruskal {
	public static void kruskal(MGraph G){
		int u,v,rootU,rootV,k;
		int[] vset = new int[G.n];	//表示每一个元素所在集合的代表元素
		Edge[] E = new Edge[G.e];	//存放所有边
		k = 0;											//E数组的下表，从0开始
		for(int i = 0 ; i < G.n ; i++){
			for(int j = 0 ; j < G.n ; j++){
				if(G.edges[i][j] != 0 && G.edges[i][j] != -1){
					E[k].u = i;
					E[k].v = j;
					E[k].w = G.edges[i][j];
					k++;
				}
			}
		}
		//按边加权值排序，从小到大
		Arrays.sort(E, new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				if(e1.w < e2.w){
					return -1;
				}else if(e1.w > e2.w){
					return 1;
				}else{
					return 0;
				}
			}
		});
		//初始化，表示一开始每个元素分别为一个集合
		for(int i = 0 ; i < G.n ; i++){
			vset[i] = i;
		}
		k = 1;	//表示生成的边数
		int i = 0;		//E的下标
		while(k < G.n){
			rootU = vset[E[i].u];		//起始点所在集合的代表元素
			rootV = vset[E[i].v];		//	终止点所在集合的代表元素
			if(rootU != rootV){
				System.out.println(String.format("%d ---> %d, %d", E[i].u,E[i].v,E[i].w));
				k++;
				//合并这条边所连接的两个集合
				for(int j = 0 ; j < G.n ; j++){
					if(vset[j] == rootV){
						vset[j] = rootU;
					}
				}
			}
			i++;
		}
	}
}
