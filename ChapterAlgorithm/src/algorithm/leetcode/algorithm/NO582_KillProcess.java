package algorithm.leetcode.algorithm;
/*
 * medium
 * 582. Kill Process 
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. 
This is just like a tree structure. Only one process has PPID that is 0, 
which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process 
and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, 
return a list of PIDs of processes that will be killed in the end. 
You should assume that when a process is killed, all its children processes will be killed. 
No order is required for the final answer.

Example 1:

Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation: 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.

Note:
    1.	The given kill id is guaranteed to be one of the given PIDs.
    2.	n >= 1.

 */
import java.util.*;
public class NO582_KillProcess {
	public static void main(String[] args) {
		List<Integer> pid= new ArrayList<Integer>();
		List<Integer> ppid= new ArrayList<Integer>();
		Scanner in = new Scanner(System.in);
		String[] ss = in.nextLine().split(" ");
		for(String s : ss){
			pid.add(Integer.valueOf(s));
		}
		ss = in.nextLine().split(" ");
		for(String s : ss){
			ppid.add(Integer.valueOf(s));
		}
		int kill = Integer.valueOf(in.nextLine());
		System.out.println(killProcess2(pid, ppid, kill));
	}
	//方法1：
	//不断迭代，一层一层遍历找出剩余的项
	//超时
	public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Set<Integer> killSet = new HashSet<>(),nextKillSet;
        List<Integer> result = new ArrayList<>();
        killSet.add(kill);
        while(!killSet.isEmpty()){
            nextKillSet = new HashSet<>();
            for(int i = 0 ; i < pid.size() ; i++){
                if(killSet.contains(pid.get(i))){
                    result.add(pid.get(i));
                    pid.remove(i);
                    ppid.remove(i--);
                }else if(killSet.contains(ppid.get(i))){
                    nextKillSet.add(pid.get(i));
                }
            }
            killSet = nextKillSet;
        }
        return result;
    }
	//方法2：
	//用map记录进程的子进程列表，通过BFS或者DFS得到需要杀死的进程
	public static List<Integer> killProcess2(List<Integer> pid, List<Integer> ppid, int kill) {
		Map<Integer,List<Integer>> map = new HashMap<>();
        List<Integer> killList = new ArrayList<>();
        for(int i = 0 ; i < pid.size() ; i++){
            if(!map.containsKey(ppid.get(i))){
                map.put(ppid.get(i),new ArrayList<Integer>());
            }
            if(!map.containsKey(pid.get(i))){
                map.put(pid.get(i),new ArrayList<Integer>());
            }
            map.get(ppid.get(i)).add(pid.get(i));
        }
        dfs(killList,map,kill);
        return killList;
    }
	private static void dfs(List<Integer> killList,Map<Integer,List<Integer>> map,int kill){
        if(map.containsKey(kill)){
            killList.add(kill);
            for(int k : map.get(kill)){
                dfs(killList,map,k);
            }
        }
    }
	private static void bfs(List<Integer> killList,Map<Integer,List<Integer>> map,int kill){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        while(!queue.isEmpty()){
            kill = queue.poll();
            killList.add(kill);
            if(map.containsKey(kill)){
                for(int k : map.get(kill)){
                    queue.offer(k);
                }
            }
        }
    }
}
