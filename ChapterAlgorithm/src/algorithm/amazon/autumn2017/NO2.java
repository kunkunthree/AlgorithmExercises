package algorithm.amazon.autumn2017;
/*
 * 二分图的最大匹配、完美匹配、匈牙利算法：
 * 	小明有个长方形蛋糕，分成M*N等份，记第i行第j列的下标是i*N+j，其中0<=i<M,0<=j<N，
 * 请朋友来吃，每个朋友有各自的选择列表，但是每个人只能拿一份，求最多能分给多少个人
 *
 * 引用：http://blog.jobbole.com/106084/
 * 匈牙利算法的要点如下
    1.从左边第 1 个顶点开始，挑选未匹配点进行搜索，寻找增广路。
        1.1.	如果经过一个未匹配点，说明寻找成功。更新路径信息，匹配边数 +1，停止搜索。
        1.2.	如果一直没有找到增广路，则不再从这个点开始搜索。事实上，此时搜索后会形成一棵匈牙利树。
        	我们可以永久性地把它从图中删去，而不影响结果。
    2.由于找到增广路之后需要沿着路径更新匹配，所以我们需要一个结构来记录路径上的点。
    	DFS 版本通过函数调用隐式地使用一个栈，而 BFS 版本使用 prev 数组。

性能比较

两个版本的时间复杂度均为 O(V⋅E) 。DFS 的优点是思路清晰、代码量少，但是性能不如 BFS。
我测试了两种算法的性能。对于稀疏图，BFS 版本明显快于 DFS 版本；而对于稠密图两者则不相上下。
在完全随机数据 9000 个顶点 4,0000 条边时前者领先后者大约 97.6%，9000 个顶点 100,0000 条边时前者领先后者 8.6%, 
而达到 500,0000 条边时 BFS 仅领先 0.85%。

补充定义和定理：

最大匹配数：最大匹配的匹配边的数目
最小点覆盖数：选取最少的点，使任意一条边至少有一个端点被选择
最大独立数：选取最多的点，使任意所选两点均不相连
最小路径覆盖数：对于一个 DAG（有向无环图），选取最少条路径，使得每个顶点属于且仅属于一条路径。
路径长可以为 0（即单个点）。

定理1：最大匹配数 = 最小点覆盖数（这是 Konig 定理）
定理2：最大匹配数 = 最大独立数
定理3：最小路径覆盖数 = 顶点数 – 最大匹配数

 */
import java.util.*;
public class NO2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] ss = in.nextLine().split(" ");
		int m = Integer.valueOf(ss[0]);
		int n = Integer.valueOf(ss[1]);
		int friendsNum = Integer.valueOf(ss[2]);
		List<List<Integer>> choiceLists = new ArrayList<>();
		for(int i = 0 ; i < friendsNum ; i++){
			ss = in.nextLine().split(" ");
			choiceLists.add(new ArrayList<Integer>());
			for(String s : ss){
				if(s != null && s.length() > 0){
					choiceLists.get(i).add(Integer.valueOf(s));
				}
			}
		}
		int result = hungarian2(m, n, friendsNum, choiceLists);
		System.out.println(result);
	}
	//递归，DFS
	public static int hungarian(int m,int n,int friendsNum,List<List<Integer>> choiceLists){
		int ans = 0;
		int[] match = new int[friendsNum+m*n+1];
		List<List<Integer>> nodes = new ArrayList<>();
		for(int i = 0 ; i < friendsNum+m*n+1 ; i++){
			nodes.add(new ArrayList<Integer>());
			match[i] = -1;
		}
		for(int i = 0 ; i < friendsNum ; i++ ){
			for(int j : choiceLists.get(i)){
				nodes.get(i+m*n+1).add(j);
				nodes.get(j).add(i+m*n+1);
			}
		}
		boolean[] check = new boolean[friendsNum+m*n+1];
		for(int i = 0 ; i < friendsNum ; i++){
			if(match[i+m*n+1] == -1){
				Arrays.fill(check, false);
				if(dfs(i+m*n+1,nodes,check,match)){
					ans++;
				}
			}
		}
		return ans;
	}
	public static boolean dfs(int u,List<List<Integer>> nodes,boolean[] check,int[] match){
		List<Integer> node = nodes.get(u);
		for(int v : node){
			if(!check[v]){
				check[v] = true;
				if(match[v] == -1 || dfs(match[v],nodes,check,match)){
					match[u] = v;
					match[v] = u;
					return true;
				}
			}
		}
		return false;
	}
	//迭代，BFS
	public static int hungarian2(int m,int n,int friendsNum,List<List<Integer>> choiceLists){
		int ans = 0;
		int[] match = new int[friendsNum+m*n+1];
		int[] check = new int[friendsNum+m*n+1];
		int[] pre = new int[friendsNum+m*n+1];
		List<List<Integer>> nodes = new ArrayList<>();
		for(int i = 0 ; i < friendsNum+m*n+1 ; i++){
			nodes.add(new ArrayList<Integer>());
			match[i] = -1;
			check[i] = -1;
		}
		for(int i = 0 ; i < friendsNum ; i++ ){
			for(int j : choiceLists.get(i)){
				nodes.get(i+m*n+1).add(j);
				nodes.get(j).add(i+m*n+1);
			}
		}
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0 ; i < friendsNum ; i++){
			int start = i+m*n+1;
			if(match[start] == -1){
				queue.clear();
				queue.offer(start);
				pre[start] = -1;		//设置起点
				boolean flag = false;		//flag表示是否找到增广路
				while(!queue.isEmpty() && !flag){
					int u  = queue.poll();
					List<Integer> node = nodes.get(u);
					for(int j = 0  ; j < node.size() && !flag; j++){
						int v = node.get(j);
						if(check[v] != start){
							check[v] = start;
							queue.offer(match[v]);
							if(match[v] >= 0){		//此点为匹配点
								pre[match[v]] = u;
							}else{		//找到未匹配点，交替路变为增广路
								flag = true;
								int d = u,e = v;
								while(d != -1){
									int t = match[d];
									match[d] = e;
									match[e] = d;
									d = pre[d];
									e = t;
								}
							}
						}
					}
				}
				if(match[start] != -1){
					ans++;
				}
			}
		}
		return ans;
	}
}
