package algorithm.leetcode.algorithm;
/*
 * medium
 * 396. Rotate Function
 *  Given an array of integers A and let n to be its length.

Assume Bk to be an array obtained by rotating the array A k positions clock-wise,
 we define a "rotation function" F on A as follow:

F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).

Note:
n is guaranteed to be less than 105.

Example:

A = [4, 3, 2, 6]

F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.

 */
public class NO396_RotateFunction {
	public static void main(String[] args) {
		int[] A = new int[]{4, 3, 2, 6};
		System.out.println(maxRotateFunction(A));
	}
	//方法1：
	//迭代，通过推导得到递推公式
	//B(k+1)[i] = B(k)[(i-1)%n]
	//F(k) = 					0*B(k)[0]+1*B(k)[1]+...+(n-1)*B(k)[n-1]
	//F(k+1) =0*B(k+1)[0] + 1*B(k+1)[1] +...+(n-1)*B(k+1)[n-1]
	//	=0*B(k)[n-1]  + 1*B(k)[0] + 2*B(k)[1]+...+(n-2)*B(k)[n-3] + (n-1)*B(k)[n-2]
	//F(k+1)-F(k) = B(k)[0]+B(k)[1]+...+B(k)[n-1] - n*B(k)[n-1] = sum(A) - n*B(k)[n-1]
	//F(k+1) = F(k) + sum(A) - n*B(k)[n-1] = F(k) + sum(A) - n*B(k-1)[n-2]=....=F(k) + sum(A) - n*B(k-i)[n-1-i] 
	//				= F(k) + sum(A) - n*B(0)[n-1-k] = F(k) + sum(A) - n*A[n-1-k]
	public static int maxRotateFunction(int[] A) {
        int n = A.length,max = Integer.MIN_VALUE,sum = 0,F = 0;
        for(int i = 0 ; i < n ; i++){
            F+=i*A[i];
            sum+=A[i];
        }
        max = Math.max(max,F);
        for(int i = 1 ; i < n ; i++){
            F = F + sum - n * A[n-i];
            max = Math.max(max,F);
        }
        return n == 0 ? 0 : max;
    }
	//方法2：
	//方法1的更好写法
	public int maxRotateFunction2(int[] A) {
		int allSum = 0;
		int len = A.length;
		int F = 0;
		for (int i = 0; i < len; i++) {
		    F += i * A[i];
		    allSum += A[i];
		}
		int max = F;
		for (int i = len - 1; i >= 1; i--) {
		    F = F + allSum - len * A[i];
		    max = Math.max(F, max);
		}
		return max; 
	}
}
