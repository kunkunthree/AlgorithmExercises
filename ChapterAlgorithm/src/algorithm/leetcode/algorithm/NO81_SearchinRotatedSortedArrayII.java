package algorithm.leetcode.algorithm;
/*
 * medium
 * 81. Search in Rotated Sorted Array II
 * 
    Follow up for "Search in Rotated Sorted Array":
    What if duplicates are allowed?

    Would this affect the run-time complexity? How and why?

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.
 */
public class NO81_SearchinRotatedSortedArrayII {
	public static void main(String[] args) {
		int[] nums = new int[]{1,3,1,1};
		int target = 3;
		System.out.println(search2(nums, target));
	}
	//方法1：
	//先找出旋转的k值，在通过修正的二分法找出target
	//最差情况O(n),最好情况O(logN)
    public boolean search(int[] nums, int target) {
        int k = 0,n = nums.length;
        for(int i = 0 ; i < n-1 ; i++){
            if(nums[i] > nums[i+1]){
                k = i+1;
                break;
            }
        }
        int left = 0,right = n-1,mid,realMid;
        while(left <= right){
            mid = right - (right - left) / 2;
            realMid = (mid+k)%n;
            if(nums[realMid] < target){
                left = mid + 1;
            }else if(nums[realMid] > target){
                right = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }
    
    //方法2：
    //二分查找，当数组全为相同元素且target不在数组中时，时间复杂度为O(n)
    //先确定nums[mid]是否等于target，如果相等则返回true，如果不等，则进行下面的判断
    //通过nums[mid]和nums[right]比较，确定哪个范围是排好序的，
    //再确定target是否在排好序的范围内，如果不在，则在另一个范围
    public static boolean search2(int[] nums, int target) {
        int left = 0,right = nums.length-1,mid;
        while(left <= right){
            mid = left + (right - left) / 2; //当left < right 时，mid < right，mid是偏小的
            if(nums[mid] == target){
                return true;
            }
            if(nums[right] > nums[mid]){//nums[mid...right]是排好序的
                if(target > nums[mid] && target <= nums[right]){    //target在nums[mid...right]范围内
                    left = mid+1;
                }else{      //target在nums[left...mid]范围内
                    right = mid;
                }
            }else if(nums[right] < nums[mid]){//nums[mid...right]是没有排好序的，但nums[left...midt]肯定是排好序的
                if(target < nums[mid] && target >= nums[left]){     //target在nums[left...mid]范围内
                    right = mid;
                }else{      //target在nums[mid...right]范围内
                    left = mid + 1;     
                }
            }else{      //nums[right] = nums[mid] && target != nums[mid]
                //不能确定哪边时排好序的，但能确定nums[right]不是target
                right--;
            }
        }
        return false;
    }
    
    //方法3：
    //方法2的另一种形式，通过nums[mid]和nums[right]比较，确定哪个范围是排好序的
    public boolean search3(int[] nums, int target) {
        int left = 0,right = nums.length-1,mid;
        while(left <= right){
            mid = left + (right - left) / 2; //当left < right 时，mid < right，mid是偏小的
            if(nums[mid] == target){
                return true;
            }
            if(nums[left] < nums[mid]){//nums[left...mid]是排好序的
                if(target < nums[mid] && target >= nums[left]){    //target在nums[left...mid]范围内
                    right = mid;
                }else{      //target在nums[mid...right]范围内
                    left = mid+1;
                }
            }else if(nums[left] > nums[mid]){//nums[left...mid]是没有排好序的，但nums[mid...right]肯定是排好序的
                if(target > nums[mid] && target <= nums[right]){     //target在nums[mid...right]范围内
                    left = mid + 1;     
                }else{      //target在nums[left...mid]范围内
                    right = mid;
                }
            }else{      //nums[left] = nums[mid] && target != nums[mid]
                //不能确定哪边时排好序的，但能确定nums[left]不是target
                left++;
            }
        }
        return false;
    }
}
