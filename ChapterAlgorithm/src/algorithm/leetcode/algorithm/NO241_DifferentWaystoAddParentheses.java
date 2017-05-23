package algorithm.leetcode.algorithm;
/*
 * medium
 * 241. Different Ways to Add Parentheses
 * Given a string of numbers and operators,
 *  return all possible results from computing all the different possible ways to group numbers and operators. 
 *  The valid operators are +, - and *.

Example 1

Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2

Output: [0, 2]

Example 2

Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10

Output: [-34, -14, -10, -10, 10]
 */
import java.util.*;
public class NO241_DifferentWaystoAddParentheses {
	//方法1：
	//递归实现，通过运算符将两边分开，计算左边的结果集，计算右边的结果集，然后左右两边结果集进行+,-,*运算
	public List<Integer> diffWaysToCompute(String input) {
        int start = 0;
        int end = input.length()-1;
        return diffWaysToComputeHelper(input,start,end);
    }
    public List<Integer> diffWaysToComputeHelper(String input,int start,int end){
        List<Integer> list = new ArrayList<>();
        if(start > end){
            return list;
        }
        int num = 0;
        String s = input.substring(start,end+1);
        if(!s.contains("+") && !s.contains("-") && !s.contains("*")){
            for(int i = start ; i <= end ; i++){
                num = num * 10 + input.charAt(i) - '0';
            }
            list.add(num);
            return list;
        }
        int tmpStart;
        for(int i = start ; i <= end ; i++){
            if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
                for(int left : diffWaysToComputeHelper(input,start,i-1)){
                    for(int right : diffWaysToComputeHelper(input,i+1,end)){
                        if(input.charAt(i) == '+'){
                            list.add(left+right);
                        }
                        if(input.charAt(i) == '-'){
                            list.add(left-right);
                        }
                        if(input.charAt(i) == '*'){
                            list.add(left*right);
                        }
                    }
                }
            }
        }
        return list;
    }
    //方法2：
    //方法1的简化写法
    public List<Integer> diffWaysToCompute2(String input) {
        List<Integer> list = new ArrayList<>();
        int start = 0;
        int end = input.length();
        int num = 0;
        int tmpStart;
        for(int i = start ; i < end ; i++){
            if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
                for(int left : diffWaysToCompute2(input.substring(start,i))){
                    for(int right : diffWaysToCompute2(input.substring(i+1,end))){
                        if(input.charAt(i) == '+'){
                            list.add(left+right);
                        }
                        if(input.charAt(i) == '-'){
                            list.add(left-right);
                        }
                        if(input.charAt(i) == '*'){
                            list.add(left*right);
                        }
                    }
                }
            }
        }
        if(list.size() == 0){
            for(int i = start ; i < end ; i++){
                num = num * 10 + input.charAt(i) - '0';
            }
            list.add(num);
        }
        return list;
    }
}
