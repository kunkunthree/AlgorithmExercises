package algorithm.microsoft.beauty.of.programming.chapter1;
/*
 * 一摞烙饼问题:
 *  			星期五的晚上，一帮同事在希格玛大厦附近的“硬盘酒吧”多喝了几杯。程序员多喝了几杯之后谈什么呢？
 *  自然是算法问题。有个同事说：“我以前在餐馆打工，顾客经常点非常多的烙饼。店里的饼大小不一，
 *  我习惯在到达顾客饭桌前，把一摞饼按照大小次序摆好——小的在上面，大的在下面。由于我一只手托着盘子，
 *  只好用另一只手，一次抓住最上面的几块饼，把它们上下颠倒个个儿，反复几次之后，这摞烙饼就排好序了。
 *  我后来想，这实际上是个有趣的排序问题：假设有n块大小不一的烙饼，那最少要翻几次，才能达到最后大小有序
 *  的结果呢？
 *  你能否写出一个程序，对于n块大小不一的烙饼，输出最优化的翻饼过程呢？
 *  
 *  
10
3 2 1 6 5 4 9 8 7 0
6
4 8 6 8 4 9
 */
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
public class PancakeSorting {
	public static int[] cakeArray,swapArray,reverseCakeArray,reverseCakeArraySwap;
	//cakeArray：烙饼信息数组
	//swapArray：交换结果数组
	//reverseCakeArray：当前翻转烙饼信息数组
	//reverseCakeArraySwap：当前翻转烙饼交换结果数组
	public static int searchTime = 0;
	public static int cakeNum;
	public static int maxSwap;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		cakeNum = in.nextInt();
		 cakeArray = new int[cakeNum];
		for(int i = 0 ; i < cakeNum ; i ++){
			cakeArray[i] = in.nextInt();
		}
		//设置最多交换次数
		maxSwap = upBound(cakeNum);
		//初始化交换结果数组
		 swapArray = new int[maxSwap];
		//初始化中间交换结果数组
		 reverseCakeArray = new int[cakeNum];
		for(int i = 0 ; i < cakeNum ; i++){
			reverseCakeArray[i]	 = cakeArray[i];
		}
		 reverseCakeArraySwap = new int[maxSwap];
		
		 searchTime = 0;
		 
		Search(reverseCakeArray,reverseCakeArraySwap,0);
		output(swapArray,reverseCakeArray, maxSwap, searchTime);
	}
	public static int upBound(int cakeNum){
		return 2 * cakeNum;
	}
	public static int lowerBound(int[] cakeArray, int cakeNum){
		int t = 0,ret = 0;
		//根据当前数组的排序信息情况来判断最少需要交换多少次
		for(int i = 1 ; i < cakeNum ; i++){
			t = cakeArray[i] - cakeArray[i-1];
			if(t ==1 || t == -1){
				
			}else{
				ret++;
			}
		}
		return ret;
	}
	//排序主函数
	public static void Search(int[] cakeArray1,int[] reverseCakeArraySwap,int step){
		int i ,estimate;
		searchTime++;
		//估算这次搜索需要的最小交换次数
		estimate = lowerBound(cakeArray1, cakeNum);
		if(step +estimate > maxSwap){
//			System.out.println("至少所需排序次数超越最大次数:" + (step +estimate) + "   " + maxSwap);
			return;
		}
//		System.out.println("至少所需排序次数:" + step +"  "+estimate + "   " + maxSwap);
		//如果已经排好序，即翻转完成，输出结果
		if(isSorted(cakeArray1, cakeNum)){
			System.out.println("排序完成:" + step);
			if(step < maxSwap){
				maxSwap = step;
				for(i = 0 ; i < maxSwap ; i++){
					swapArray[i] = reverseCakeArraySwap[i];
				}
				System.out.println(Arrays.toString(reverseCakeArraySwap));
				System.out.println(Arrays.toString(cakeArray1));
				for(i = 0 ; i < cakeNum ; i++){
					PancakeSorting.reverseCakeArray[i] = cakeArray1[i];
				}
			}
			return;
		}
		for(i = 1 ; i < cakeNum ; i++){
//			if(reverseCakeArraySwap[0] == 2 && reverseCakeArraySwap[1] == 1 && reverseCakeArraySwap[2] == 1){
//				System.out.println("2112			" + i);
//				System.out.println(Arrays.toString(reverseCakeArraySwap));
//				System.out.println(Arrays.toString(cakeArray));
//			}
//			int[] tmpCakeArray = Arrays.copyOf(cakeArray, cakeArray.length);
			int[] tmpCakeArray = new int[cakeArray1.length];
			for(int j = 0 ; j < cakeArray1.length ;j++){
				tmpCakeArray[j] = cakeArray1[j];
			}
			if(i == 1 && step == 0){
				int x= 1;
				if(x != 1)return;
			}
//			int[] tmpReverseCakeArraySwap = Arrays.copyOf(reverseCakeArraySwap, reverseCakeArraySwap.length);
			int[] tmpReverseCakeArraySwap = new int[reverseCakeArraySwap.length];
			for(int j = 0 ; j < reverseCakeArraySwap.length ;j++){
				tmpReverseCakeArraySwap[j] = reverseCakeArraySwap[j];
			}
			revert(tmpCakeArray,0, i);
			tmpReverseCakeArraySwap[step] = i;
			Search(tmpCakeArray,tmpReverseCakeArraySwap,step+1);
			if(i == 1 && step == 0){
				int x= 0;
				if(x != 0)return;
			}
//			revert(0, i);
		}
	}
	//判断是否排好序
	public static boolean isSorted(int[] cakeArray,int cakeNum){
		for(int i = 1 ; i < cakeNum ; i++){
			if(cakeArray[i] < cakeArray[i-1]){
				return false;
			}
		}
		return true;
	}
	//翻转烙饼信息
	public static void revert(int[] reverseCakeArray, int begin , int end){
		int i,j,t;
		for(i = begin,j = end; i < j ; i++,j--){
			t = reverseCakeArray[i];
			reverseCakeArray[i] = reverseCakeArray[j];
			reverseCakeArray[j] = t;
		}
	}
	public static void output(int[] arraySwap,int[] reverseCakeArray,int maxSwap,int searchTime){
		for(int i = 0 ; i < maxSwap ; i++){
			System.out.print(arraySwap[i] + " ");
		}
		System.out.println();
		for(int i = 0 ; i < cakeNum ; i++){
			System.out.print(reverseCakeArray[i] + " ");
		}
		System.out.println();
		System.out.println("初始数组：" + Arrays.toString(cakeArray));
		System.out.println("交换过程：");
		for(int i = 0 ; i < maxSwap ; i++){
			revert(cakeArray,0,arraySwap[i]);
			System.out.println(Arrays.toString(cakeArray));
		}
		System.out.println("\n Search times: " + searchTime);
		System.out.println("Total Swap times = " + maxSwap);
	}

}
