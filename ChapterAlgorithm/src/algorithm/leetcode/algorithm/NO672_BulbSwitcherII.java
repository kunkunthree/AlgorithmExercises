package algorithm.leetcode.algorithm;
/*
 * hard
 * 672. Bulb Switcher II 
 *  There is a room with n lights which are turned on initially and 4 buttons on the wall. 
 *  After performing exactly m unknown operations towards buttons, 
 *  you need to return how many different kinds of status of the n lights could be.

Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
    1.	Flip all the lights.
    2.	Flip lights with even numbers.
    3.	Flip lights with odd numbers.
    4.	Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...

Example 1:

Input: n = 1, m = 1.
Output: 2
Explanation: Status can be: [on], [off]

Example 2:

Input: n = 2, m = 1.
Output: 3
Explanation: Status can be: [on, off], [off, on], [off, off]

Example 3:

Input: n = 3, m = 1.
Output: 4
Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].

Note: n and m both fit in range [0, 1000]. 
 */
public class NO672_BulbSwitcherII {
	//方法1：
	//n == 1
	//Only 2 possibilities: 1 and 0.
	//n == 2
	//After one operation, it has only 4 possibilities: 11, 10 and 01.
	//After two and more operations, it has only 4 possibilities: 11, 10, 01 and 00.
	//n == 3
	//After one operation, it has only 4 possibilities: 111, 101, 010 and 011.
	//After two operations, it has 7 possibilities: 111,101,010,011,000,001 and 110.
	//After three and more operations, it has 8 possibilities, plus 100 on above case.
	//n >= 4
	//After one operation, it has only 4 possibilities: 1111, 1010, 0101 and 0110.
	//After two or more operations: it has 8 possibilities, 1111,1010,0101,0111,0000,0011, 1100 and 1001.
	public int flipLights(int n, int m) {
        if(n <= 0 || m < 0)return 0; //没有灯泡
        if(m == 0)return 1; //没有操作
        if(n == 1)return 2; //只有1个灯泡，只有开和关两个状态
        if(n == 2 && m == 1)return 3;   //只有2个灯泡和1个操作，那么就只有11 01 10三种状态
        if(n == 2)return 4; //m >= 2 只有2个灯泡，操作数大于等于2，那么状态有00 01 10 11四种状态
        if(m == 1)return 4; //n >= 3 只有1种操作，但是灯泡数大于等于3 那么有4种状态，因为此时操作2和操作4结果是不一样的
        if(m == 2)return 7; //n >= 3 只有2种操作，但是灯泡数大于等于3，有7种状态，111,101,010,011,000,001 and 110
        if(m >= 3)return 8; //n >= 3 m >= 3 ，8种状态
        return 8;
    }
}
