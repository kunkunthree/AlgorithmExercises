package algorithm.graph;
/*
 * 图：
 */
public class MGraph {
	int vertex[];
	int edges[][];			//0表示同一个点，-1表示无法到达，大于0表示两点直连的边的距离
	int n,e;
	public MGraph(int n ,int e) {
		this.n = n;
		vertex = new int[n];
		this.e = e;
		edges = new int[e][e];
	}
}
