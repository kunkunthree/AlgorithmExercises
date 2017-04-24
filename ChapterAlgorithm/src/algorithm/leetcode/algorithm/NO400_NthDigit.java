package algorithm.leetcode.algorithm;

/*
 * easy
 * 400. Nth Digit
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

 Note:
 n is positive and will fit within the range of a 32-bit signed integer (n < 231).

 Example 1:
 Input:
 3
 Output:
 3

 Example 2:
 Input:
 11
 Output:
 0

 Explanation:
 The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

 */
import java.util.*;

public class NO400_NthDigit {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			System.out.println(findNthDigit(n));
		}
	}

	public static int findNthDigit(int n) {
		int count = 1;
		int scale = 1;
		// 计算n是在哪个范围内
		// 0~9为范围1，长度为9×1×1，10~99为范围2,长度为9*2*10，100~999为范围3，长度为9*3*100，以此类推
		while ((n - 1) / count / scale >= 9) {
			n = n - 9 * count * scale;
			count++;
			scale *= 10;
		}
		// 计算该位置所在的数字
		int num = scale + (n - 1) / count;
		// 计算在该数值内的内部位置
		int index = (n - 1) % count;
		return (num + "").charAt(index) - '0';
	}
}
