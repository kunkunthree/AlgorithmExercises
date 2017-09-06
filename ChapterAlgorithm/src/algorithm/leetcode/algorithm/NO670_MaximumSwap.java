package algorithm.leetcode.algorithm;

import java.util.Arrays;
import java.util.Collections;

/*
 * medium
 * 670. Maximum Swap 
 *  Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. 
 *  Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: 9973
Output: 9973
Explanation: No swap.

Note:
    1.	The given number is in the range [0, 10^8]

 */
import java.util.*;
public class NO670_MaximumSwap {
	//方法1：
	//O(nlogn)time,O(n)space, n为位数
	//先将原数digit1排序得到这些数字能组成的最大数digit2，然后从高位起一一比较第i位是否相等，
	//如果不相等，则从原数的末尾数字开始往前找到等于最大数对应的第i位digit2[[i]，
	//			假设在第j位找到，则将原数digit1的第i位和第j位交换
	public int maximumSwap(int num) {
        String s = num+"";
        int n = s.length();
        int[] digit1 = new int[n];
        int[] digit2 = new int[n];
        for(int i = 0 ; i < n ; i++){
            digit1[i] = s.charAt(i)-'0';
            digit2[i] = digit1[i];
        }
        Arrays.sort(digit2);
        for(int i = 0 ; i < n ; i++){
            if(digit1[i] < digit2[n-1-i]){
                for(int j = n-1 ; j > i ; j--){
                    if(digit2[n-1-i] == digit1[j]){
                        digit1[j] = digit1[i];
                        digit1[i] = digit2[n-1-i];
                        break;
                    }
                }
                break;
            }
        }
        int result = 0;
        for(int i = 0 ; i < n ; i++){
            result = result * 10 + digit1[i];
        }
        return result;
    }
	
	//方法2：
	//O(n)time,O(n)space,n为位数
	//贪婪算法：
	//本着高位数字越大，整个数越大的原则，如果低位数字比它高位数字要大，那么交换这两个数后整个数肯定会变大
	//为了使一次交换后能得到最大的数，需要交换尽可能大的数字，而与这个数字交换的数字的位数应该越高越好
	//maxSeen表示当前遍历过的最大数字，maxIdx表示第一次遍历到的最大数字的下标
	//power表示当前遍历到的数字的位数，swapIdx1和swapIdx2表示最终要交换的两个数字的下标
	//1.	从最低位开始往最高位遍历，并用list保存每一位数字，
	//				每次遍历都保存当前遍历到的最大数字和第一次遍历到的下标。
	//2.	如果当前数字比maxSeen大，则设置maxIdx和当前power为swapIdx1和swapIdx2
	//		如果当前数字比maxSeen小，则更新maxSeen和maxIdx。
	//3.	最后交换swapIdx1和swapIdx2，并组成新的数并返回结果
	public int maximumSwap2(int num) {
        if(num <= 0){
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        int maxSeen = 0, power = 0,maxIdx = -1,swapIdx1 = 0, swapIdx2 = 0;
        while(num > 0){
            int digit = num%10;
            list.add(digit);
            if(maxSeen > digit){
                swapIdx1 = maxIdx;
                swapIdx2 = power;
            }else if(maxSeen < digit){
                maxSeen = digit;
                maxIdx = power;
            }
            power++;
            num/=10;
        }
        Collections.swap(list,swapIdx1,swapIdx2);
        int result = 0,n = list.size();
        for(int i = 0 ; i < n ; i++){
            result = result * 10 + list.get(n-1-i);
        }
        return result;
    }
	
	//方法3：
	//用一个bucket记录0-9数字在num中的最低位下标
	//第一次遍历，记录0-9数字在num中的最低位下标
	//第二次遍历，从高位往低位遍历，判断当前数字大的数字的下标是否大于当前数字下标，
	//								如果大于，则交换两个下标的数字
	//返回结果
	public int maximumSwap3(int num) {
        if(num <= 0){
            return 0;
        }
        char[] digit = String.valueOf(num).toCharArray();
        int[] bucket = new int[10];
        int n = digit.length;
        for(int i = 0 ; i < n ; i++){
            bucket[digit[i]-'0'] = i;
        }
        for(int i = 0 ; i < n ; i++){
            for(int k = 9 ; k > digit[i] - '0' ; k--){
                if(bucket[k] > i){
                    char tmp = digit[bucket[k]];
                    digit[bucket[k]] = digit[i];
                    digit[i] = tmp;
                    return Integer.valueOf(new String(digit));
                }
            }
        }
        return num;
    }
}
