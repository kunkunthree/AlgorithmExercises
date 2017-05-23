package algorithm.leetcode.algorithm;
/*
 * medium
 * 526. Beautiful Arrangement
 *  Suppose you have N integers from 1 to N. 
 *  We define a beautiful arrangement as an array that is constructed by these N numbers successfully 
 *  if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:

    1.	The number at the ith position is divisible by i.
    2.	i is divisible by the number at the ith position.

Now given N, how many beautiful arrangements can you construct?

Example 1:

Input: 2
Output: 2
Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.

Note:
   1.	 N is a positive integer and will not exceed 15.

 */
import java.util.*;
public class NO526_BeautifulArrangement {
	//方法1：
	//递归回溯，全排列
	private int count = 0;
    public int countArrangement(int N) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1 ; i <= N ; i++){
            list.add(i);
        }
        helper(list,1,N);
        return count;
    }
    private void helper(List<Integer> list,int position,int N){
        if(position == N+1){
            count++;
        }
        int tmp;
        int length = N - position + 1;
        for(int i = 0 ; i < length ; i++){
            tmp = list.get(i);
            list.remove(i);
            if(tmp%position == 0 || position%tmp == 0){
                helper(list,position+1,N);
            }
            list.add(i,tmp);
        }
    }
}
