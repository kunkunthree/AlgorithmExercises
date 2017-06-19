package algorithm.leetcode.algorithm;
/*
 * hard
 * 135. Candy 
 *  There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give? 
 */
import java.util.*;
public class NO135_Candy {
	//方法1：
	//三次遍历：
	//先初始化一个相同长度的数组nums，初始值均为1
	//第一次：从左往右遍历，1～n-1,如果ratings[i] > ratings[i-1]，则nums[i] = nums[i-1]+1;
	//第二次：从右往左遍历，n-1~1,如果ratings[i-1] > ratings[i]，则nums[i-1] = Math.max(nums[i]+1,nums[i-1]);
	//第三次：从左往右，求nums数组的和
	public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        int n = ratings.length,sum = 0;
        int[] nums = new int[n];
        Arrays.fill(nums,1);
        for(int i = 1 ; i < n ; i++){
            if(ratings[i] > ratings[i-1]){
                nums[i] = nums[i-1]+1;
            }
        }
        for(int i = n-1 ; i > 0 ; i--){
            if(ratings[i-1] > ratings[i]){
                nums[i-1] = Math.max(nums[i]+1,nums[i-1]);
            }
        }
        for(int i = 0 ; i < n ; i++){
            sum+=nums[i];
        }
        return sum;
    }
	//方法2：
	//O(n)time,O(1)space
	//记录每一段递减序列的首项pre和除去首项的长度countDown，
	//通过求和得到除首项外递减序列应赋予的值的和(countDown+...+3+2+1)
	//即从尾项开始直到首项前一项，从1到countDown依次赋值
	//如果首项位置赋予的值小于等于countDown，则应补偿首项的值，即要补回countDown-pre+1
	//如果某项与前面一项相等，则该项位置应赋予1
	//如果某项大于前面一项，则该项应赋予前一项所赋予的值pre+1
	public int candy2(int[] ratings) {
        if(ratings == null || ratings.length == 0){
            return 0;
        }
        int n = ratings.length,sum = 1,pre = 1,countDown = 0;
        for(int i = 1 ; i <= n ; i++){
            if(i == n || ratings[i] >= ratings[i-1]){
                if(countDown > 0){
                    sum+=countDown*(countDown+1)/2;
                    if(countDown >= pre){
                        sum+=countDown-pre+1;
                    }
                    countDown = 0;
                    pre = 1;
                }
                if(i < n){
                    pre = ratings[i] == ratings[i-1] ? 1 : pre+1;
                    sum+=pre;
                }
            }else{
                countDown++;
            }
        }
        return sum;
    }
}
