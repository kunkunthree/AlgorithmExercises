package algorithm.netease.spring2017;
/*
 * 11.
 * [编程题] 堆砖块:
 * 		小易有n块砖块，每一块砖块有一个高度。小易希望利用这些砖块堆砌两座相同高度的塔。
 * 为了让问题简单，砖块堆砌就是简单的高度相加，某一块砖只能使用在一座塔中一次。
 * 小易现在让能够堆砌出来的两座塔的高度尽量高，小易能否完成呢。
输入描述:
		输入包括两行：
		第一行为整数n(1 ≤ n ≤ 50)，即一共有n块砖块
		第二行为n个整数，表示每一块砖块的高度height[i] (1 ≤ height[i] ≤ 500000)
输出描述:
		如果小易能堆砌出两座高度相同的塔，输出最高能拼凑的高度，如果不能则输出-1.
		保证答案不大于500000。
输入例子:
3
2 3 5
输出例子:
5

 */
import java.util.*;
public class HeapingUpBricks {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] height = new int[n];
		for(int i = 0 ; i < n ; i++){
			height[i] = in.nextInt();
		}
		System.out.println(getTowerHeight(height));
	}
	public static int getTowerHeight(int[] height){
		int length = height.length;
		int sum = 0;
		for(int i = 0 ; i < length ; i++){
			sum+=height[i];
		}
		System.out.println(sum);
		if(sum%2 == 1){
			return -1;
		}
		int halfSum = sum/2;
		int[] c = new int[halfSum+1];
		boolean[] b = new boolean[halfSum+1];
		b[0] = true;
		for(int i = 1 ; i <= halfSum ; i++){
			b[i] = false;
		}
		for(int i = 0 ; i < length ; i++){
			for(int j = halfSum ; j >= height[i] ; j--){
				if(c[j] > c[j-height[i]]+height[i]){
					c[j] = c[j-1];
					b[j] = b[j-1];
				}else{
					c[j] = c[j-height[i]]+height[i];
					b[j] = b[j-height[i]];
				}
			}
		}
		if(b[halfSum] == true){
			return halfSum;
		}else{
			return -1;
		}
	}
}
