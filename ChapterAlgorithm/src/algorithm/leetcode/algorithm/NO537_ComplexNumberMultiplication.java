package algorithm.leetcode.algorithm;
/*
 * medium
 * 537. Complex Number Multiplication 
 *  Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.

Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.

Note:
    1.	The input strings will not have extra blank.
    2.	The input strings will be given in the form of a+bi, 
		    where the integer a and b will both belong to the range of [-100, 100].
		     And the output should be also in this form.

 */
public class NO537_ComplexNumberMultiplication {
	//方法1：
	//直接法
	//直接分别得到字符串的实部和虚部，然后进行运算，得到结果的实部和虚部
	public String complexNumberMultiply(String a, String b) {
        int real = 0,imaginary = 0;
        int[] cn1 = getComplexNumber(a);
        int[] cn2 = getComplexNumber(b);
        real = cn1[0]*cn2[0] - cn1[1]*cn2[1];
        imaginary = cn1[0]*cn2[1] + cn1[1]*cn2[0];
        return real + "+" + imaginary + "i";
    }
    private int[] getComplexNumber(String s){
        int[] result = new int[2];
        int part = 0,length = s.length(),num = 0,p = 1;
        char c;
        for(int i = 0 ; i < length ; i++){
            c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                num = num * 10 + c - '0';
            }else if(c == '+'){
                result[part] = num*p;
                num = 0;
                p = 1;
                part = 1;
            }else if(c == '-'){
                p = -1;
            }else if(c == 'i'){
                part = 1;
            }
        }
        if(num != 0){
            result[part] = num * p;
        }
        return result;
    }
    //方法2：
    //方法1的简化形式
    public String complexNumberMultiply2(String a, String b) {
        String result = "";
        String[] A = a.split("\\+");
        String[] B = b.split("\\+");
        int a1 = Integer.parseInt(A[0]);
        int b1 = Integer.parseInt(A[1].replace("i",""));

        int a2 = Integer.parseInt(B[0]);
        int b2 = Integer.parseInt(B[1].replace("i",""));

        int a1a2 = a1 * a2;
        int b1b2 = b1 * b2;
        int a1b2a2b1 = (a1 * b2) + (b1 * a2);

        String afinal = (a1a2 + (-1 * b1b2)) + "";
        String bfinal = a1b2a2b1 + "i";
        result = afinal+"+"+bfinal;
        return result;
    }
}
