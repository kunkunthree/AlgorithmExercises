package algorithm.leetcode.algorithm;
/*
 * medium
 * 210. Course Schedule II
 *  There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs,
 return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. 
If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]

There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. 
Another correct ordering is[0,2,1,3].

Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.

 */
import java.util.*;
public class NO210_CourseScheduleII {
	//方法1：
	//拓扑逆排序，
	//如果遍历到的入度为0的节点数小于总节点数，则返回空数组
	//如果等于总节点数，则返回排序后的新数组
	public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[] result = new int[numCourses];
        boolean[][] edges = new boolean[numCourses][numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0 ; i < prerequisites.length ; i++){
            edges[prerequisites[i][0]][prerequisites[i][1]] = true;
            indegree[prerequisites[i][1]]++;
        }
        for(int i = 0 ; i < numCourses ; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        int course,count = numCourses;
        while(!queue.isEmpty()){
            course = queue.poll();
            result[--count] = course;
            for(int i = 0 ; i < numCourses ; i++){
                if(edges[course][i] == true){
                    if(--indegree[i] == 0){
                        queue.offer(i);
                    }
                }
            }
        }
        return count == 0 ? result : new int[0];
    }
	//方法2：
	//DFS
	public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] visited = new int[numCourses];
        int[] result = new int[numCourses];
        boolean[][] edges = new boolean[numCourses][numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0 ; i < prerequisites.length ; i++){
            edges[prerequisites[i][0]][prerequisites[i][1]] = true;
        }
        int[] count = new int[1];
        for(int i = 0 ; i < numCourses ; i++){
            if(visited[i] == 0 && dfs(edges,visited,result,i,count) == false){
                return new int[0];
            }
        }
        return result;
    }
    public boolean dfs(boolean[][] edges,int[] visited,int[] result,int course,int[] count){
        visited[course] = -1;
        for(int i = 0 ; i < edges[course].length ; i++){
            if(edges[course][i] == true){
                if(visited[i] == -1 || 
                    visited[i] == 0 && dfs(edges,visited,result,i,count) == false){
                    return false;
                }
            }
        }
        visited[course] = 1;
        result[count[0]++] = course;
        return true;
    }
}
