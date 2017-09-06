package algorithm.pinduoduo.automn20170801;
/*
 * [编程题] 最大乘积
时间限制：1秒
空间限制：32768K
给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大，
要求时间复杂度：O(n)，空间复杂度：O(1)
输入描述:
数组大小n
无序整数数组A[n]

输出描述:
满足条件的最大乘积

输入例子1:
4
3 4 1 2

输出例子1:
24
 */
import java.util.*;
public class LargestProduct {
	public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Long> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            list.add(in.nextLong());
        }
        Collections.sort(list);
        long max = Integer.MIN_VALUE;
        if(n < 3){
            return;
        }
        max = Math.max(max,list.get(0)*list.get(1)*list.get(2));
        max = Math.max(max,list.get(0)*list.get(1)*list.get(n-1));
        max = Math.max(max,list.get(0)*list.get(n-2)*list.get(n-1));
        max = Math.max(max,list.get(n-3)*list.get(n-2)*list.get(n-1));
        System.out.println(max);
    }
}
