package algorithm.leetcode.algorithm;
/*
 * medium
 * 324. Wiggle Sort II
 *  Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space? 
 */
public class NO324_WiggleSortII {
	//方法1：
	//O(n)time,O(1)space
	//因为偶数下标比左右两边的偶数下标大，所以只要所有偶数下标的值都大于奇数下标的值，那么必然符合题目所求
	//那么可以把数组分为两部分a和b，a的所有值都大于等于b，mid为分割a和b的值，
	//如果只是单纯地把a放在奇数坐标，b放在偶数坐标，可能会出现相邻坐标的值相等的情况
	//那么只要把大于mid的部分放在奇数下标的较前，把小于mid的部分放在偶数下标的较后，
	//等于mid的部分放在奇数下标的较后和偶数下标的较前，那么此时必然不会出现相邻值相等的情况
	//先用quickSelect找出数组的中间值mid，此时前半段的值大于等于mid，后半段的值小于等于mid
	//用虚拟指针，让前半段指针i<=n/2指向奇数指针，后半段指针i>n/2指向偶数数指针，令newIndex(i) = (1+2*i)%(n|1)
	//注意，因为需要使newIndex(i)不能有重叠的情况，所以不能单纯地令newIndex(i) = (1+2*i)%n
	//因为如果n为偶数时，newIndex(0) =  (1 + 2 * 0)%n = 1 = (1 + 2 * n/2) % n = newIndex(n/2)
	//newIndex((i) =  (1 + 2 * i)%n = 1+2*i = (1 + 2 * (i+n/2)) % n = newIndex(i+n/2),	i<n/2
	//为了使后半部分映射的坐标和前半部分映射的坐标错开，我们可以使n与1做或运算，得到一个奇数，
	//那么映射的坐标就不会重叠newIndex((i) =  (1 + 2 * i)%n = 1+2*i ≠ 2*i = (1 + 2 * (i+n/2)) % n = newIndex(i+n/2),	i<n/2
	public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 1){
            return;
        }
        int n = nums.length;
        int mid = quickSelect(nums,0,n-1,n/2);
        int left = 0;
        int right = n-1;
        int i = 0;
        while(i <= right){
        	//把大于mid的部分放在奇数下标的较前
            if(nums[newIndex(i,n)] > mid){
                swap(nums,newIndex(i++,n),newIndex(left++,n));
            }//把小于mid的部分放在偶数下标的较后
            else if(nums[newIndex(i,n)] < mid){
                swap(nums,newIndex(i,n),newIndex(right--,n));
            }else{
            	//等于mid的部分放在奇数下标的较后和偶数下标的较前
                i++;
            }
        }
    }
    private int newIndex(int i,int n){
        return (1+2*i)%(n|1);
    }
    private int quickSelect(int[] nums,int start,int end,int k){
        if(start > end){
            return Integer.MAX_VALUE;
        }
        int pivot = nums[start];
        int left = start;
        for(int i = start+1 ; i <= end ; i++){
            if(nums[i] > pivot){
                swap(nums,i,++left);
            }
        }
        swap(nums,start,left);
        if(left == k){
            return nums[left];
        }else if(left > k){
            return quickSelect(nums,start,left-1,k);
        }else{
            return quickSelect(nums,left+1,end,k);
        }
    }
    private void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
