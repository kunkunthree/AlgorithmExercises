package algorithm.leetcode.algorithm;
/*
 * medium
 * 137. Single Number II
 *  Given an array of integers, every element appears three times except for one, which appears exactly once. 
 *  Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? 

这类问题（其他所有元素都出现K次，而只有一个元素出现M次，求这个出现M次的元素的值）的解决思路：
this kind of question the key idea is design a counter that record state. the problem can be every one occurs K times 
except one occurs M times. for this question, K =3 ,M = 1(or 2) .
so to represent 3 state, we need two bit. let say it is a and b, and c is the incoming bit.
then we can design a table to implement the state move.

current   incoming  next
a b            c    				a b
0 0            0    				0 0
0 1            0    				0 1
1 0            0    				1 0
0 0            1    				0 1
0 1            1   				 	1 0
1 0            1    				0 0

like circuit design, we can find out what the next state will be with the incoming bit.( we only need find the ones)
then we have for a to be 1, we have

    current   incoming  next
    a b            c    				a b
    1 0            0    				1 0
    0 1            1    				1 0

and this is can be represented by

a=a&~b&~c + ~a&b&c

and b can do the same we , and we find that
    current   incoming  next
    a b            c    				a b
	0 1            0    				0 1
   	0 0            1    				0 1
   	
b= ~a&b&~c+~a&~b&c

and this is the final formula of a and b and just one of the result set, because for different state move table definition, we can generate different formulas, and this one is may not the most optimised. as you may see other's answer that have a much simple formula, and that formula also corresponding to specific state move table. (if you like ,you can reverse their formula to a state move table, just using the same way but reversely)

for this questions we need to find the except one
as the question don't say if the one appears one time or two time ,
so for ab both

01 10 => 1
00 => 0

we should return a|b;
this is the key idea , we can design any based counter and find the occurs any times except one .
here is my code. with comment.
 */
public class NO137_SingleNumberII {
	//方法1：
	//根据位的状态转移，通过列表计算每一位的表达式，最终把每一位进行计算获得最后位的状态
	public int singleNumber(int[] nums) {
        int a = 0, b = 0,tmpA;
        for(int i = 0 ; i < nums.length ; i++){
            tmpA = (a&~b&~nums[i]) | (~a&b&nums[i]);
            b = (~a&b&~nums[i]) | (~a&~b&nums[i]);
            a = tmpA;
        }
        return a|b;
    }
}
