package algorithm;
/*
 * 小易喜欢的单词:
 * 		小易喜欢的单词具有以下特性：
	1.单词每个字母都是大写字母
	2.单词没有连续相等的字母
	3.单词没有形如“xyxy”(这里的x，y指的都是字母，并且可以相同)这样的子序列，子序列可能不连续。
	例如：
	小易不喜欢"ABBA"，因为这里有两个连续的'B'
	小易不喜欢"THETXH"，因为这里包含子序列"THTH"
	小易不喜欢"ABACADA"，因为这里包含子序列"AAAA"
	小易喜欢"A","ABA"和"ABCBA"这些单词
	给你一个单词，你要回答小易是否会喜欢这个单词。
输入描述:
		输入为一个字符串，都由大写字母组成，长度小于100
输出描述:
		如果小易喜欢输出"Likes",不喜欢输出"Dislikes"
输入例子:
		AAA
输出例子:
		Dislikes
 */
import java.util.*;
public class LikedWords {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		char[] array = in.nextLine().toCharArray();
		if(ifLiked(array)){
			System.out.println("Likes");
		}else{
			System.out.println("Dislikes");
		}
	}
	public static boolean ifLiked(char[] array){
		int index = 0;
		if(array[index] < 'A' || array[index] > 'Z'){
			return false;
		}
		int[] countChar = new int [26];
		countChar[array[index] - 'A']+=1;
		index++;
		while(index < array.length){
			if(array[index] < 'A' || array[index] > 'Z'){
				return false;
			}
			if(array[index -1] == array[index]){
				return false;
			}
			countChar[array[index] - 'A']+=1;
			if(countChar[array[index] - 'A'] == 4){
				return false;
			}
			if(countChar[array[index] - 'A'] > 0){
				int preIndex = index - 2;
				int[] record =  new int[26];
				while(preIndex >= 0 && array[index] != array[preIndex]){
					if(record[array[preIndex] - 'A'] == 0){
						record[array[preIndex] - 'A'] = 1;
					}
					preIndex--;
				}
				while(preIndex >= 0){
					if(record[array[preIndex] - 'A'] == 1){
						return false;
					}
				}
			}
			index++;
		}
		return true;
	}
}
