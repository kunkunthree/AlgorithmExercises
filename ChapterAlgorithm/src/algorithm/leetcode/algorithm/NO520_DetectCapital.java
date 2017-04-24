package algorithm.leetcode.algorithm;
/*
 * easy
 * 520. Detect Capital 
 *  Given a word, you need to judge whether the usage of capitals in it is right or not.
We define the usage of capitals in a word to be right when one of the following cases holds:

    All letters in this word are capitals, like "USA".
    All letters in this word are not capitals, like "leetcode".
    Only the first letter in this word is capital if it has more than one letter, like "Google".

Otherwise, we define that this word doesn't use capitals in a right way.

Example 1:
Input: "USA"
Output: True

Example 2:
Input: "FlaG"
Output: False

Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters. 
 */
public class NO520_DetectCapital {
	public static void main(String[] args) {
		System.out.println((int)'a');
	}
    public boolean detectCapitalUse(String word) {
        if(word == null || word.length() == 0){
            return false;
        }
        int length = word.length();
        if(word.charAt(0) >= 'a' && word.charAt(0) <= 'z'){
            for(int i = 1 ; i < length ; i++){
                if(word.charAt(i) < 'a' || word.charAt(i) > 'z'){
                    return false;
                }
            }
        }else if(word.charAt(0) >= 'A' && word.charAt(0) <= 'Z'){
            for(int i = 1 ; i < length-1 ; i++){
                if(word.charAt(i) - 'a' >= 0 && word.charAt(i+1) - 'a' >= 0){
                    continue;
                }else if(word.charAt(i) - 'Z' <= 0 && word.charAt(i+1) - 'Z' <= 0){
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    //利用正则表达式
    public boolean detectCapitalUse2(String word) {
        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }
    //计数法，统计大写字母的数量，如果数量为字符串长度或者为0，则为true，
    //如果数量为1且首字母为大写字母，则为true
    //其他情况为false
    public boolean detectCapitalUse3(String word) {
        int cnt = 0;
        for(char c: word.toCharArray()) if('Z' - c >= 0) cnt++;
        return ((cnt==0 || cnt==word.length()) || (cnt==1 && 'Z' - word.charAt(0)>=0));
    }
}
