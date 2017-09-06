package algorithm.leetcode.algorithm;

import java.util.Arrays;

/*
 * medium
 * 667. Beautiful Arrangement II 
 *  Given two integers n and k, you need to construct a list which contains n different positive integers
 *   ranging from 1 to n and obeys the following requirement:
Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 
has exactly k distinct integers.

If there are multiple answers, print any of them.

Example 1:
Input: n = 3, k = 1
Output: [1, 2, 3]
Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, 
and the [1, 1] has exactly 1 distinct integer: 1.

Example 2:
Input: n = 3, k = 2
Output: [1, 3, 2]
Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, 
and the [2, 1] has exactly 2 distinct integers: 1 and 2.

Note:
    The n and k are in the range 1 <= k < n <= 104.
 */
public class NO667_BeautifulArrangementII {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(constructArray2(6,3)));
	}
	//方法1：
	//找规律
	//k = 1时，1 2 3 4 ... n; 差的绝对值：1,1,1,1,...,1
	//k = 2时，1 n n-1 n-2 .... 2；差的绝对值：n-1,1,1,1,1...
	//k = 3时，1 n 2 3 4 ... n-1；差的绝对值：n-1,n-2,1,1,1....
	//k = 4时，1 n 2 n-1 n-2 .... 3；差的绝对值：n-1,n-2,n-3,1,1....
	//规律，当k>1时，从数组的头或者尾部添加一个数进去，下一次换另一边
	//				当k=1时，上一次如果是从头部取数，则继续从头部取，如果是从尾部取数，则继续从尾部取
	public static int[] constructArray(int n, int k) {
        int count = 1;
        int min = 1,max = n;
        int[] result = new int[n];
        boolean flag = true;
        for(int i = 0 ; i < n ; i++){
            if(flag == true){
                result[i] = min++;
                if(count < k){
                    flag = false;
                    count++;
                }
            }else{
                result[i] = max--;
                if(count < k){
                    flag = true;
                    count++;
                }
            }
        }
        return result;
    }
	//方法2：
	public static int[] constructArray2(int n, int k) {
        int[] res = new int[n];
        for (int i = 0, l = 1, r = n; l <= r; i++)
            res[i] = k > 1 ? (k-- % 2 != 0 ? l++ : r--) :  l++;
        return res;
    }
}
