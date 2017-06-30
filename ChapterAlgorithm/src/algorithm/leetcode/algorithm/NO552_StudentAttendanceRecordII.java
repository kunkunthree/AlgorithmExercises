package algorithm.leetcode.algorithm;
/*
 * hard
 * 552. Student Attendance Record II 
 * Given a positive integer n, return the number of all possible attendance records with length n, 
 * which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:
    'A' : Absent.
    'L' : Late.
    'P' : Present.

A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) 
or more than two continuous 'L' (late).

Example 1:

Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 

Note: The value of n won't exceed 100,000. 
 */
public class NO552_StudentAttendanceRecordII {
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
	}
	//方法1：
	//dp，动态规划
	//O(n)time,O(n)space
	//The answer to the whole problem is T(n), and
	// 	T(n) = A(n) + P(n) + L(n), n ≥ 1.
	// Recursive formula:
	//		 P(n) = A(n - 1) + P(n - 1) + L(n - 1), n ≥ 2.
	// 	A(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
	// 	noAP(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
	// 	L(n) = A(n - 1) + P(n - 1) + A(n - 2) + P(n - 2), n ≥ 3.
	// 	noAL(n) = noAP(n - 1) + noAP(n - 2), n ≥ 3.
	// with Initial value
	// 	A(1) = P(1) = L(1) = 1.
	// 	noAP(1) = noAL(1) = 1.
	// 	L(2) = 3.
	// 	noAL(2) = 2.
	public int checkRecord(int n) {
        if(n <= 0){
            return 0;
        }
        int m = 1000000007;
        int[] A = new int[n];
        int[] L = new int[n];
        int[] P = new int[n];
        int[] PnoA = new int[n];
        int[] LnoA = new int[n];
        A[0] = L[0] = P[0] = PnoA[0] = LnoA[0] = 1;
        if(n > 1){
            L[1] = 3;
            LnoA[1] = 2;
        }
        for(int i = 1 ; i < n ; i++){
            A[i] = (PnoA[i-1] + LnoA[i-1])%m;
            if(i > 1)L[i] = ((A[i-1] + P[i-1])%m + (A[i-2] + P[i-2])%m)%m;
            P[i] = ((A[i-1] + P[i-1])%m + L[i-1])%m;
            PnoA[i] = (PnoA[i-1] + LnoA[i-1])%m;
            if(i > 1)LnoA[i] = (PnoA[i-1] + PnoA[i-2])%m;
        }
        return ((A[n-1] + L[n-1])%m + P[n-1])%m;
    }
	//方法2：
	//方法1的简化：
	// When n ≥ 4, the 3 formulas
	//		 A(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
	//		 noAP(n) = noAP(n - 1) + noAL(n - 1), n ≥ 2.
	//		 noAL(n) = noAP(n - 1) + noAP(n - 2), n ≥ 3.
	// can be simplified to
	//	 	A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4.
	// Finally, the recursive formula group becomes
	//		 P(n) = A(n - 1) + P(n - 1) + L(n - 1), n ≥ 2.
	//		 L(n) = A(n - 1) + P(n - 1) + A(n - 2) + P(n - 2), n ≥ 3.
	//		 A(n) = A(n - 1) + A(n - 2) + A(n - 3), n ≥ 4.
	// Here, noAP(n) and noAL(n) disappeared.
	// with Initial value
	//		 P(1) = 1.
	//		 L(1) = 1, L(2) = 3.
	//		 A(1) = 1, A(2) = 2, A(3) = 4.
	public int checkRecord2(int n) {
		if(n <= 0){
            return 0;
        }
		if(n == 1) return 3;
        int m = 1000000007;
        int[] A = new int [n];
        int[] P = new int [n];
        int[] L = new int [n];
        
        P[0] = 1;
        L[0] = 1;
        L[1] = 3;
        A[0] = 1;
        A[1] = 2;
        if(n > 2)A[2] = 4;
        for(int i = 1; i < n; i++)
        {
            P[i] = ((A[i - 1] + P[i - 1]) % m + L[i - 1]) % m;
            
            if(i > 1) L[i] = ((A[i - 1] + P[i - 1]) % m + (A[i - 2] + P[i - 2]) % m) % m;
            
            if(i > 2) A[i] = ((A[i - 1] + A[i - 2]) % m + A[i - 3]) % m;
        }
        return ((A[n - 1] % m + P[n - 1] % m) % m + L[n - 1] % m) % m;
    }
}
