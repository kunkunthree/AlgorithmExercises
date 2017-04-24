package algorithm.leetcode.algorithm;
/*
 * medium
 * 89. Gray Code
 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2

Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */
import java.util.*;
public class NO89_GrayCode {
	public static void main(String[] args) {
		System.out.println(grayCode2(4));
	}
	//方法1：
	//找规律，当n = 1时，[00,01],当n = 2时，[00,01,11,10]，由于G(n)是graycode序列，那么G(n)逆序也是graycode序列
	//而G(n)最后一个数和G(n)逆序的第一个数是相等的，那么只要在这个数高一位加1，这两个数也是graycode序列
	//那么要把G(n)和G(n)逆序串在一起形成graycode序列，只要把G(n)逆序更高一位置1即可
	//设G(n)逆序为G(-n)
	//则G(n+1) = {G(n),1<<n+G(-n)}
	public static List<Integer> grayCode(int n) {
        List<Integer> list;
        if(n == 0){
            return new ArrayList<Integer>(Arrays.asList(0));
        }
        int add = 1<<(n-1);
        list = grayCode(n-1);
        for(int i = list.size()-1 ; i >= 0 ; i--){
            list.add(list.get(i)+add);
        }
        return list;
    }
	//方法2：
	//公式法：
	public static List<Integer> grayCode2(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
        return result;
    }
}
