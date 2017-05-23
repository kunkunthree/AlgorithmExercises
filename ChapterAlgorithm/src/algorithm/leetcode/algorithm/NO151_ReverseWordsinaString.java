package algorithm.leetcode.algorithm;
/*
 * medium
 * 151. Reverse Words in a String
 *  Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space. 
 */
public class NO151_ReverseWordsinaString {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
	}
	//方法1：
	//顺序遍历得到分割的字符串，逆序组成字符串
	public String reverseWords(String s) {
        String result = "";
        int start = -1,end = -1;
        char c;
        for(int i = 0 ; i < s.length() ; i++){
            c = s.charAt(i);
            if(c != ' '){
                if(start < 0){
                    start = i;
                    end = i+1;
                }else{
                    end++;
                }
            }else{
                if(start >= 0){
                    if(result.length() == 0){
                        result = s.substring(start,end);
                    }else{
                        result = s.substring(start,end) + " " + result;
                    }
                    start = -1;
                    end = -1;
                }
            }
        }
        if(start >= 0){
            if(result.length() == 0){
                result = s.substring(start,end);
            }else{
                result = s.substring(start,end) + " " + result;
            }
        }
        return result;
    }
}
