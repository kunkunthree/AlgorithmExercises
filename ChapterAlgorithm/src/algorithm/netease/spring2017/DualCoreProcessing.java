package algorithm.netease.spring2017;

/*
 * 1
 * [编程题] 双核处理:
 * 		一种双核CPU的两个核能够同时的处理任务，现在有n个已知数据量的任务需要交给CPU处理，
 * 假设已知CPU的每个核1秒可以处理1kb，每个核同时只能处理一项任务。
 * n个任务可以按照任意顺序放入CPU进行处理，现在需要设计一个方案让CPU处理完这批任务所需的时间最少，
 * 求这个最小的时间。
 输入描述:
 输入包括两行：
 第一行为整数n(1 ≤ n ≤ 50)
 第二行为n个整数length[i](1024 ≤ length[i] ≤ 4194304)，表示每个任务的长度为length[i]kb，
 每个数均为1024的倍数。
 输出描述:
 输出一个整数，表示最少需要处理的时间
 输入例子:
 5
 3072 3072 7168 3072 1024
 输出例子:
 9216

 */
import java.util.*;

public class DualCoreProcessing {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(sc.hasNext()){
			int n=sc.nextInt();
			int[] cpu=new int[n];
			int sum=0;
			for(int i=0;i<n;i++){
				cpu[i]=sc.nextInt()/1024;
				sum+=cpu[i];
			}
			int sumhalf=sum/2;
			int[] time=new int[sum+1];
			for(int i=0;i<cpu.length;i++){
				for(int j=sumhalf;j>=0;j--){
					if(j >= cpu[i]){
						time[j]=Math.max(time[j], time[j-cpu[i]]+cpu[i]);
					}
				}
			}
				System.out.println(Math.max(time[sumhalf], sum-time[sumhalf])*1024);
		}
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		int[] array = new int[n];
//		for(int i = 0 ; i < n ; i++){
//			array[i] = in.nextInt();
//		}
//		System.out.println(getMinProcessingTime(array));
	}
	public static int getMinProcessingTime(int[] array) {
		int sum = 0, max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
			sum += array[i];
		}
		int[][] f = new int[array.length][sum / 2 + 1];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j <= sum / 2; j++) {
				if (i == 0) {
					if (j >= array[i])
						f[i][j] = array[i];
					else
						f[i][j] = 0;
				} else {
					f[i][j] = f[i - 1][j];
					if (array[i] <= j
							&& f[i - 1][j - array[i]] + array[i] > f[i][j]) {
						f[i][j] = f[i - 1][j - array[i]] + array[i];
					}
				}
			}
		}
		// System.out.println(sum/2);
		int result = f[array.length - 1][sum / 2 - 1];
		if (result < sum / 2) {
			return sum - result;
		} else {
			return result;
		}
		// Arrays.sort(array);
		// int tmp;
		// int length = array.length;
		// int cpu1 = 0, cpu2 = 0;
		// int time = 0;
		// for(int i = length -1 ; i >=0 ; i--){
		// if(cpu1 == 0){
		// cpu1 = array[i];
		// }else if(cpu2 == 0){
		// cpu2 = array[i];
		// }
		// if(cpu1 != 0 && cpu2 != 0){
		// if(cpu1 > cpu2){
		// time+= cpu2;
		// cpu1-= cpu2;
		// cpu2 = 0;
		// }else{
		// time+= cpu1;
		// cpu2-= cpu1;
		// cpu1 = 0;
		// }
		// }
		// }
		// if(cpu1 != 0){
		// time+=cpu1;
		// }
		// if(cpu2 != 0){
		// time+=cpu2;
		// }
		// return time;
	}
}
