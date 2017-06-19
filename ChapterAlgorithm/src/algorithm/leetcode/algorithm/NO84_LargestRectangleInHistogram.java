package algorithm.leetcode.algorithm;
/*
 * hard
 * 84. Largest Rectangle in Histogram 
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
 * find the area of largest rectangle in the histogram. 
 * 
 * see NO84_LargestRectangleInHistogram_1.png:
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * 
 * see NO84_LargestRectangleInHistogram_2.png:
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * 
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10. 
 */
import java.util.*;
public class NO84_LargestRectangleInHistogram {
	//方法1：
	//利用栈stack，保存比当前高度高的柱子下标，
	//当当前高度比stack顶端的高度低时，直接压进栈，
	//当当前高度比stack顶端的高度高时，计算当前下标之前连续的比当前高度高的矩形面积
	//解析：
	//http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
	public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int h = 0,maxArea = 0;
        for(int i = 0 ; i <= heights.length ; i++){
            //当超过柱状图右边界时，高度为0
            h = (i == heights.length ? 0 : heights[i]);
            //如果stack为空或者当前高度比stack顶部高度要高，则将heights[i]压进栈
            if(stack.isEmpty() || h >= heights[stack.peek()]){
                stack.push(i);
            }else{
                int top = stack.pop();
                //计算中间比heights[i]要高的柱状图面积
                maxArea = Math.max(maxArea,heights[top] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
                i--;
            }
        }
        return maxArea;
    }
}
