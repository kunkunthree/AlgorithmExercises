package algorithm.leetcode.algorithm;
/*
 * hard
 * 42. Trapping Rain Water
 *  Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 *  compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6. 

see NO42_TrappingRainWater.png
The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water
 (blue section) are being trapped. Thanks Marcos for contributing this image!
 
 similar problems：
 11. Container With Most Water 
 238. Product of Array Except Self 
407. Trapping Rain Water II 
 */
import java.util.*;
public class NO42_TrappingRainWater {
	//方法1：
	//利用stack，计算当前能够存储最多的水量
	//stack用于记录当前能作为左边支柱最长的下标
	public int trap(int[] height) {
        if(height == null || height.length <= 1){
            return 0;
        }
        int sum = 0,pre,cur;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i< height.length ; i++){
            if(stack.isEmpty()){
                stack.push(i);
            }else{
                if(height[i] >= height[stack.peek()]){
                    if(stack.size() == 1){
                        stack.pop();
                    }else{
                        pre = stack.pop();
                        while(!stack.isEmpty() && height[i] >= height[stack.peek()]){
                            cur = stack.pop();
                            sum+=(i-cur-1)*(height[cur]-height[pre]);
                            pre = cur;
                        }
                        if(!stack.isEmpty()){
                            cur = stack.peek();
                            sum+=(i-cur-1)*(height[i]-height[pre]);
                        }
                    }
                }
                stack.push(i);
            }
        }
        return sum;
    }
	//方法2：
	//利用stack，计算当前能够存储最多的水量
	//stack用于记录左边下降的柱子的下标
	//当遇到比stack顶端小的柱子，pop出顶端当做底部，
	//计算当前能围的水的容量，即当前stack顶部高度和当前遇到柱子高度的最小值减去底部高度
	public int trap2(int[] height) {
        if(height == null || height.length < 3){
            return 0;
        }
        int left = 0,right = height.length-1,sum = 0;
        int maxLeft = 0,maxRight = 0;
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < height.length){
            if (s.isEmpty() || height[i]<=height[s.peek()]){
                s.push(i++);
            }
            else {
                int bot = s.pop();
                maxBotWater = s.isEmpty()? // empty means no il
                0:(Math.min(height[s.peek()],height[i])-height[bot])*(i-s.peek()-1);
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }
	//方法3：
	//用两个指针，从两端向中间移动，记录左右两端当前最高的支柱
	public int trap3(int[] height) {
        if(height == null || height.length <= 1){
            return 0;
        }
        int left = 0,right = height.length-1,sum = 0;
        int maxLeft = 0,maxRight = 0;
        while(left <= right){
            if(height[left] <= height[right]){
                if(height[left] >= maxLeft){
                    maxLeft = height[left];
                }else{
                    sum+=(maxLeft-height[left]);
                }
                left++;
            }else{
                if(height[right] >= maxRight){
                    maxRight = height[right];
                }else{
                    sum+=(maxRight-height[right]);
                }
                right--;
            }
        }
        return sum;
    }
	//方法4：
	//两个指针left，right,指向两边，两个变量记录两边最大高度maxLeft，maxLeft
	//当maxLeft < maxRight，说明左边能够存水，存水量增加maxLeft-height[left]
	//否则，水量增加maxRight-height[right]
	public int trap4(int[] height) {
        if(height == null || height.length < 3){
            return 0;
        }
        int left = 0,right = height.length-1,sum = 0;
        int maxLeft = 0,maxRight = 0;
        while(left <= right){
            maxLeft = Math.max(maxLeft,height[left]);
            maxRight = Math.max(maxRight,height[right]);
            if(maxLeft < maxRight){
                sum+=(maxLeft-height[left++]);
            }else{
                sum+=(maxRight-height[right--]);
            }
        }
        return sum;
    }
}
