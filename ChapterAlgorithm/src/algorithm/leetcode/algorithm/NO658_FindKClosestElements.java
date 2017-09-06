package algorithm.leetcode.algorithm;
/*
 * medium
 * 658. Find K Closest Elements 
 *  Given a sorted array, two integers k and x, find the k closest elements to x in the array. 
 *  The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]

Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]

Note:
    1.	The value k is positive and will always be smaller than the length of the sorted array.
    2.	Length of the given array is positive and will not exceed 10^4
    3.	Absolute value of elements in the array and x will not exceed 10^4

 */
import java.util.*;
public class NO658_FindKClosestElements {
	//方法1：
	//O(n)time
	//先用二分法找出target在数组中对应的插入位置index，然后用两个指针
	//左指针left从index-1开始向左移动，右指针从index向右移动
	//每次移动前先比较两个指针所指向的值离target的距离，取更近的值，相等距离取更小值
	//每次只移动一个指针
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        int index = findIndex(arr,x);
        int left = index-1,right = index;
        int leftValue,rightValue;
        while((left >= 0 || right < arr.size()) && result.size() < k){
            if(left < 0){		//左边超出边界
                result.add(arr.get(right++));
            }else if(right >= arr.size()){	//右边超出边界
                result.add(0,arr.get(left--));
            }else{		//左右都在边界内
                leftValue = arr.get(left);
                rightValue = arr.get(right);
                if(x-leftValue <= rightValue-x){	// <= 是因为相同距离的情况下选更小值
                    result.add(0,leftValue);
                    left--;
                }else{
                    result.add(rightValue);
                    right++;
                }
            }
        }
        return result;
    }
    //找到target在数组中最大的坐标，坐标之前的元素都小于等于target，后面的元素都大于等于target
    //如果该坐标在数组范围内，那么该坐标对应的值应当大于等于target
    private int findIndex(List<Integer> list,int target){
        int left = 0,right = list.size()-1,mid;
        while(left < right){
            mid = left + (right-left)/2;
            if(list.get(mid) > target){
                right = mid;
            }else if(list.get(mid) < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return left;
    }
    
    //方法2
    //O(nlogn)time
    //先以x的距离排序，然后截取前k个数，然后再从小到大排序
    public List<Integer> findClosestElements2(List<Integer> arr, int k, int x) {
    	final int tmpX = x;
    	Collections.sort(arr,new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                if(a == b){
                    return a-b;
                }else{
                    return Math.abs(tmpX-a) - Math.abs(tmpX-b);
                }
            }
        });
        List<Integer> result = arr.subList(0,k);
        Collections.sort(result);
        return result;
    }
    
}
