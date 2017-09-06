package algorithm.leetcode.algorithm;
/*
 * medium
 * 540. Single Element in a Sorted Array 
 *  Given a sorted array consisting of only integers where every element appears twice except for one element
 *   which appears once. Find this single element that appears only once.

Example 1:

Input: [1,1,2,3,3,4,4,8,8]
Output: 2

Example 2:

Input: [3,3,7,7,10,11,11]
Output: 10

Note: Your solution should run in O(log n) time and O(1) space. 
 */
public class NO540_SingleElementInASortedArray {
	//方法1：
	//O(log n) time and O(1) space. 
	//只出现1次的数的下标必然是偶数，该数前面的数第一次出现的下标是偶数，后面的数第一次出现的下标是奇数
	//根据这个规律进行二分
	public int singleNonDuplicate(int[] nums) {
        int left = 0,right = nums.length-1,mid;
        while(left < right){
            mid = left + (right - left)/2;
            if(mid > 0 && nums[mid] == nums[mid-1]){    //如果出现了两次，左边
                if(((mid-1)&1) == 0){       //如果第一次出现的下标是偶数
                    left = mid+1;
                }else{                      //如果第一次出现的下标是奇数
                    right = mid-2;
                }
            }else if(mid < nums.length-1 && nums[mid] == nums[mid+1]){  //如果出现了两次，右边
                if((mid&1) == 0){       //如果第一次出现的下标是偶数
                    left = mid+2;
                }else{                  //如果第一次出现的下标是奇数
                    right = mid-1;
                }
            }else{
                return mid;
            }
        }
        return nums[left];
    }
	//方法2：
	//同方法1，但是方法更简单，直接对下标取一半
	public int singleNonDuplicate2(int[] nums) {
        // binary search
        int n=nums.length, lo=0, hi=n/2;
        while (lo < hi) {
            int m = (lo + hi) / 2;
            if (nums[2*m]!=nums[2*m+1]) hi = m;
            else lo = m+1;
        }
        return nums[2*lo];
    }
}
