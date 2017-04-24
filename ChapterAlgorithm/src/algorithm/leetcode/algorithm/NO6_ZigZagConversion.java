package algorithm.leetcode.algorithm;
/*
 * medium
 * 6. ZigZag Conversion
 *  The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 *   (you may want to display this pattern in a fixed font for better legibility)

P   	A   	H   	N
A P	L 	S 	I 	I 	G
Y   	I   	R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);

convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR". 
 */
public class NO6_ZigZagConversion {
	//计算每行的下标,假设行数为[0,numRows-1]
	//第0行和最后numRows-1行的相邻两个数的下标间隔相等，间隔为2 × numRows -2
	//中间行的相邻两个数的下标间隔不相等，但是每隔两个数的下标间隔为2 × numRows -2
	//第i行（1<i<numRows）相邻两个数间隔为2 × (numRows-i) -2或者 2 * i 
	//特殊情况，numRows = 1时，间隔为恒为1
    public String convert(String s, int numRows) {
        StringBuilder result = new StringBuilder();
        int length = s.length();
        int k = numRows == 1 ? 1 : 2 * numRows - 2;
        for(int i = 0 ; i < numRows ; i++){
            int l = 2 * (numRows - i) - 2;
            for(int j = i; j < length ; j+=k){
                result.append(s.charAt(j));
                if(i > 0 && i < numRows-1 && j+l < length){
                    result.append(s.charAt(j+l));
                }
            }
        }
        return result.toString();
    }
    //方法2：控制行数下标
    public String convert2(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
        
        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }
}
