package algorithm.leetcode.algorithm;
/*
 * hard
 * 466. Count The Repetitions 
 * Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".

On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters 
from s2 such that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, 
but it can not be obtained from “acbbe”.

You are given two non-empty strings s1 and s2 (each at most 100 characters long)
 and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. 
 Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. 
 Find the maximum integer M such that [S2,M] can be obtained from S1.

Example:

Input:
s1="acb", n1=4
s2="ab", n2=2

Return:
2

 */
import java.util.*;
public class NO466_CountTheRepetitions {
	public static void main(String[] args) {
	}
	//方法1：
	//首先，我们需要计算当s2从s1中获得后，后面剩下的字符串remain
	//(s1,s2) -> (sRemain,matchCount)
	//(abcd, ab) -> (cd, 1)
	//(ababa, ab) -> (a, 2)
	//(a, aaaa) -> (a, 0)
	//(aabaabaab, aba) -> (ab, 2) , aabaaba 匹配 aba 2次.
	//然后，观察剩余字符串+s1与s2匹配的情况，用[]把上一次剩余的字符串标识出来
	//(abcd, ab): abcd -> [cd]abcd -> [cd]abcd -> [cd]abcd -> ...
	//(ababa, ab): ababa -> [a]ababa -> [a]ababa -> [a]ababa -> ...
	//(a, aaaa): a -> [a]a -> [aa]a -> [aaa]a -> a -> [a]a -> [aa]a -> ...
	//(aabaabaab, aba): aabaabaab -> [ab]aabaabaab -> [ab]aabaabaab -> ...
	//显然，在序列中会形成一个循环，设循环的长度为length，在循环开始之前的长度为k
	//(abcd, ab): loop=1, k=1
	//(a, aaaa): loop=4, k=0
	//(aabaabaab, aba): loop=1, k=1
	//所以我们只需要计算循环前的长度，和循环的长度，以及循环后未满足一个循环的长度
	//最终结果就是这三个长度的总和total除以n2，就是我们所求的最大M
	public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if(!ableToObtains(s1,s2)){
            return 0;
        }
        int count = 0,k = -1;
        ArrayList<Integer> countList = new ArrayList<>();
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("");
        countList.add(0);
        String s = s1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i <= n1 ; i++){
            builder.delete(0,builder.length());
            count+=getRemain(s,s2,builder);
            String remain=builder.toString();
            k = stringList.indexOf(remain);
            if(k != -1){
                break;
            }
            countList.add(count);
            stringList.add(remain);
            s = remain + s1;
        }
        if(k == -1){
            return count/n2;
        }
        int countOfLoop = count - countList.get(k);
        int lengthOfLoop = stringList.size() - k;
        count = countList.get(k);
        n1-=k;
        count+=countOfLoop*(n1/lengthOfLoop);
        n1%=lengthOfLoop;
        count+=countList.get(k+n1)-countList.get(k);
        return count/n2;
    }
    private boolean ableToObtains(String s1,String s2){
        boolean[] count = new boolean[26];
        for(char c : s1.toCharArray()){
            count[c-'a'] = true;
        }
        for(char c : s2.toCharArray()){
            if(count[c-'a'] == false){
                return false;
            }
        }
        return true;
    }
    private int getRemain(String s1,String s2,StringBuilder builder){
        int count = 0;
        int lastIndex = -1;
        for(int i = 0,j = 0; i < s1.length() ; i++){
            if(s1.charAt(i) == s2.charAt(j)){
                j++;
                if(j == s2.length()){
                    lastIndex = i;
                    j = 0;
                    count++;
                }
            }
        }
        builder.append(s1.substring(lastIndex+1));
        return count;
    }
}
