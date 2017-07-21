package algorithm.leetcode.algorithm;
/*
 * medium
 * 17. Letter Combinations of a Phone Number
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * NO17_LetterCombinationsofaPhoneNumber.png

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
Although the above answer is in lexicographical order, your answer could be in any order you want. 

similar problems:
22. Generate Parentheses 
39. Combination Sum 
401. Binary Watch 
 */
import java.util.*;
public class NO17_LetterCombinationsofaPhoneNumber {
	public static void main(String[] args) {
		String digits = "2";
		System.out.println(letterCombinations(digits));
	}
	//方法1：利用递归
    public static  List<String> letterCombinations(String digits) {
        String[] array = new String[]{" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> list = new ArrayList<String>();
        if(digits == null || digits.length() == 0){
            return list;
        }
        letterCombinationsHelper(digits,"",0,array,list);
        return list;
    }
    public static void letterCombinationsHelper(String digits,String s,int n,String[] array,List<String> list){
        if(n == digits.length()){
            list.add(s);
            return;
        }
        int num = 1;
        if(n >= 0 && n < digits.length()){
            num = digits.charAt(n) - '0';
        }else{
            return;
        }
        for(int i = 0 ; i < array[num].length() ; i++){
            letterCombinationsHelper(digits,s+array[num].charAt(i),n+1,array,list);
        }
    }
    //方法2：利用队列
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }
}
