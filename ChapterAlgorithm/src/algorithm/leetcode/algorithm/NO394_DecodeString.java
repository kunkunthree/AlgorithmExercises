package algorithm.leetcode.algorithm;
/*
 * medium
 * 394. Decode String
 *  Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being 
repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only 
for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

 */
import java.util.*;
public class NO394_DecodeString {
	//方法1：
	//迭代，利用stack
	public String decodeString(String s) {
        Stack<Integer> stackInt = new Stack<>();    //存储中间重复字符串次数
        Stack<String> stackStr = new Stack<>();     //存储中间所用到的字符串
        int num = 0,n = s.length();
        String str = "",tmp;
        char c;
        stackStr.push(str);
        for(int i = 0 ; i < n ; i++){
            c = s.charAt(i);
            if(isDigit(c)){ //数字
                num = num *10 + c - '0';
            }else if(isCharacter(c)){   //字母
                stackStr.push(stackStr.pop()+c);
            }else if(c == '['){     //'['，标识有重复字符串
                stackInt.push(num);
                num = 0;
                stackStr.push("");
            }else if(c == ']'){     //']'，标志重复字符串的结束
                num = stackInt.pop();   //得到重复次数
                str = stackStr.pop();   //得到重复字符串
                tmp = stackStr.pop();   //得到当前需要接重复字符串的字符串
                for(int j = 0 ; j < num ; j++){
                    tmp+=str;
                }
                stackStr.push(tmp);
                num = 0;
            }else{
                throw new RuntimeException();
            }
        }
        return stackStr.pop();
    }
    private boolean isDigit(char c){
        return c <= '9' && c >= '0';
    }
    private boolean isCharacter(char c){
        return c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A';
    }
}
