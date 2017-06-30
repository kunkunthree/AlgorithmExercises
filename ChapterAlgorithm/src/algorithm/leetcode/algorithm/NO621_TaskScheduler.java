package algorithm.leetcode.algorithm;
/*
 * medium
 * 621. Task Scheduler 
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z 
 * where different letters represent different tasks.Tasks could be done without original order. 
 * Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks,
 there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:

Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

Note:
    1.		The number of tasks is in the range [1, 10000].
    2.		The integer n is in the range [0, 100].

 */
import java.util.*;
public class NO621_TaskScheduler {
	//方法1：
	//假设以频率最高的一组字母作为头部建立长度为n的字段，在字段中剩余的空位中填入剩余字符
	// Examples:
	//
	// AAAABBBEEFFGG 3
	//
	// here X represents a space gap:
	//
	// Frame: "AXXXAXXXAXXXA"
	// insert 'B': "ABXXABXXABXXA" <--- 'B' has higher frequency than the other
	// characters, insert it first.
	// insert 'E': "ABEXABEXABXXA"
	// insert 'F': "ABEFABEXABFXA" <--- each time try to fill the k-1 gaps as
	// full or evenly as possible.
	// insert 'G': "ABEFABEGABFGA"
	//
	// AACCCBEEE 2
	//
	// 3 identical chunks "CE", "CE CE CE" <-- this is a frame
	// insert 'A' among the gaps of chunks since it has higher frequency than
	// 'B' ---> "CEACEACE"
	// insert 'B' ---> "CEABCEACE" <----- result is tasks.length;
	//
	// AACCCDDEEE 3
	//
	// 3 identical chunks "CE", "CE CE CE" <--- this is a frame.
	// Begin to insert 'A'->"CEA CEA CE"
	// Begin to insert 'B'->"CEABCEABCE" <---- result is tasks.length;
	//
	// ACCCEEE 2
	//
	// 3 identical chunks "CE", "CE CE CE" <-- this is a frame
	// Begin to insert 'A' --> "CEACE CE" <-- result is (c[25] - 1) * (n + 1) +
	// 25 -i = 2 * 3 + 2 = 8
	public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for(char c : tasks){
            count[c-'A']++;
        }
        Arrays.sort(count);
        int i = 24;
        while(i >= 0 && count[i] == count[25]){
            i--;
        }
        return Math.max(tasks.length,(n+1) * (count[25]-1) + 25-i);
    }
	
	//方法2：
	//贪婪算法，利用HashMap和PriorityQueue
	//1. 优先处理剩余个数最大的字符
	//2. 把任务放到PriorityQueue中降序排列
	//3.	开始处理queue的头部字符，如果还有剩余，则把当前次数和个数存入map中进行冷却
	//4.	如果map中有字符已经可以经过n个间隔，冷却时间到了，则将其放到queue中，
	//5. 重复3和4直到没有任务剩余
	public int leastInterval2(char[] tasks, int n) {
        if(n == 0){
            return tasks.length;
        }
        HashMap<Character,Integer> taskToCount = new HashMap<>();
        for(char c : tasks){
            if(!taskToCount.containsKey(c)){
                taskToCount.put(c,0);
            }
            taskToCount.put(c,taskToCount.get(c)+1);
        }
        Queue<Integer> queue = new PriorityQueue<Integer>(1,new Comparator<Integer>(){
            public int compare(Integer i1,Integer i2){
                return i2 - i1;
            }
        });
        for(char c : taskToCount.keySet()){
            queue.offer(taskToCount.get(c));
        }
        
        Map<Integer,Integer> coolDown = new HashMap<>();
        int curTime = 0;
        while(!queue.isEmpty() || !coolDown.isEmpty()){
            if(coolDown.containsKey(curTime - n - 1)){
               queue.offer(coolDown.remove(curTime-n-1)); 
            }
            if(!queue.isEmpty()){
                int left = queue.poll() - 1;
                if(left != 0){
                    coolDown.put(curTime,left);
                }
            }
            curTime++;
        }
        return curTime;
    }
}
