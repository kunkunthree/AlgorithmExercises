package algorithm.leetcode.algorithm;
/*
 * medium
 * 556. Next Greater Element III 
 *  Given a positive 32-bit integer n, you need to find the smallest 32-bit integer 
 *  which has exactly the same digits existing in the integer n and is greater in value than n. 
 *  If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21

Example 2:
Input: 21
Output: -1

similar problems：
496. Next Greater Element I 
503. Next Greater Element II 
 */
import java.util.*;
public class NO556_NextGreaterElementIII {
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		String s;
		
	}
	//方法1：
	//从右到左找到第一个减少的数字num[i]如果没有，说明该数已经是最大的了，则返回1
	//如果有，则设该位下标为i，从最右往左找第一个比num[i]大的数num[j]下标为j，将num[j]与num[i]交换
	//那么此时i后面的数为一个递减数列，将后面的数逆转为一个递增序列，则此时是第一个比n大的数
	//如果这个数比Integer.MAX_VALUE大，则返回-1;
	public int nextGreaterElement(int n) {
        String s = n + "";
        int i = s.length()-2,length = s.length();
        while(i >= 0 && s.charAt(i) >= s.charAt(i+1)){
            i--;
        }
        if(i < 0){
            return -1;
        }
        int j = length-1;
        while(j > i && s.charAt(i) >= s.charAt(j)){
            j--;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0,i)+s.charAt(j));
        for(int k = length-1 ; k > i ; k--){
            if(k!=j){
                sb.append(s.charAt(k));
            }else{
                sb.append(s.charAt(i));
            }
        }
        long result = Long.valueOf(sb.toString());
        return result > Integer.MAX_VALUE ? -1 : (int)result;
    }
	//方法2：
	//原理和方法1一样，只是写法更加易懂
	public int nextGreaterElement2(int n) {
        char[] number = (n + "").toCharArray();
        
        int i, j;
        // I) Start from the right most digit and 
        // find the first digit that is
        // smaller than the digit next to it.
        for (i = number.length-1; i > 0; i--)
            if (number[i-1] < number[i])
               break;

        // If no such digit is found, its the edge case 1.
        if (i == 0)
            return -1;
            
         // II) Find the smallest digit on right side of (i-1)'th 
         // digit that is greater than number[i-1]
        int x = number[i-1], smallest = i;
        for (j = i+1; j < number.length; j++)
            if (number[j] > x && number[j] <= number[smallest])
                smallest = j;
        
        // III) Swap the above found smallest digit with 
        // number[i-1]
        char temp = number[i-1];
        number[i-1] = number[smallest];
        number[smallest] = temp;
        
        // IV) Sort the digits after (i-1) in ascending order
        Arrays.sort(number, i, number.length);
        
        long val = Long.parseLong(new String(number));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }
}
