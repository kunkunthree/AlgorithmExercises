package algorithm.leetcode.algorithm;
/*
 * medium
 * 12. Integer to Roman
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * I:1	II:2		III:3		IV:4		V:5		VI:6	VII:7		VIII:8		IX:9		X:10
 * L:50	XL:40		LX:60
 * C:100		XL:90		LX:110
 * D:500		CD:400		DC:600
 * M:1000		CM:900		MC:1100
 */
public class NO12_IntegertoRoman {
	public static void main(String[] args) {
		int num = 2;
		System.out.println(intToRoman(num));
	}
	//方法1
    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int[] step = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] s = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int i = 0;
        while(num > 0 && i < step.length){
            if(num >= step[i]){
                num-=step[i];
                result.append(s[i]);
                continue;
            }
            i++;
        }
        return result.toString();
    }
    //方法2： 列出"个十百千"位 各自对应的字符串
    public static String intToRoman2(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}
