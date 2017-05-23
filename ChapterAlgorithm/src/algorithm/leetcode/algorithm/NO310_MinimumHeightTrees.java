package algorithm.leetcode.algorithm;
/*
 * medium
 * 310. Minimum Height Trees
 *  For a undirected graph with tree characteristics, we can choose any node as the root. 
 *  The result graph is then a rooted tree. Among all possible rooted trees, 
 *  those with minimum height are called minimum height trees (MHTs). 
 *  Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. 
You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

return [3, 4]

Note:

(1) According to the definition of tree on Wikipedia: “
a tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf. 
 */
import java.util.*;
public class NO310_MinimumHeightTrees {
	public static void main(String[] args) {
		int n = 6;
		int[][] edges = new int[][]{{0,3},{1,3},{2,3},{4,3},{5,4}};
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);set.add(2);set.add(3);
		Iterator<Integer> it = set.iterator();
		it.next();
		it.next();
		System.out.println(set);
//		System.out.println(findMinHeightTrees(n, edges));
	}
	//方法1：
	//暴力破解，遍历所有节点，进行BFS，O(n^2)time,O(n)space
	public static  List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer,List<Integer>> heightMap = new HashMap<>();
        Map<Integer,Set<Integer>> edgeMap = new HashMap<>();
        Set<Integer> visitedSet = new HashSet<>();
        int length = edges.length;
        int x,y,height,min = Integer.MAX_VALUE,tmp;
        Set<Integer> set;
        List<Integer> list;
        for(int j = 0 ; j < length ; j++){
            x = edges[j][0];
            y = edges[j][1];
            if(!edgeMap.containsKey(x)){
                edgeMap.put(x,new HashSet<Integer>());
            }
            set = edgeMap.get(x);
            set.add(y);
            if(!edgeMap.containsKey(y)){
                edgeMap.put(y,new HashSet<Integer>());
            }
            set = edgeMap.get(y);
            set.add(x);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i < n ; i++){
            height = 0;
            if(edgeMap.containsKey(i)){
                queue.clear();
                visitedSet.clear();
                set = edgeMap.get(i);
                visitedSet.add(i);
                for(int nextNode : set){
                    if(visitedSet.add(nextNode)){
                        queue.offer(nextNode);
                    }
                }
                while(!queue.isEmpty()){
                    height++;
                    int num = queue.size();
                    for(int j = 0 ; j < num ; j++){
                        tmp = queue.poll();
                        set = edgeMap.get(tmp);
                        for(int nextNode : set){
                            if(visitedSet.add(nextNode)){
                                queue.offer(nextNode);
                            }
                        }
                    }
                }
            }
            if(!heightMap.containsKey(height)){
                heightMap.put(height,new ArrayList<Integer>());
            }
            heightMap.get(height).add(i);
            if(height < min){
                min = height;
            }
        }
        return heightMap.get(min);
    }
	//方法2：
	//O(n)time，从所有叶节点（度为1的节点）开始出发，BFS，不断摘除叶节点，直到剩下一个或两个节点时，
	//此时以两个节点作为根节点的树，高度最小。
	//方法类似与拓扑排序
	public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if(edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0){
            return Arrays.asList(0);
        }
        List<Set<Integer>> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            list.add(new HashSet<Integer>());
        }
        int length = edges.length;
        for(int i = 0 ; i < length ; i++){
            list.get(edges[i][0]).add(edges[i][1]);
            list.get(edges[i][1]).add(edges[i][0]);
        }
        Queue<Integer> leaves = new LinkedList<>();
        for(int i = 0 ; i < n ; i++){
            if(list.get(i).size() == 1){
                leaves.offer(i);
            }
        }
        while(n > 2){
            length = leaves.size();
            n-=length;
            for(int i = 0 ; i < length ; i++){
                int leaf = leaves.poll();
                int root = list.get(leaf).iterator().next();
                list.get(root).remove(leaf);
                if(list.get(root).size() == 1){
                    leaves.offer(root);
                }
            }
        }
        return new ArrayList<Integer>(leaves);
    }
}
