package algorithm.leetcode.algorithm;
/*
 * easy
 * 191. Number of 1 Bits 
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has 
 * (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, 
 * so the function should return 3.
 * 
 * similar problems:
190. Reverse Bits 
231. Power of Two 
338. Counting Bits 
401. Binary Watch
461. Hamming Distance 
 */
public class NO191_Numberof1Bits {
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            if((n&1) == 1){
                count++;
            }
            n>>>=1;
        }
        return count;
    }
}
