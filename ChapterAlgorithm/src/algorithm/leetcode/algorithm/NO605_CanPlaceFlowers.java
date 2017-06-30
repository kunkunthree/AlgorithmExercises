package algorithm.leetcode.algorithm;
/*
 * easy
 * 605. Can Place Flowers 
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. 
 * However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), 
and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True

Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False

Note:
    1.		The input array won't violate no-adjacent-flowers rule.
    2.		The input array size is in the range of [1, 20000].
    3.		n is a non-negative integer which won't exceed the input array size.

 */
public class NO605_CanPlaceFlowers {
	//方法1：
	//求连续0的个数，然后减去这些连续0能插入的1的个数
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int num = 1;
        for(int i = 0 ; i < flowerbed.length ; i++){
            if(flowerbed[i] == 1){
                n-=Math.max(0,(num-1)/2);
                if(n <= 0){
                    return true;
                }
                num = 0;
            }else if(flowerbed[i] == 0){
                num++;
            }
        }
        num++;
        n-=Math.max(0,(num-1)/2);
        return n <= 0;
    }
}
