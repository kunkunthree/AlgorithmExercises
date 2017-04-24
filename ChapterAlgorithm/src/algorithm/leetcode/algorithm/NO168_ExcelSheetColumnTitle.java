package algorithm.leetcode.algorithm;
/*
 * easy
 * 168. Excel Sheet Column Title
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 */
public class NO168_ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        if(n <= 0){
            return "";
        }
        int x = n % 26;
        int y = n/26;
        if(x == 0){
            return convertToTitle(y-1)+"Z";
        }else{
            return convertToTitle(y)+(char)((int)'A'+x-1);
        }
    }
    //更简单的写法
    public String convertToTitle2(int n) {
    	return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));
    }
}
