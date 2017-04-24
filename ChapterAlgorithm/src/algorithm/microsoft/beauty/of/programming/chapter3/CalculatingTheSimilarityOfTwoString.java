package algorithm.microsoft.beauty.of.programming.chapter3;
/*
 * 3.3	计算字符串的相似度：
 * 		许多程序会大量使用字符串。对于不同的字符串，我们希望能够有办法判断其相似程序。
 * 我们定义一套操作方法来把两个不相同的字符串变得相同，具体的操作方法为：
1.修改一个字符（如把“a”替换为“b”）;　　
2.增加一个字符（如把“abdd”变为“aebdd”）;
3.删除一个字符（如把“travelling”变为“traveling”）;
比如，对于“abcdefg”和“abcdef”两个字符串来说，我们认为可以通过增加/减少一个“g”的方式来达到目的。上面的两种方案，都仅需要一 次 。把这个操作所需要的次数定义为两个字符串的距离，而相似度等于“距离+1”的倒数。也就是说，“abcdefg”和“abcdef”的距离为1，相似度 为1/2=0.5。
给定任意两个字符串，你是否能写出一个算法来计算它们的相似度呢？

分析与解法：
不难看出，两个字符串的距离肯定不超过它们的长度之和（我们可以通过删除操作把两个串都转化为空串）。
虽然这个结论对结果没有帮助，但至少可以知道，任意两个字符串的距离都是有限的。
我们还是就住集中考虑如何才能把这个问题转化成规模较小的同样的子问题。如果有两个串A=xabcdae和B=xfdfa，
它们的第一个字符是相同的，只要计算A[2,…,7]=abcdae和B[2,…,5]=fdfa的距离就可以了。
但是如果两个串的第一个字符不相同，那么可以进行 如下的操作（lenA和lenB分别是A串和B串的长度）。
1.删除A串的第一个字符，然后计算A[2,…,lenA]和B[1,…,lenB]的距离。
2.删除B串的第一个字符，然后计算A[1,…,lenA]和B[2,…,lenB]的距离。
3.修改A串的第一个字符为B串的第一个字符，然后计算A[2,…,lenA]和B[2,…,lenB]的距离。
4.修改B串的第一个字符为A串的第一个字符，然后计算A[2,…,lenA]和B[2,…,lenB]的距离。
5.增加B串的第一个字符到A串的第一个字符之前，然后计算A[1,…,lenA]和B[2,…,lenB]的距离。
6.增加A串的第一个字符到B串的第一个字符之前，然后计算A[2,…,lenA]和B[1,…,lenB]的距离。
在这个题目中，我们并不在乎两个字符串变得相等之后的字符串是怎样的。所以，可以将上面的6个操作合并为：
1.一步操作之后，再将A[2,…,lenA]和B[1,…,lenB]变成相同字符串。
2.一步操作之后，再将A[2,…,lenA]和B[2,…,lenB]变成相同字符串。
3.一步操作之后，再将A[1,…,lenA]和B[2,…,lenB]变成相同字符串。
 */
public class CalculatingTheSimilarityOfTwoString {
	public static void main(String[] args) {
		String s1 = "abcdefgaaaaaaaa";
		String s2 = "abcdefeaaba";
		System.out.println(calculateStringDistance(s1.toCharArray(), 0, s1.length()-1, s2.toCharArray(), 0, s2.length()-1));
//		System.out.println(false ^ false);
	}
	public static int calculateStringDistance(char[] s1,int begin1,int end1,char[] s2,int begin2,int end2){
		if(begin1 > end1 && begin2 > end2){
			return 0;
		}
		if((begin1 > end1) ^ (begin2 > end2) ){
//			if(end2 > begin1){
//				return end2 - begin1 + 1;
//			}else if(end1 > begin2){
//				return end1 - begin2 +1;
//			}else{
//				return 1;
//			}
			return max(end1 - begin2 + 1, end2 - begin1 +1);
		}
//		if(begin2 > end2){
//				if(end1 > begin2){
//					return end1 - begin2 +1;
//				}else{
//					return end2 - begin1 + 1;
//				}
//		}
		
		if(s1[begin1] == s2[begin2]){
			return calculateStringDistance(s1, begin1+1, end1, s2, begin2+1, end2);
		}else{
			int d1 = calculateStringDistance(s1, begin1+1, end1, s2, begin2, end2);
			int d2 = calculateStringDistance(s1, begin1, end1, s2, begin2+1, end2);
			int d3 = calculateStringDistance(s1, begin1+1, end1, s2, begin2+1, end2);
			return min(d1,d2,d3)+1;
		}
	}
	public static int min(int...d){
		int minimun = Integer.MAX_VALUE;
		for(int x : d){
			if(x < minimun){
				minimun = x;
			}
		}
		return minimun;
	}
	public static int max(int...d){
		int maximum = Integer.MIN_VALUE;
		for(int x : d){
			if(x > maximum){
				maximum = x;
			}
		}
		return maximum;
	}
}
