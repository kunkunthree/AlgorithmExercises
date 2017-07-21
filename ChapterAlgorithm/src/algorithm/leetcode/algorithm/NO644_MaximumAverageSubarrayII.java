package algorithm.leetcode.algorithm;
/*
 * hard
 * 644. Maximum Average Subarray II 
 *  Given an array consisting of n integers, find the contiguous subarray whose length is greater than
 *   or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.

Note:
    1.		1 <= k <= n <= 10,000.
    2.		Elements of the given array will be in range [-10,000, 10,000].
    3.		The answer with the calculation error less than 10-5 will be accepted.

similar problems：
643. Maximum Average Subarray I 
 */
public class NO644_MaximumAverageSubarrayII {
	//方法1：
	//通过二分法从[min,max]中找出符合的平均值，min是数组最小值，max是数组最大值
	//每次通过判断二分后的中间值是否能在数组中是否能找到连续子数组平均值比该值大，
	//		如果符合，则left = mid，增大下界
	//		如果不符合，则right = mid，缩小上界
	//判断方法：
	//(nums[i]+nums[i+1]+...+nums[j])/(j-i+1)>=x
	//=>nums[i]+nums[i+1]+...+nums[j]>=x*(j-i+1)
	//=>(nums[i]-x)+(nums[i+1]-x)+...+(nums[j]-x)>=0
	//判断数组中长度大于等于k的连续子数组的部分和是否大于等于0
	//为了求连续子数组部分和最大值，可以通过滑动窗口法获得
	//并且求滑动窗口左端到右端-k位置的部分和，如果小于0，则可以舍弃
	public double findMaxAverage(int[] nums, int k) {
        if(nums == null || nums.length < k){
            throw new RuntimeException("输入有误");
        }
        double left = Integer.MIN_VALUE,right = Integer.MAX_VALUE,mid;
        for(int num : nums){
            left = Math.min(left,num);
            right = Math.max(right,num);
        }
        while(right - left > 0.00001){
            mid = (left + right)/2.0;
            if(check(nums,k,mid)){
                left = mid;
            }else{
                right = mid;
            }
        }
        return right;
    }
    private boolean check(int[] nums,int k ,double x){
        int n = nums.length;
        double[] a = new double[n];
        for(int i = 0 ; i < n ; i++){
            a[i] = nums[i] - x;
        }
        double now = 0.0,last = 0;
        for(int i = 0 ; i < k ; i++){
            now+=a[i];
        }
        if(now >= 0){
            return true;
        }
        for(int i = k ; i < n ; i++){
            now+=a[i];
            last+=a[i-k];
            if(last < 0){
                now-=last;
                last = 0;
            }
            if(now >= 0){
                return true;
            }
        }
        return false;
    }
}
