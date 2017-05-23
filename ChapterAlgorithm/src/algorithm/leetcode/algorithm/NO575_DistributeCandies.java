package algorithm.leetcode.algorithm;
/*
 * easy
 * 575. Distribute Candies 
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies. 
 * Each number means one candy of the corresponding kind. 
 * You need to distribute these candies equally in number to brother and sister. 
 * Return the maximum number of kinds of candies the sister could gain.

Example 1:

Input: candies = [1,1,2,2,3,3]
Output: 3
Explanation:
There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too. 
The sister has three different kinds of candies. 

Example 2:

Input: candies = [1,1,2,3]
Output: 2
Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1]. 
The sister has two different kinds of candies, the brother has only one kind of candies. 

Note:
    1.		The length of the given array is in range [2, 10,000], and will be even.
    2.		The number in given array is in range [-100,000, 100,000].
 */
import java.util.*;
public class NO575_DistributeCandies {
	//方法1：
	//因为糖果要数量平分，所以女孩得到糖果的数量为sum/2，女孩得到糖果的种类最多也为sum/2，
	//所以只要统计所有糖果共有的种类数count，
	//当count >= sum/2时，女孩得到糖果的种类的最大数目为sum/2
	//当count < sum/2时，女孩得到糖果的种类的最大数目为count
	public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        int sum = candies.length,count = 0;
        for(int i = 0 ; i < sum ; i++){
            if(set.add(candies[i])){
                count++;
            }
        }
        return count > sum/2 ? sum/2 : count;
    }
	//方法2：
	//方法1的简化
	public int distributeCandies2(int[] candies) {
        Set<Integer> set = new HashSet<>();
        int sum = candies.length;
        for(int i = 0 ; i < sum ; i++){
            set.add(candies[i]);
        }
        return set.size() > sum/2 ? sum/2 : set.size();
    }
}
