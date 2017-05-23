package algorithm.leetcode.algorithm;
/*
 * medium
 * 179. Largest Number
 * Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
 */
import java.util.*;
public class NO179_LargestNumber {
	public static void main(String[] args) {
		int[] nums = new int[]{1,2};
		System.out.println(largestNumber(nums));
	}
	//方法1：
	//利用comparator来进行排序，两个字符串s1和s2，当s1+s2字典序大于s2+s1时，s1应该在s2前面
//	String s1 = "9";
//	String s2 = "31";
//
//	String case1 =  s1 + s2; // 931
//	String case2 = s2 + s1; // 319
	public static String largestNumber(int[] nums) {
		if(nums == null || nums.length == 0){
            return "";
        }
        String[] ss = new String[nums.length];
        for(int i = 0 ; i < nums.length ; i++){
            ss[i] = String.valueOf(nums[i]);
        }
        Comparator<String> cmp = new Comparator<String>(){
            @Override
            public int compare(String s1,String s2){
                String a = s1+s2;
                String b = s2+s1;
                return b.compareTo(a);
            }
        };
        Arrays.sort(ss,cmp);
        if(ss[0].charAt(0) == '0'){
            return "0";
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < nums.length ; i++){
            result.append(ss[i]);
        }
        return result.toString();
    }
}
