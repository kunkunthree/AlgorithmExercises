package algorithm.leetcode.algorithm;
/*
 * medium
 * 397. Integer Replacement 
 *  Given a positive integer n and you can do operations as follow:

    If n is even, replace n with n/2.
    If n is odd, you can replace n with either n + 1 or n - 1.

What is the minimum number of replacements needed for n to become 1?

Example 1:
Input:
8
Output:
3
Explanation:
8 -> 4 -> 2 -> 1

Example 2:
Input:
7
Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1

 */
public class NO397_IntegerReplacement {
	//方法1：
	//迭代，尽量执行除法操作，
	//如果n+1能做两次除法操作，就选择+1，即当(n+1)/2是偶数时，选择+1
	//否则，(n-1)/2是偶数，则选择-1
	public int integerReplacement(int n) {
        int count = 0;
        long tmp = n;
        while(tmp > 1){
            if(tmp == 3){
                tmp--;
            }else if((tmp&1) == 1){
                if(((tmp+1)&2) == 0){
                    tmp++;
                }else{
                    tmp--;
                }
            }else{
                tmp>>=1;
            }
            count++;
        }
        return count;
    }
	//方法2：
	//方法1的更简化写法，当n为奇数时，只需要看倒数第二位是否为1，是则+1，否则-1
	public int integerReplacement2(int n) {
	    int c = 0;
	    while (n != 1) {
	        if ((n & 1) == 0) {
	            n >>>= 1;
	        } else if (n == 3 || ((n >>> 1) & 1) == 0) {
	            --n;
	        } else {
	            ++n;
	        }
	        ++c;
	    }
	    return c;
	}
}
