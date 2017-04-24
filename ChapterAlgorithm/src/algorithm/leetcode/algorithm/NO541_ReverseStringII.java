package algorithm.leetcode.algorithm;
/*
 * easy
 * 541. Reverse String II 
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters 
 * counting from the start of the string. If there are less than k characters left, reverse all of them. 
 * If there are less than 2k but greater than or equal to k characters, then reverse the first k characters 
 * and left the other as original.

Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Restrictions:
    The string consists of lower English letters only.
    Length of the given string and k will in the range [1, 10000]

 */
public class NO541_ReverseStringII {
	//按照最终结果的顺序访问字符
    public String reverseStr(String s, int k) {
        StringBuilder result = new StringBuilder();
        int tmp,start;
        for(int i = 0 ; i*k < s.length() ; i++){
            start = i*k;
            tmp = Math.min((i+1)*k-1,s.length()-1);
            if(i%2 == 0){
                while(tmp >= start){
                    result.append(s.charAt(tmp--));
                }
            }else{
                for(int j = start ; j <= tmp ; j++){
                    result.append(s.charAt(j));
                }
            }
        }
        return result.toString();
    }
    //只对需要交换的字符操作
    public String reverseStr2(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        while(i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap(arr, i, j);
            i += 2 * k;
        }
        return String.valueOf(arr);
    }
    private void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
}
