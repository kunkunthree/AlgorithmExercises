package algorithm.leetcode.algorithm;
/*
 * medium
 * 332. Reconstruct Itinerary
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], 
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. 
 * Thus, the itinerary must begin with JFK.

Note:

    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order 
    when read as a single string. For example, the itinerary ["JFK", "LGA"]has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.

Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].

Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order. 
 */
import java.util.*;
public class NO332_ReconstructItinerary {
	//方法1：O(n)time,n为ticket的数量
	//因为题目说至少存在一条路径可以通过所有边，所以边所构成的图至少存在一条欧拉路径
	//可以用hierholzer 算法求解其中一条欧拉路径
	//因为欧拉路径可能存在多条，而题目要求得到字母序最小的那条，那么需要先将同一个节点出发的边进行排序
	//可以利用PriorityQueue对String排序
	//那么最后得到的路径就是所求的路径
	//hierholzer 算法：
	//如果存在一条欧拉路径，如果这条路径没有环，则dfs直接走到尾，访问的顺序是递归后访问的逆序
	//如果存在回路，则在访问回路前已经把后面访问的路径保存起来，那么访问完回路又会把回路保存起来，
	//最后把回路之前的路径保存起来，如果是用stack进栈的顺序的话，欧拉路径的顺序是stack出栈的顺序
	private Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;
    public List<String> findItinerary(String[][] tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for(String[] ticket : tickets){
            if(!flights.containsKey(ticket[0])){
                flights.put(ticket[0],new PriorityQueue<String>());
            }
            flights.get(ticket[0]).offer(ticket[1]);
        }
        dfs("JFK");
        return path;
    }
    public void dfs(String from){
        PriorityQueue<String> arrivals = flights.get(from);
        while(arrivals != null && !arrivals.isEmpty()){
           dfs(arrivals.poll()); 
        }
        path.addFirst(from);
    }
    //方法2：
    //hierholzer 算法：迭代实现
    //hierholzer 算法的核心就是深度优先搜索走过的边，一边遍历某条边，一边从图中删除该边，直到走到尽头
    //如果走到尽头，那么就不断回退，直到某个点还有没有没遍历的边存在，则继续遍历该边，不断重复上述步骤
    //直到回退到初始节点且此时初始节点已没有可访问的边
    public List<String> findItinerary2(String[][] tickets) {
        Map<String, PriorityQueue<String>> flights = new HashMap<>();
        LinkedList<String> path = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        for(String[] ticket : tickets){
            if(!flights.containsKey(ticket[0])){
                flights.put(ticket[0],new PriorityQueue<String>());
            }
            flights.get(ticket[0]).offer(ticket[1]);
        }
        stack.push("JFK");
        while(!stack.isEmpty()){
            String from = stack.peek();
            //查看该点还有没有没访问过的边
            if(flights.get(from) == null || flights.get(from).isEmpty()){
                //如果已经到了尽头，存储该点，回退到上一个顶点
                path.addFirst(from);
                stack.pop();
            }else{  //该点还有没访问的边，则继续访问，并删除该边
                stack.push(flights.get(from).poll());
            }
        }
        return path;
    }
}
