package algorithm.microsoft.beauty.of.programming.chapter3;
/*
 * 3.2	电话号码对应英语单词：
 * 电话的号码盘上的数字对应字母
 * 1）尽可能快地得到电话号码对应的字母组合
 * 2）从里面找出能够对应单词的字母组合
 */
import java.util.*;
public class PhoneNumberCorrespondingToCharacters {
	public static char[][] c = new char[][]{
		"".toCharArray(),
		"".toCharArray(),
		"ABC".toCharArray(),
		"DEF".toCharArray(),
		"GHI".toCharArray(),
		"JKL".toCharArray(),
		"MNO".toCharArray(),
		"PQRS".toCharArray(),
		"TUV".toCharArray(),
		"WXYZ".toCharArray(),
	};
	public static int[] total = new int[]{0,0,3,3,3,3,3,4,3,4};
	public static void main(String[] args) {
		int[] phoneNum = new int[]{2,3,4,5};
//		printAllCharactersCombination(new int[]{2,2,2});
		recursiveSearch(phoneNum,new int[phoneNum.length], 0, phoneNum.length);
	}
	//循环实现
	public static void printAllCharactersCombination(int[] phoneNum){
		int length = phoneNum.length;
		int[] answer = new int[length];
		while(true){
			for(int i = 0 ; i < length ; i++){
				System.out.print(c[phoneNum[i]][answer[i]]);
			}
			System.out.println();
			int k = length - 1;
			while(k >= 0){
				if(answer[k] < total[phoneNum[k]] - 1){
					answer[k]++;
					break;
				}else{
					answer[k] = 0;
					k--;
				}
			}
			if(k < 0){
				break;
			}
		}
	}
	public static void recursiveSearch(int[] phoneNum , int[] answer,int index, int n){
		if(index == n){
			for(int i = 0 ; i < n ; i++){
				System.out.print(c[phoneNum[i]][answer[i]]);
			}
			System.out.println();
			return;
		}
		for(answer[index] = 0 ; answer[index] < total[phoneNum[index]] ; answer[index]++){
			recursiveSearch(phoneNum, answer, index+1, n);
		}
	}
}
