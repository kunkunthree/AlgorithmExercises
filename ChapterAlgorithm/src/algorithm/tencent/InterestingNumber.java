package algorithm.tencent;
/*
 * 有趣的数字:
 * 		小Q今天在上厕所时想到了这个问题：有n个数，两两组成二元组，差最小的有多少对呢？差最大呢？
输入描述:
 		输入包含多组测试数据。
对于每组测试数据：
 		N - 本组测试数据有n个数
 		a1,a2...an - 需要计算的数据
 保证:
 		1<=N<=100000,0<=ai<=INT_MAX.
输出描述:
		对于每组数据，输出两个数，第一个数表示差最小的对数，第二个数表示差最大的对数。
输入例子:
6
45 12 45 32 5 6
输出例子:
1 2

	1.先排序
         特殊情况：如果排完序之后发现数组中所有数都相同，直接输出结果
             结果为：差最大个数 = 差最小个数 = （n * (n-1))/2;(两两组合)
    2.统计数组中每种数字的个数（这里用的map）
    3.计算差最小个数
        3.1.如果数组中没有重复数字，说明最小差不为0，最小差肯定是数组中相邻两个数的差
            因此，遍历一边数组，计算并统计最小差。
        3.2.如果数组中有重复数字，说明最小差是0，此时，遍历一边map，数字个数不为0的
            数字会产生最小差0，利用公式计算即可
    4.计算差最大个数
        只有一种情况，最大值与最小值的两两组合，即最大值个数 * 最小值个数
    算法复杂度：排序O(nlogn), 统计O(n)
 */
import java.util.*;
public class InterestingNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int num = in.nextInt();
			int[] array = new int[num];
			for(int i = 0; i < num ; i++){
				array[i] = in.nextInt();
			}
			//排序，升序
			Arrays.sort(array);
			//如果数组中的值全相同，直接两两组合
			if(array[0] == array[num-1]){
				int tmp = num * (num -1) / 2;
				System.out.println(tmp + " " + tmp);
				continue;
			}
			Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
			for(int i = 0 ; i < num ; i++){
				if(map.containsKey(array[i])){
					map.put(array[i], map.get(array[i])+1);
				}else{
					map.put(array[i], 1);
				}
			}
			//求最小差个数
			int minCount = 0;
//			System.out.println(map.size() + "  " + num);
			if(map.size() == num){
				int min = array[1]-array[0];
				minCount = 1;
				for(int i = 2 ; i< num ; i++){
					int tmp = array[i] - array[i-1];
					if(tmp < min){
						min = tmp;
						minCount = 1;
					}else if (tmp == min){
						minCount++;
					}
				}
//				System.out.println("min: " + min);
			}else{
				for(Integer key : map.keySet()){
					int tmpCount = map.get(key);
					if(tmpCount > 1){
						minCount += tmpCount *  (tmpCount - 1) / 2;
					}
				}
			}
			//求最大差个数
			int maxCount = 0;
			List<Integer> keyset = new ArrayList<Integer>(map.keySet());
			int maxNum = map.get(keyset.get(keyset.size()-1));
			int minNum = map.get(keyset.get(0));
			maxCount = maxNum * minNum;
//			System.out.println(Arrays.toString(array));
			System.out.println(minCount + " " + maxCount);
		}
	}
}
