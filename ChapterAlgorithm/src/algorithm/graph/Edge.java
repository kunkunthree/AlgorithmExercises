package algorithm.graph;
/*
 * 边
 */
public class Edge {
	public int u;		//如果是有向边，则表示边的起始顶点
	public int v;		//如果是有向边，则表示边的终止顶点
	public int w;		//表示边的权值
	public Edge() {}
	public Edge(int u , int v ,int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}
}
