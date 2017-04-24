package algorithm.leetcode.algorithm;
/*
 * easy
 * 405. Convert a Number to Hexadecimal 
 *  Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, 
 *  two’s complement method is used.

Note:

    All letters in hexadecimal (a-f) must be in lowercase.
    The hexadecimal string must not contain extra leading 0s. 
    If the number is zero, it is represented by a single zero character '0'; 
    otherwise, the first character in the hexadecimal string will not be the zero character.
    The given number is guaranteed to fit within the range of a 32-bit signed integer.
    You must not use any method provided by the library which converts/formats the number to hex directly.

Example 1:
Input:
26
Output:
"1a"

Example 2:
Input:
-1
Output:
"ffffffff"

 */
public class NO405_ConvertaNumbertoHexadecimal {
    public String toHex(int num) {
        if(num == 0){
            return "0";
        }
        char[] c = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String s = "";
        boolean isZero = true;
        while(num != 0){
            int factor = 1;
            int sum = 0;
          //每4位取一个字符
            for(int i = 0 ; i < 4 ; i++){
                if((num&1) == 1){
                    sum+=factor;
                }
                factor<<=1;
                num>>>=1;
            }
            s = c[sum]+s;
        }
        return s;
    }
    //改进，直接取4位，不是一位一位取
    public String toHex2(int num) {
        if(num == 0){
            return "0";
        }
        char[] c = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String s = "";
        boolean isZero = true;
        while(num != 0){
            //每4位取一个字符
            s = c[num&15]+s;
            num>>>=4;
        }
        return s;
    }
}
