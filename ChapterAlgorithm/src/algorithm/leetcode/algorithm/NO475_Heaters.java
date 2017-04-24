package algorithm.leetcode.algorithm;
/*
 * easy
 * 475. Heaters 
 * 		Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius 
 * to warm all the houses.
 * 		Now, you are given positions of houses and heaters on a horizontal line, 
 * find out minimum radius of heaters so that all houses could be covered by those heaters.
 * 		So, your input will be the positions of houses and heaters seperately, 
 * and your expected output will be the minimum radius standard of heaters.

Note:
    Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
    Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
    As long as a house is in the heaters' warm radius range, it can be warmed.
    All the heaters follow your radius standard and the warm radius will the same.

Example 1:

Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, 
then all the houses can be warmed.

Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, 
then all the houses can be warmed.

 */
import java.util.*;
public class NO475_Heaters {
	public static void main(String[] args) {
		int[] houses = new int[]{1,2,3,4,5,6,7,8,9};
		int[] heaters = new int[]{1,4,6};
		System.out.println(findRadius(houses, heaters));
	}
	//先对houses和heaters排序，升序
	//在寻找每个house最近的heater，
	//即当j+1<heaters.length时寻找第一个符合houses[i] < (heaters[j]+heaters[j+1])的j，则houses[i]离heaters[j]最近
	//当j+1 = heaters.length时，此时离houses[i]最近的都是heaters[j]
	//最后找出这些最近距离的最大值，即为heater所需要的最小的半径
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i = 0, j = 0,max = 0;
        while(i < houses.length){
            while(j+1 < heaters.length && houses[i] * 2 >= heaters[j] + heaters[j+1]){
                j++;
            }
            max = Math.max(max,Math.abs(heaters[j] - houses[i]));
            i++;
        }
        return max;
    }
}
