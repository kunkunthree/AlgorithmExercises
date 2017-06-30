package algorithm.leetcode.algorithm;
/*
 * hard
 * 517. Super Washing Machines 
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

Example1

Input: [1,0,5]

Output: 3

Explanation: 
1st move:    1     0 <-- 5    =>    1     1     4
2nd move:    1 <-- 1 <-- 4    =>    2     1     3    
3rd move:    2     1 <-- 3    =>    2     2     2   

Example2

Input: [0,3,0]

Output: 2

Explanation: 
1st move:    0 <-- 3     0    =>    1     2     0    
2nd move:    1     2 --> 0    =>    1     1     1     

Example3

Input: [0,2,0]

Output: -1

Explanation: 
It's impossible to make all the three washing machines have the same number of dresses. 

Note:
    The range of n is [1, 10000].
    The range of dresses number in a super washing machine is [0, 1e5].

 */
public class NO517_SuperWashingMachines {
	//方法1：
	//O(n) time, O(1) space
	//通过观察移动的特点，假设每次都使最左边非平均数变成平均数，
	//如 [0,0,11,5] 平均数为 4 ,可转换为与平均数的差:
	//[-4,-4,7,1] ->  [0,-8,7,1] -> [0,0,-1,1] -> [0,0,0,0] -> done
	//最少的步数取决于中间过程中累加和的绝对值最大值以及与平均数的差的数组中的最大值
	//result = Math.max(Math.max(result,Math.abs(sum)),machines[i]-average);
	public int findMinMoves(int[] machines) {
        int sum = 0;
        for(int num : machines){
            sum+=num;
        }
        if(sum%machines.length != 0){
            return -1;
        }
        int result = 0,average = sum / machines.length;
        sum = 0;
        for(int i = 0 ; i < machines.length ; i++){
            sum+=machines[i] - average;
            result = Math.max(Math.max(result,Math.abs(sum)),machines[i]-average);
        }
        return result;
    }
	
	//方法2;
	//dp,动态规划
	public int findMinMoves2(int[] machines) {
        int total = 0, target = 0, result = 0, n = machines.length;
        for (int d : machines) total += d;
        if (total == 0) return 0;
        if (total % n != 0) return -1;
        target = total / n;
        
        int[] move = new int[n];
        for (int i = 0; i < n - 1; i++) {
            if (machines[i] > target) {
                move[i] += machines[i] - target;
                machines[i + 1] += machines[i] - target;
                machines[i] = target;
                result = Math.max(result, move[i]);
            }
            else {
                move[i + 1] = target - machines[i];
                machines[i + 1] -= target - machines[i];
                machines[i] = target;
                result = Math.max(result, move[i + 1]);
            }
        }
        
        return result;
    }
}
