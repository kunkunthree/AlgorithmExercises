package algorithm.leetcode.algorithm;
/*
 * easy
 * 557. Reverse Words in a String III 
 * Given a string, you need to reverse the order of characters in each word within a sentence 
 * while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Note: In the string, each word is separated by single space and there will not be any extra space in the string. 

similar problems:
541. Reverse String II 
 */
public class NO557_ReverseWordsinaStringIII {
    public String reverseWords(String s) {
        int i = 0,length = s.length(),start,end;
        char tmp;
        char[] result = s.toCharArray();
        // StringBuilder result = new StringBuilder();
        while(i < length){
            while(i < length && result[i] == ' '){
                i++;
            }
            start = i;
            while(i < length && result[i] != ' '){
                i++;
            }
            end = i-1;
            while(start < end){
                tmp = result[start];
                result[start] = result[end];
                result[end] = tmp;
                start++;
                end--;
            }
        }
        return String.valueOf(result);
    }
}
