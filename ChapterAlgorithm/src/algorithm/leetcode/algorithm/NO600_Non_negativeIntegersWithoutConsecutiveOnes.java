package algorithm.leetcode.algorithm;
/*
 * hard
 * 600. Non-negative Integers without Consecutive Ones 
 * Given a positive integer n, find the number of non-negative integers less than or equal to n, 
 * whose binary representations do NOT contain consecutive ones.

Example 1:

Input: 5
Output: 5
Explanation: 
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 

Note: 1 <= n <= 109 

similar problems:
198. House Robber 
213. House Robber II 
474. Ones and Zeroes 
 */
public class NO600_Non_negativeIntegersWithoutConsecutiveOnes {
	//方法1：
	//dp，动态规划
	//O(logn)space,O(logn)time
	//先对所给整数化为逆序的二进制字符串，长度为n
	//然后求长度为n的没有连续1的二进制字符串的个数
	//http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
	//endWithZero[i]表示第i位以0结尾的没有连续1的字符串个数
	//endWithOne[i]表示第i位以1结尾的没有连续1的字符串个数
	//endWithZero[i] = endWithZero[i-1] + endWithOne[i-1];
    //endWithOne[i] = endWithZero[i-1];
	//
	//最后对该二进制字符串进行遍历，从n-2~0，
	//1.如果遇到sb.charAt(i) == '1' && sb.charAt(i+1) == '1' 即遇到11，则说明后面的范围都已经都包含了，
	//		所以不用继续遍历
	//2.如果遇到sb.charAt(i) == '0' && sb.charAt(i+1) == '0' 即遇到00，则说明最后结果多算了01（低位为1）的情况，
	//		所以需要减去endWithOne[i]
	//3.如果遇到01或者10，则低位的0和1都包括，所以直接跳过进入下一位遍历
	public int findIntegers(int num) {
        if(num < 0){
            return 0;
        }
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
        int n = sb.length();
        
        int[] endWithOne = new int[n];
        int[] endWithZero = new int[n];
        endWithOne[0] = endWithZero[0] = 1;
        for(int i = 1 ; i < n ; i++){
            endWithZero[i] = endWithZero[i-1] + endWithOne[i-1];
            endWithOne[i] = endWithZero[i-1];
        }
        
        int result = endWithOne[n-1] + endWithZero[n-1];
        for(int i = n-2 ; i >= 0 ; i--){
            if(sb.charAt(i) == '1' && sb.charAt(i+1) == '1')break;
            if(sb.charAt(i) == '0' && sb.charAt(i+1) == '0')result-=endWithOne[i];
        }
        return result;
    }
	//方法2：
	//O(1) time O(1) space DP Solution
	public int findIntegers2(int num) {
        //one:all bit before cur is less than num and no continues 1 and cur bit is at one;
        //zero:all bit before cur is less than num and no continues 1 and cur bit is at zero;
        int one=0,zero=0,temp;
        boolean isNum=true;
        for(int i=31;i>=0;i--){
            temp=zero;
            zero+=one;
            one=temp;
            if(isNum&&((num>>i)&1)==1){
                zero+=1;
            }
            if(((num>>i)&1)==1&&((num>>i+1)&1)==1){
                isNum=false;
            }
            
        }
        return one+zero+(isNum?1:0);
    }
}
