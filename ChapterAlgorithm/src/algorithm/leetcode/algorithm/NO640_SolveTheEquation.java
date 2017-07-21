package algorithm.leetcode.algorithm;
/*
 * medium
 * 640. Solve the Equation 
 *  Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:

Input: "x+5-3+x=6+x-2"
Output: "x=2"

Example 2:

Input: "x=x"
Output: "Infinite solutions"

Example 3:

Input: "2x=x"
Output: "x=0"

Example 4:

Input: "2x+3x-6x=x+2"
Output: "x=-1"

Example 5:

Input: "x=x+2"
Output: "No solution"

 */
public class NO640_SolveTheEquation {
	//方法1：
	//直接法，求两边的x系数和与常数和
	//然后直接比较：
	//1.两边x系数和与常数和都相等："Infinite solutions"
	//2.两边x系数和相等，常数和不相等："No solution"
	//3.求x：
	//		3.1.x不是整数："No solution"
	//		3.2.x是整数：返回"x="+x
	public String solveEquation(String equation) {
        String[] expression = equation.split("=");
        int[] left = getResult(expression[0]);
        int[] right = getResult(expression[1]);
        if(left[0] == right[0] && left[1] == right[1]){
            return "Infinite solutions";
        }else if(left[1] == right[1]){
            return "No solution";
        }
        int constant = right[0]-left[0];
        int xFactor = left[1] - right[1];
        int x = constant/xFactor;
        return x*xFactor == constant ? "x="+x : "No solution";
    }
    private int[] getResult(String expression){
        int[] result = new int[]{0,0}; //result[0]表示常数，result[1]表示x的系数
        int num = 0;
        int add = 1;
        int index = 0;
        for(int i = 0 ; i <= expression.length() ; i++){
            if(i == expression.length() || expression.charAt(i) == '+' || expression.charAt(i) == '-'){
                result[index]+=add*num;
                if(i < expression.length()){
                    if(expression.charAt(i) == '+'){
                        add = 1;
                    }else if(expression.charAt(i) == '-'){
                        add = -1;
                    }
                }
                num = 0;
                index = 0;
            }else if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9'){
                num = num*10 + expression.charAt(i) - '0';
            }else if(expression.charAt(i) == 'x'){
                index = 1;
                if(i == 0 || expression.charAt(i-1) == '+' || expression.charAt(i-1) == '-'){
                    num = 1;
                }
            }
        }
        return result;
    }
}
