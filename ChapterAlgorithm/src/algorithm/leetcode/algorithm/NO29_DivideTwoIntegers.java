package algorithm.leetcode.algorithm;
/*
 * medium
 * 29. Divide Two Integers
 *  Divide two integers without using multiplication, division and mod operator.
If it is overflow, return MAX_INT. 
 */
public class NO29_DivideTwoIntegers {
	public static void main(String[] args) {
		int x = -2147483648;
		x= -1 * x;
//		System.out.println(divide(Integer.MIN_VALUE,1));
		System.out.println(0xffffffff);
	}
	//溢出的情况只有被除数为Integer.MIN_VALUE，除数为-1时的情况
	//nteger.MIN_VALUE as dividend is really troublesome. 
	//Thus, I turn everything to negative value and keep finding closest 1,2,4,8... multiples and recursive on rest. 
	//The only case that will cause overflow is Integer.MIN_VALUE / -1, so I list it alone as an edge case.
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1 || divisor == 0){
            return Integer.MAX_VALUE;
        }
        if(dividend > 0 && divisor > 0){
            return divideHelper(-dividend,-divisor);
        }else if(dividend > 0){
            return -divideHelper(-dividend,divisor);
        }else if(divisor > 0){
            return -divideHelper(dividend,-divisor);
        }
        return divideHelper(dividend,divisor);
    }
    public int divideHelper(int dividend,int divisor){
    	// base case
        if(dividend > divisor){
            return 0;
        }
     // get highest digit of divisor
        int count = 0;
        while((divisor << count) >= dividend && divisor << count < 0 && count < 31){
            count++;
        }
        count--;
        return (1<<count)+divide(dividend-(divisor<<count),divisor);
    }
}
