package algorithm.leetcode.algorithm;
/*
 * medium
 * 306. Additive Number
 * Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. 
Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8

"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.

1 + 99 = 100, 99 + 100 = 199

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers? 
 */
import java.math.*;
public class NO306_AdditiveNumber {
	public static void main(String[] args) {
		String s;
	}
	//方法1：
	//递归实现
	public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for(int i = 1 ; i <= n/2 ; i++){
        	//从0开始长度大于1的字符串不符合
            if(num.charAt(0) == '0' && i > 1){
                return false;
            }
            BigInteger x1 = new BigInteger(num.substring(0,i));
            for(int j = 1 ; Math.max(i,j) <= n-i-j ; j++){
            	//从0开始长度大于1的字符串不符合
                if(num.charAt(i) == '0' && j > 1){
                    break;
                }
                BigInteger x2 = new BigInteger(num.substring(i,i+j));
                if(isValid(x1,x2,i+j,num)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValid(BigInteger x1,BigInteger x2,int start,String num){
        if(start == num.length()){
            return true;
        }
        x2 = x2.add(x1);
        x1 = x2.subtract(x1);
        String s = x2.toString();
        return num.startsWith(s,start) && isValid(x1,x2,start+s.length(),num);
    }
    //方法2：
    //迭代实现
    public boolean isAdditiveNumber2(String num) {
        int n = num.length();
        for(int i = 1 ; i <= n/2 ; i++){
            for(int j = 1 ; Math.max(i,j) <= n-i-j ; j++){
                if(isValid(i,j,num)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValid(int i,int j,String num){
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        String sum;
        BigInteger x1 = new BigInteger(num.substring(0, i));
        BigInteger x2 = new BigInteger(num.substring(i, i + j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 = x2.add(x1);
            x1 = x2.subtract(x1);
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
    //方法3：
    //迭代实现，利用Long
    public boolean isAdditiveNumber3(String num) {
        int n = num.length();
        for(int i = 1 ; i <= n/2 ; i++){
            for(int j = 1 ; Math.max(i,j) <= n-i-j ; j++){
                if(isValid2(i,j,num)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValid2(int i,int j,String num){
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        String sum;
        Long x1 = new Long(num.substring(0, i));
        Long x2 = new Long(num.substring(i, i + j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 =x1+x2;
            x1 = x2 - x1;
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
}
