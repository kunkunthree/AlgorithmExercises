package algorithm.leetcode.algorithm;
/*
 * medium
 * 207. Course Schedule
 *  There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0, 
and to take course 0 you should also have finished course 1. So it is impossible.

Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
    Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.

 */
import java.util.*;
public class NO207_CourseSchedule {
	public static void main(String[] args) {
		int numCourses = 2;
		int[][] prerequisites = new int[][]{{0,1}};
		System.out.println(canFinish2(numCourses, prerequisites));
	}
	//判断一个有向图是否有环
	//方法1：
	//BFS
	//可以利用拓扑排序，看输出的节点数是否等于图中的总节点数，是的话就无环，如果小于的话就有环
	//拓扑排序算法：
	//由AOV网构造拓扑序列的拓扑排序算法主要是循环执行以下两步，直到不存在入度为0的顶点为止。
	//(1) 选择一个入度为0的顶点并输出之；
	//(2) 从网中删除此顶点及所有出边。
	//循环结束后，若输出的顶点数小于网中的顶点数，则输出“有回路”信息，否则输出的顶点序列就是一种拓扑序列。
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] indegree = new int[numCourses];
        int length = prerequisites.length;
        boolean[][] edges = new boolean[numCourses][numCourses];
        for(int i = 0 ; i < length ; i++){
            edges[prerequisites[i][0]][prerequisites[i][1]] = true;
            indegree[prerequisites[i][1]]++;
        }
        for(int i = 0 ; i < numCourses ; i++){
        	//从入度为0的节点开始排序
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0,from;
        while(!queue.isEmpty()){
            from = queue.poll();
            count++;	//统计节点数
            for(int to = 0 ; to < numCourses ; to++){
                if(edges[from][to] == true){
                    indegree[to]--;
                    if(indegree[to] == 0){
                        queue.offer(to);
                    }
                    edges[from][to] = false;
                }
            }
        }
        return count == numCourses;
    }
	
	//方法2：
	//DFS
	//用visited表示访问的状态，防止A->B，A->C->B这种情况出现，且能提高访问效率，减少不必要的访问
	//0表示未访问状态，-1表示已经过该节点且正在访问其后代节点中，1表示已访问该节点及其后代节点
	public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] indegree = new int[numCourses];
        int length = prerequisites.length;
        boolean[][] edges = new boolean[numCourses][numCourses];
        int[] visited = new int[numCourses];
        for(int i = 0 ; i < length ; i++){
            edges[prerequisites[i][0]][prerequisites[i][1]] = true;
            indegree[prerequisites[i][1]]++;
        }
        for(int i = 0 ; i < numCourses ; i++){
            if(visited[i] == 0 && dfs(edges,visited,i) == false){
                return false;
            }
        }
        return true;
    }
    public static boolean dfs(boolean[][] edges,int[] visited,int course){
        visited[course] = -1;
        for(int i = 0 ; i < edges[course].length ; i++){
            if(edges[course][i] == true){
            	//如果遇到visited[i] = 1，则表示i及其后代节点的线路无环，不需要访问
                if(visited[i] == -1 || visited[i] == 0 && dfs(edges,visited,i) == false){
                    return false;
                }
            }
        }
        visited[course] = 1;
        return true;
    }
}
