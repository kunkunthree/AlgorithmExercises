package algorithm.leetcode.algorithm;
/*
 * medium
 * 636. Exclusive Time of Functions 
 * Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, 
 * find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. 
For example, "0:start:0" means function 0 starts from the very beginning of time 0.
 							"0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, 
the time spent by calling other functions should not be considered as this function's exclusive time.
 You should return the exclusive time of each function sorted by their function id.

Example 1:
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.

Note:
    1.		Input logs will be sorted by timestamp, NOT log id.
    2.		Your output should be sorted by function id, which means the 0th element of your output corresponds
     		to the exclusive time of function 0.
    3.		Two functions won't start or end at the same time.
    4.		Functions could be called recursively, and will always end.
    5.		1 <= n <= 100

 */
import java.util.*;
public class NO636_ExclusiveTimeOfFunctions {
	public static void main(String[] args) {
	}
	//方法1：
	//用一个stack记录某个函数的开始时间戳，stack高度代表调用层数，
	//当内部调用一个函数时等调用结束后，需要对该开始时间戳
	//进行更新，更新为结束时间+1
	//对所有日志进行遍历，
	//1.当前为开始时间戳，
	//		1.1	如果stack为空，则说明是最外层main函数，并非0～n-1函数中的任意一个，所以之间压进栈
	//		1.2	如果stack不为空，则说明是0～n-1函数中的某层调用，则计算stack顶层开始时间戳到当前时间戳的时间间隔
	//													并加到stack顶层函数id所对应的运行花费时间中
	//2.当前为结束时间戳，
	//		2.1	如果stack为空，则说明日志有丢失，抛出异常
	//		2.2	如果stack不为空，则说明是当前结束时间戳是stack顶层函数所运行结束的时间，
	//													计算花费时间time为当前结束时间戳-stack顶层开始时间戳+1，加到对应的花费时间中
	//													stack顶端出栈，此时如果stack非空，则更新此时顶端函数开始时间戳为当前结束时间戳+1
	//注意：开始时间戳的时间是时间段的开始，结束时间戳的时间是时间段的结束
	//				例如，0:start:1 	表示id为0的函数在时间段[1.0 , 2.0]中的1.0开始 ， 
	//							0:end:1		表示0的函数在时间段[1.0 , 2.0]中的2.0结束
	public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n],pre;
        int fId,timestamp;
        Stack<int[]> stack = new Stack<>();
        for(String log : logs){
            String[] str = log.split(":");
            fId = Integer.parseInt(str[0]);
            timestamp = Integer.parseInt(str[2]);
            if(str[1].equals("start")){
                if(!stack.isEmpty()){
                    pre = stack.peek();
                    result[pre[0]]+=timestamp-pre[1];
                }
                stack.push(new int[]{fId,timestamp});
            }else if(str[1].equals("end")){
                if(stack.isEmpty()){
                    throw new RuntimeException("输入有误");
                }else{
                    pre = stack.pop();
                    result[fId]+=timestamp-pre[1]+1;
                    if(!stack.isEmpty()){
                        stack.peek()[1] = timestamp+1;
                    }
                }
            }
        }
        return result;
    }
}
