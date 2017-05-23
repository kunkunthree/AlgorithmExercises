package algorithm.leetcode.algorithm;
/*
 * medium
 * 134. Gas Station
 *  There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique. 
 */
public class NO134_GasStation {
	public static void main(String[] args) {
		int[] gas = new int[]{1,2};
		int[] cost = new int[]{2,1};
		System.out.println(canCompleteCircuit(gas, cost));
	}
	//方法1：
	//迭代，剪枝，只对上一个加油点消耗比加的油量大的加油站开始测试，
	//即只对行驶到下一个加油站油量会开始增加的加油点进行测试
	public static int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int sum = 0;
        for(int i = 0 ; i < length ; i++){
            gas[i]-=cost[i];
        }
        for(int i = 0 ; i < length ; i++){
            if(gas[i] >= 0 && gas[(i+length-1)%length] < 0 || length == 1){
                int j = 0;
                sum = 0;
                while(j < length){
                    sum+=gas[(i+j)%length];
                    if(sum < 0){
                        break;
                    }
                    j++;
                }
                if(j == length){
                    return i;
                }
            }
        }
        return -1;
    }
	//方法2：
	//迭代实现，O(n)time,O(1)space
	//思路：从0到length-1遍历每一个节点，start用于标记起点，初始值为0，
	//sum用于记录油量开始递增的站点出发的油量，初始值为0
	//total用于记录到目前位置，总增长为负的油量差，初始值为0
	//当sum<0时，把sum加到total里，把起点设置为下一个节点
	//遍历完所有站点后，把sum加进total，如果total小于0，说明任何站点为起点都不能支持跑完所有站点
	//如果total>=0,则返回start
	public int canCompleteCircuit2(int[] gas, int[] cost) {
        int length = gas.length;
        int sum = 0,total = 0,start = 0;
        for(int i = 0 ; i < length ; i++){
            gas[i]-=cost[i];
        }
        for(int i = 0 ; i < length ; i++){
            sum+=gas[i];
            if(sum < 0){
                total+=sum;
                sum = 0;
                start = i+1;
            }
        }
        total+=sum;
        return total < 0 ? -1 : start;
    }
}
