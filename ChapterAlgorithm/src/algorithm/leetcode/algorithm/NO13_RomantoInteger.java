package algorithm.leetcode.algorithm;
/*
 * easy
 * 13. Roman to Integer 
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * I:1	II:2		III:3		IV:4		V:5		VI:6	VII:7		VIII:8		IX:9		X:10
 * L:50	XL:40		LX:60
 * C:100		XL:90		LX:110
 * D:500		CD:400		DC:600
 * M:1000		CM:900		MC:1100
 * 
 * similar problems:
 * 12. Integer to Roman 
 */
import java.util.*;
public class NO13_RomantoInteger {
	public static void main(String[] args) {
	}
    public static int romanToInt(String s) {
        char[] c = s.toCharArray();
        int length = c.length;
        int[] b = new int[length];
        int num=0,tmp=0;
        for(int i = 0 ; i < length ; i++){
            switch(c[i]){
                case 'I':tmp = 1;break;
                case 'V':tmp = 5;break;
                case 'X':tmp = 10;break;
                case 'L':tmp = 50;break;
                case 'C':tmp = 100;break;
                case 'D':tmp = 500;break;
                case 'M':tmp = 1000;break;
                default:tmp = 0;break;
            }
            b[i] = tmp;
        }
        int i =0;
        for(i = 0 ; i < length-1 ; i++){
                if(b[i]<b[i+1]){
                    num-=b[i];
                }else{
                    num+=b[i];
                }
        }
        return num+b[i];
    }
}
