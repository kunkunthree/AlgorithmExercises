package algorithm.leetcode.algorithm;
/*
 * easy
 * 231. Power of Two
 * Given an integer, write a function to determine if it is a power of two. 
 */
public class NO231_PowerofTwo {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0){
            return false;
        }
        while(n > 0){
            if((n&1) == 1 && n > 1){
                return false;
            }
            n>>=1;
        }
        return true;
    }
    //更简单的方法
    public boolean isPowerOfTwo2(int n) {
        if(n<=0) return false;
        return (n&(n-1)) == 0;
    }
}
