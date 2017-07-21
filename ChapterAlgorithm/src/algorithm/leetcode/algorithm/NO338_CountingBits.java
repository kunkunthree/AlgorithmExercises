package algorithm.leetcode.algorithm;
/*
 * medium
 * 338. Counting Bits
 * Given a non negative integer number num. 
 * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation 
 * and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

    It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
    		But can you do it in linear time O(n) /possibly in a single pass?
    Space complexity should be O(n).
    Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++
     		or in any other language.

similar problems:
191. Number of 1 Bits 
 */
public class NO338_CountingBits {
	//方法1：
	//(2^n,2^(n+1))的1的个数比(2^(n-1),2^n)多1，因为多了一个高位1，2^n的1的个数都是1,0的1的个数时0
	public int[] countBits(int num) {
        int[] result = new int[num+1];
        result[0] = 0;
        int i = 1;
        while(i <= num){
            result[i] = 1;
            for(int j = i+1 ; j < 2*i && j <= num ; j++){
                result[j] = 1+result[j-i];
            }
            i*=2;
        }
        return result;
    }
	//方法2：
	//2n+1的1的个数比n多1，2n的1的个数和n相等
	public int[] countBits2(int num) {
	    int[] f = new int[num + 1];
	    for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
	    return f;
	}
	//方法3：
	//方法1的简化写法
	public int[] countBits3(int num) {
	    int result[] = new int[num + 1];
	    int offset = 1;
	    for (int index = 1; index < num + 1; ++index){
	        if (offset * 2 == index){
	            offset *= 2;
	        }
	        result[index] = result[index - offset] + 1;
	    }
	    return result;
	}
}
