package algorithm.leetcode.algorithm;
/*
 * easy
 * 461. Hamming Distance
 * 		The Hamming distance between two integers is the number of positions 
 * at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
Note:
		0 ≤ x, y < 231.
Example:
		Input: x = 1, y = 4
		Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
The above arrows point to positions where the corresponding bits are different.
 */
import java.util.*;
public class NO461_HammingDistance {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int x = in.nextInt();
			int y = in.nextInt();
			System.out.println(hammingDistance(x, y));
		}
	}
    public static int hammingDistance(int x, int y) {
        int num = 0;
        //int tmp = x ^ y;
        while(x != 0 || y != 0){
            if((x&1) != (y&1)){
                num++;
            }
            //tmp>>=1;
            x>>=1;
            y>>=1;
        }
        return num;
    }
}
