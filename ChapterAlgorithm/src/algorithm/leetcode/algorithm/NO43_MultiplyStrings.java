package algorithm.leetcode.algorithm;
/*
 * medium
 * 43. Multiply Strings
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:
    The length of both num1 and num2 is < 110.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.

 */
public class NO43_MultiplyStrings {
	public static void main(String[] args) {
		String num1 = "123";
		String num2 = "456";
		System.out.println(multiply(num1, num2));
	}
	//方法1：
	//1，The product of two numbers cannot exceed the sum of the two lengths. (e.g. 99 * 99 cannot be five digit)
	//2，	int d1 = num1.charAt(i) - '0';
	//			int d2 = num2.charAt(j) - '0';
	//			products[i + j + 1] += d1 * d2;
    public static String multiply(String num1, String num2) {
        int length1 = num1.length(),length2 = num2.length();
        int[] product = new int[length1+length2];
        int x,y;
        for(int i = 0 ; i < length1 ; i++){
            x = num1.charAt(i)-'0';
            for(int j = 0 ; j < length2 ; j++){
                y = num2.charAt(j) - '0';
                product[i+j+1]+= x*y;
            }
        }
        int carry = 0;
        for(int i = length1+length2-1 ; i>=0 ; i--){
            product[i] = product[i]+carry;
            carry = product[i]/10;
            product[i] = product[i]%10;
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(i < length1+length2 && product[i] == 0){
            i++;
        }
        while(i < length1+length2){
            result.append(product[i++]);
        }
        return result.length() == 0 ? "0" : result.toString();
    }
    //方法2：
    //`num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]` 
    public String multiply2(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
       
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }  
        
        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
