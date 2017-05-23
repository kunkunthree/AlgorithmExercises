package algorithm.leetcode.algorithm;
/*
 * medium
 * 473. Matchsticks to Square
 * Remember the story of Little Match Girl? 
 * By now, you know exactly what matchsticks the little match girl has, 
 * please find out a way you can make one square by using up all those matchsticks. 
 * You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. 
Your output will either be true or false, to represent whether you could make one square 
using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.

Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.

Note:

    1.The length sum of the given matchsticks is in the range of 0 to 10^9.
    2.The length of the given matchstick array will not exceed 15.

 */
import java.util.*;
public class NO473_MatchstickstoSquare {
	//方法1：
	//DFS，得到总边长，判断是否为4的倍数，如果不是则返回false，如果是，则得到正方形的边长
	//通过DFS判断是否能得到正方形每条边的边长
	public boolean makesquare(int[] nums) {
        if(nums == null || nums.length < 4){
            return false;
        }
        Arrays.sort(nums);
        int length = nums.length,sum = 0;
        for(int num : nums){
            sum+=num;
        }
        if(sum % 4 != 0){
            return false;
        }
        int sideLength = sum/4;
        int[] len = new int[4];
        Arrays.fill(len,sideLength);
        return makesquare(nums,length-1,len);
    }
    private boolean makesquare(int[] nums,int index,int[] len){
        if(index < 0){
            return len[0] == 0 && len[1] == 0 && len[2] == 0 && len[3] == 0;
        }
        for(int i = 0 ; i < len.length ; i++){
            len[i]-=nums[index];
            if(len[i] >= 0 && makesquare(nums,index-1,len)){
                return true;
            }
            len[i]+=nums[index];
        }
        return false;
    }
    //方法2:
    //dp，动态规划
    //遍历所有能选择的路径，用一个set存储中间得到的能组成正方形的边的路径，
    //若当前路径能组成正方形边的路径，则从set中找到与当前路径互斥路径，
    //则这两条路径组成的是能得到2条正方型边的路径，
    //再判断与这两条路径互斥的另外两条路径是否能构成正方形的两条边，如果可以，则返回true
    //如果都不可以，则把当前路径加入set
    public boolean makesquare2(int[] nums) {
        if(nums == null || nums.length < 4){
            return false;
        }
        Arrays.sort(nums);
        int length = nums.length,sum = 0;
        for(int num : nums){
            sum+=num;
        }
        if(sum % 4 != 0){
            return false;
        }
        int sideLength = sum/4;
        int all = (1<<length)-1;
        boolean[] dp = new boolean[1<<length];
        Set<Integer> usedMaskSet = new HashSet<>();
        for(int mask = 0 ; mask < all ; mask++){
            int subsum = 0;
            for(int i = 0 ; i < 32 ; i++){
                if(((mask>>i) & 1) == 1){
                    subsum+=nums[i];
                }
            }
            if(subsum == sideLength){
                for(int usedMask : usedMaskSet){
                    if((usedMask & mask) == 0){
                        usedMask|=mask;
                        dp[usedMask] = true;
                        if(dp[usedMask^all] == true){
                            return true;
                        }
                    }
                }
                usedMaskSet.add(mask);
            }
        }
        return false;
    }
}
