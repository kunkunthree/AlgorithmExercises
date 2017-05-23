package algorithm.leetcode.algorithm;
/*
 * medium
 * 592. Fraction Addition and Subtraction 
 * Given a string representing an expression of fraction addition and subtraction, 
 * you need to return the calculation result in string format. The final result should be irreducible fraction. 
 * If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. 
 * So in this case, 2 should be converted to 2/1.

Example 1:
Input:"-1/2+1/2"
Output: "0/1"

Example 2:
Input:"-1/2+1/2+1/3"
Output: "1/3"

Example 3:
Input:"1/3-1/2"
Output: "-1/6"

Example 4:
Input:"5/3+1/3"
Output: "2/1"

Note:
    1.	The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
    2.	Each fraction (input and output) has format ±numerator/denominator. 
    		If the first input fraction or the output is positive, then '+' will be omitted.
    3.	The input only contains valid irreducible fractions, where the numerator and denominator of 
    		each fraction will always be in the range [1,10]. If the denominator is 1, 
    		it means this fraction is actually an integer in a fraction format defined above.
    4.	The number of given fractions will be in the range [1,10].
    5.	The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.

 */
public class NO592_FractionAdditionandSubtraction {
	public static void main(String[] args) {
		String expression = "-1/2+1/2";
		System.out.println(fractionAddition(expression));
	}
	//方法1：
	//利用最大公约数和最小公倍数在每次进行加减运算时进行分母通分，然后对分子进行运算
	//最后对最后结果进行约分
	public static String fractionAddition(String expression) {
        int p = 1,n = expression.length(), num = 0,gcd = 0;
        int preNumerator = 0, preDenominator = 1, curNumerator = 0, curDenominator = 1;
        char c;
        for(int i = 0 ; i <= n ; i++){
            if(i == n){
                c = '+';
            }else{
                c = expression.charAt(i);
            }
            if(c == '+' || c == '-'){
            	if(num != 0){
                    curDenominator = num * p;
                }
                //最大公约数
                gcd = getGCD(preDenominator,curDenominator);
                curNumerator*= preDenominator / gcd;
                preNumerator*= curDenominator / gcd;
                preNumerator+=curNumerator;
                preDenominator = preDenominator * curDenominator / gcd;
                num = 0;
                if(c == '+'){
                    p = 1;
                }else{
                    p = -1;
                }
            }else if(c >= '0' && c <= '9'){
                num = num*10 + c - '0';
            }else if(c == '/'){
                curNumerator = num * p;
                p = 1;
                num = 0;
            }
        }
        gcd = getGCD(preDenominator,Math.abs(preNumerator));
        curNumerator = preNumerator / gcd;
        curDenominator = preDenominator / gcd;
        return curNumerator + "/" + curDenominator;
    }
    //求最大公约数
    private static int getGCD(int x,int y){
        return y == 0 ? x : getGCD(y,x%y);
    }
    //方法2：
    //其他实现形式
//    public String fractionAddition2(String expression) {
//        String[] fracs = expression.split("(?=[-,+])"); // splits input string into individual fractions
//        String res = "0/1";
//        for (String frac : fracs) res = add(res, frac); // add all fractions together
//        return res;
//    }
//
//    public String add(String frac1, String frac2) {
//        int[] f1 = Stream.of(frac1.split("/")).mapToInt(Integer::parseInt).toArray(), 
//              f2 = Stream.of(frac2.split("/")).mapToInt(Integer::parseInt).toArray();
//        int lcm = f1[1]*f2[1]/gcd(f1[1], f2[1]);
//        int numerator = lcm*f1[0]/f1[1] + lcm*f2[0]/f2[1];
//        String sign = "";
//        if (numerator < 0) {sign = "-"; numerator *= -1;}
//        return sign + numerator/gcd(numerator, lcm) + "/" + lcm/gcd(numerator, lcm); // construct reduced fraction
//    }
//
//    // Computes gcd using Euclidean algorithm
//    public int gcd(int x, int y) { return x == 0 || y == 0 ? x + y : gcd(y, x % y); }
}
