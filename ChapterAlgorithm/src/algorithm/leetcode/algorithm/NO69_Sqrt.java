package algorithm.leetcode.algorithm;
/*
 * easy
 * 69. Sqrt(x) 
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
public class NO69_Sqrt {
    public int mySqrt(int x) {
        if(x == 0 || x == 1){
            return x;
        }
        int left = 1,right = x/2;
        int tmp,mid;
        while(left<=right){
            mid = (left+right)/2;
            tmp = x/mid;
            if(tmp < mid){
                right = mid-1;
            }else if(tmp > mid){
                left = mid+1;
            }else{
                return mid;
            }
        }
        return right;
    }
}
