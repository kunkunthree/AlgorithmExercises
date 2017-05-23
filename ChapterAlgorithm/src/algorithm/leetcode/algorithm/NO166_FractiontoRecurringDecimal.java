package algorithm.leetcode.algorithm;

import java.math.BigDecimal;
import java.text.Bidi;

/*
 * medium
 * 166. Fraction to Recurring Decimal
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

    Given numerator = 1, denominator = 2, return "0.5".
    Given numerator = 2, denominator = 1, return "2".
    Given numerator = 2, denominator = 3, return "0.(6)".

 */
import java.util.*;
public class NO166_FractiontoRecurringDecimal {
	public static void main(String[] args) {
		int numerator = -1;
		int denominator = Integer.MIN_VALUE;
		System.out.println(fractionToDecimal(numerator, denominator));
	}
	//方法1：
	public static String fractionToDecimal(int numerator, int denominator) {
		Map<Long,Integer> map = new HashMap<>();
        String pn = "";
        if(numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0){
            pn = "-";
        }
        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);
        long integerPart = n/d,index = 0;
        n = (n % d) * 10;
        StringBuilder sb = new StringBuilder();
        while(n != 0 && !map.containsKey(n)){
            while(!map.containsKey(n)){
            	map.put(n,sb.length());
            	if(n < d){
            		sb.append(0);
            		n*= 10;
            	}
            }
            if(n < d && map.containsKey(n)){
                break;
            }
            sb.append(n/d);
            n = (n % d) * 10 ;
        }
        if(sb.length()>0){
            if(n != 0){
                sb.insert((int)map.get(n),'(');
                sb.append(')');
            }
            sb.insert(0,'.');
        }
        sb.insert(0,integerPart);
        sb.insert(0,pn);
        return sb.toString();
    }
	//方法2：
	public String fractionToDecimal2(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        
        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}
