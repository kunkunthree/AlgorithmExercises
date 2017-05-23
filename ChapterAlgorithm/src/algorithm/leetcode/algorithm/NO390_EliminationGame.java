package algorithm.leetcode.algorithm;
/*
 * medium
 * 390. Elimination Game
 *  There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number 
 *  and every other number afterward until you reach the end of the list.

Repeat the previous step again, but this time from right to left, remove the right most number 
and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left, until a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6

 */
public class NO390_EliminationGame {
	//方法1：
	//迭代法，O(logN)time,O(1)space
	public int lastRemaining(int n) {
        int remain = n; //序列元素剩余个数
        int step = 1;
        boolean leftToRight = true;
        int head = 1;   //指向每次提出后的序列的第一个数
        while(remain > 1){
            //当从左往右，或从右向左且元素个数为奇数时，第一个元素都会发生改变，移向下一个元素
            if(leftToRight == true || remain%2 == 1){
                head += step;   
            }
            step<<=1;       //跨度加倍
            remain>>=1;     //序列元素减半
            leftToRight = !leftToRight; //交换方向
        }
        return head;
    }
	//方法2：
	//递归，O(logN)time,O(1)space
	public int lastRemaining2(int n) {
        return leftToRight(n);
    }
	//从左往右
    public int leftToRight(int n){
        if(n <= 2){
            return n;
        }
        //当从左往右时，把剩余的序列映射到1～n/2上，
        //如果要映射回来，则只要直接乘以2即可
        return 2 * rightToLeft(n/2);
    }
  //从右往左
    public int rightToLeft(int n){
        if(n <= 2){
            return 1;
        }
        //当从右往左且序列个数为奇数时，把剩余的序列映射到1～n/2上，
        //如果要映射回来，则只要直接乘以2即可，因为剩余的都是偶数
        if(n%2 == 1){
            return 2 * leftToRight(n/2);
        }
        //当从右往左且序列个数为偶数时，把剩余的序列映射到1～n/2上，
        //如果要映射回来，则需要乘以2再减去1，因为剩余的全是奇数，映射回来乘以2的话只是偶数，需要再减1
        return 2 * leftToRight(n/2)-1;
    }
}
