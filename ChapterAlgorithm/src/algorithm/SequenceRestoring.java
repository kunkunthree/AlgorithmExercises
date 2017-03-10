package algorithm;
/*
 * 数列还原:
 * 		牛牛的作业薄上有一个长度为 n 的排列 A，这个排列包含了从1到n的n个数，但是因为一些原因，
 * 其中有一些位置（不超过 10 个）看不清了，但是牛牛记得这个数列顺序对的数量是 k，
 * 顺序对是指满足 i < j 且 A[i] < A[j] 的对数，请帮助牛牛计算出，符合这个要求的合法排列的数目。
输入描述:
		每个输入包含一个测试用例。每个测试用例的第一行包含两个整数 n 和 k（1 <= n <= 100, 0 <= k <= 1000000000），
		接下来的 1 行，包含 n 个数字表示排列 A，其中等于0的项表示看不清的位置（不超过 10 个）。
输出描述:
		输出一行表示合法的排列数目。
输入例子:
5 5
4 0 0 2 0
输出例子:
		2
 */
import java.util.*;
public class SequenceRestoring {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int result = 0;
		int n = in.nextInt();
		int k = in.nextInt();
		int[] arrayKnown = new int[n+1];
		ArrayList<Integer> arrayUnknown = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> allUnknownArray = new ArrayList<ArrayList<Integer>>();
		boolean[] flags = new boolean[n+1];	//表示该数是否存在，下标为该数
		for(int i = 1 ; i <= n ; i++){
			arrayKnown[i] = in.nextInt();
			if(arrayKnown[i] != 0){
				flags[arrayKnown[i]] = true;
			}
		}
		for(int i = 1 ;  i <= n ; i++){
			if(flags[i] == false){
				arrayUnknown.add(i);
			}
		}
//		System.out.println(arrayUnknown);
		int knownPariNum = 0;
		for(int i = 1 ; i < n ; i++){
			if(arrayKnown[i] != 0){
				for(int j = i+1 ; j <=n ; j++){
					if(arrayKnown[j] != 0 && arrayKnown[i] < arrayKnown[j]){
						knownPariNum++;
					}
				}
			}
		}
		getAllUnknownArray(allUnknownArray, arrayUnknown, 0);
		for(ArrayList<Integer> tmpArrayUnknown : allUnknownArray){
			int pairNum = knownPariNum;
			int[] tmpArrayKnown = Arrays.copyOf(arrayKnown,arrayKnown.length);
			pairNum += getUnknownPairNum(tmpArrayKnown, tmpArrayUnknown);
//			System.out.println("knownPariNum: " + knownPariNum + "  ,   pairNum:" +pairNum + "   ,  k : " + k);
			if(pairNum == k){
				result++;
			}
		}
//		System.out.println(allUnknownArray.size());
		System.out.println(result);
	}
	public static void getAllUnknownArray(ArrayList<ArrayList<Integer>> allUnknownArray , 
			ArrayList<Integer> arrayUnknown, int n){
		if(n == arrayUnknown.size()){
			allUnknownArray.add(new ArrayList<Integer>(arrayUnknown));
		}else{
			for(int i = n ; i < arrayUnknown.size() ; i++){
				Collections.swap(arrayUnknown, n, i);
				getAllUnknownArray(allUnknownArray, arrayUnknown, n+1);
				Collections.swap(arrayUnknown, n, i);
			}
		}
	}
	public static int getUnknownPairNum(int[] arrayKnown,ArrayList<Integer> arrayUnknown){
		int pairNum = 0;
		int j = 0;
		for(int i = 1 ; i < arrayKnown.length ;i++){
			if(arrayKnown[i] == 0){
				arrayKnown[i] = arrayUnknown.get(j++);
				for(int k = 1 ; k < i ; k++){
					if(arrayKnown[k] < arrayKnown[i]){
						pairNum++;
					}
				}
				for(int k = i+1 ; k < arrayKnown.length ; k++){
					if(arrayKnown[k] != 0 && arrayKnown[i] < arrayKnown[k]){
						pairNum++;
					}
				}
			}
		}
//		System.out.println(Arrays.toString(arrayKnown));
		return pairNum;
	}
}
