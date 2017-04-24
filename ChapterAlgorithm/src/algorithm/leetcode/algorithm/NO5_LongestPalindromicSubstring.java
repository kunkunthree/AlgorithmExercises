package algorithm.leetcode.algorithm;
/*
 * medium:
 * 5. Longest Palindromic Substring :
 * 		Given a string s, find the longest palindromic substring in s. 
 * You may assume that the maximum length of s is 1000.
Example:
		Input: "babad"
		Output: "bab"
Note: "aba" is also a valid answer.
Example:
		Input: "cbbd"
		Output: "bb"

 */
import java.util.*;
public class NO5_LongestPalindromicSubstring {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s;
		while(in.hasNext()){
			s = in.nextLine();
			System.out.println(longestPalindrome(s));
		}
	}
    public static String longestPalindrome(String s) {
        char[] array = s.toCharArray();
        int length = array.length;
        int start = 0, end = 0,maxLength = 1,tmpLength = 0,add = 1;
        boolean isEven = false;
        for(int i = 0 ; i < length ; i++){
            //上一个最长回文字符串长度为偶数时
            if(isEven == true){
            	tmpLength = 0;
            	//判断当前位置maxLength+1长度字符串两端是否相等，若相等则判断奇数长度字符串是否符合
            	if(i - maxLength/2 >= 0 &&  i + maxLength/2 < length 
            			&& array[i-maxLength/2]  == array[i+maxLength/2]){
            		tmpLength = getLength(array, i, length, false);
            	}
            	if(maxLength < tmpLength * 2+1){
                    maxLength = tmpLength * 2 + 1;
                    start = i-tmpLength;
                    end = i+tmpLength;
                    isEven = false;
                }
            	tmpLength = 0;
            	//判断当前位置maxLength+2长度字符串两端是否相等，若相等则判断偶数数长度字符串是否符合
            	if(i - maxLength/2-1 >= 0 &&  i + maxLength/2+1 < length 
            			&& array[i-maxLength/2]  == array[i+maxLength/2+1]){
            		tmpLength = getLength(array, i, length, true);
            	}
                if(maxLength < tmpLength * 2){
                    maxLength = tmpLength * 2;
                    start = i-tmpLength+1;
                    end = i+tmpLength;
                    isEven = true;
                }
            }
          //上一个最长回文字符串长度为奇数时
            if(isEven == false){
            	tmpLength = 0;
            	//判断当前位置maxLength+1长度字符串两端是否相等，若相等则判断奇数长度字符串是否符合
            	if(i - maxLength/2 - 1 >= 0 &&  i + maxLength/2 + 1 < length 
            			&& array[i-maxLength/2 - 1]  == array[i+maxLength/2 + 1]){
            		tmpLength = getLength(array, i, length, false);
            	}
            	if(maxLength < tmpLength * 2+1){
                    maxLength = tmpLength * 2 + 1;
                    start = i-tmpLength;
                    end = i+tmpLength;
                    isEven = false;
                }
            	tmpLength = 0;
            	//判断当前位置maxLength+2长度字符串两端是否相等，若相等则判断偶数数长度字符串是否符合
            	if(i - maxLength/2 >= 0 &&  i + maxLength/2+1 < length 
            			&& array[i-maxLength/2]  == array[i+maxLength/2+1]){
            		tmpLength = getLength(array, i, length, true);
            	}
            	if(maxLength < tmpLength * 2){
                    maxLength = tmpLength * 2;
                    start = i-tmpLength+1;
                    end = i+tmpLength;
                    isEven = true;
                }
            }
        }
        return s.substring(start,end+1);
    }
    public static int getLength(char[] array,int i,int length,boolean isEven){
    	int x;
    	if(isEven == true){
    		x = 0;
    	}else{
    		x = 1;
    	}
    	int tmpLength = 0;
    	 while(i-tmpLength-x >=0 && i + tmpLength +1 < length){
             if(array[i-tmpLength-x] == array[i+tmpLength+1]){
                 tmpLength++;
             }else{
                 break;
             }
         }
    	 return tmpLength;
    }
    public String longestPalindrome2(String s) {
        char[] array = s.toCharArray();
        int length = array.length;
        int start = 0, end = 0,maxLength = 0,tmpLength = 0;
        for(int i = 0 ; i < length ; i++){
            tmpLength = 0;
            if(i-1 >=0 && i + 1 < length){
                while(i-tmpLength-1 >=0 && i + tmpLength +1 < length){
                    if(array[i-tmpLength-1] == array[i+tmpLength+1]){
                        tmpLength++;
                    }else{
                        break;
                    }
                }
            }
            if(maxLength < tmpLength * 2+1){
                maxLength = tmpLength * 2 + 1;
                start = i-tmpLength;
                end = i+tmpLength;
            }
            tmpLength = 0;
            if(i >=0 && i + 1 < length){
                while(i-tmpLength >=0 && i + tmpLength +1 < length){
                    if(array[i-tmpLength] == array[i+tmpLength+1]){
                        tmpLength++;
                    }else{
                        break;
                    }
                }
            }
            if(maxLength < tmpLength * 2){
                maxLength = tmpLength * 2;
                start = i-tmpLength+1;
                end = i+tmpLength;
            }
        }
        return s.substring(start,end+1);
    }
    
    
    int palinStart = 0;
    int palinLen = 0;
    
    public String longestPalindrome3(String s) {
        
        if(s.length()<2 || s==null) return s;
        
        char[] chars = s.toCharArray();
        
        for(int i=0; i<chars.length-1; i++){
            checkThisPos(chars, i, i);
            checkThisPos(chars, i, i+1);
        }
        return s.substring(palinStart, palinStart+palinLen);
    }
    
    public void checkThisPos(char[] chars, int left, int right){
        while(left>=0 && right<chars.length && chars[left]==chars[right]){
            left--;
            right++;
        }
        if(right-left-1>palinLen){
            palinLen = right-left-1;
            palinStart = left+1;
            // System.out.println("palinStart: "+ palinStart+" palinLen: "+palinLen);
        }
        
    }
}
