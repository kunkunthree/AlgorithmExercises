package algorithm.leetcode.algorithm;
/*
 * medium
 * 357. Count Numbers with Unique Digits
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:
Given n = 2, return 91.
 (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99]) 
 */
public class NO357_CountNumberswithUniqueDigits {
	//方法1：
	//dp，O(n)time
	//设f(i)为有i位不同数字的个数
	//f(0) = 1;
	//f(1) = 10;
	//当i>=2时，相当于从10个数字中，先抽取最高位，然后依次抽取不同的数字，
	//第一位不能为0，所以第一位的选择有9个，然后后面依次时9,8,...依次递减直到为0
	//特殊情况，i = 1时，第一位可以为0，f(1) = 10;
	//f(2) = 9 * 9;
	//f(3) = 9 * 9 * 8;
	//当i>=2时，f(i+1) = f(i)*(9-n+1)
	//则n位数内所有不同位的个数有f(n) = f(1)+f(2)+f(3)
	public int countNumbersWithUniqueDigits(int n) {
        if(n == 0){
            return 1;
        }
        int result = 10;
        int uniqueDigits = 9;
        for(int i = 9 ; i > 9-n+1 ; i--){
            uniqueDigits*=i;
            result += uniqueDigits;
        }
        return result;
    }
}
