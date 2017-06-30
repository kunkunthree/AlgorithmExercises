package algorithm.leetcode.algorithm;
/*
 * hard
 * 480. Sliding Window Median
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
 * So the median is the mean of the two middle value.
Examples:

[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to 
the very right. You can only see the k numbers in the window. Each time the sliding window moves right 
by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position               	 Median
---------------              					 -----
[1  3  -1] -3  5  3  6  7      				 1
 1 [3  -1  -3] 5  3  6  7       			-1
 1  3 [-1  -3  5] 3  6  7      			 	-1
 1  3  -1 [-3  5  3] 6  7       			3
 1  3  -1  -3 [5  3  6] 7       			5
 1  3  -1  -3  5 [3  6  7]      			6

Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 */
import java.util.*;
public class NO480_SlidingWindowMedian {
	public static void main(String[] args) {
		 int[] minHeapSize = new int[]{0};
	}
	//方法1：
	//用两个TreeMap保存保存当前窗口的元素，并将其排序，分成两半。
	//一个maxHeap保存较小的一半，并且从大到小排序
	//一个minHeap保存较大的一半，并且从小到大排序
	//每次右移，则将新元素加入到其中一个TreeMap中，计数加一
	//		如果小于等于maxHeap的最大值，则加入maxHeap
	//		如果大于minHeap的最小值，则加入minHeap
	//	并将被移出窗口的元素，从TreeMap中计数减一
	//当计数为0时则删除。
	//调整两个heap的大小，使其大小分别为maxCapacity和minCapacity
	//最后就能得到当前窗口的中位数，如果maxCapacity比minCapacity大则直接返回maxHeap的最大值
	//否则返回maxHeap的最大值和minHeap的最小值的平均值
	//移向下一个位置
	public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < k){
            return new double[0];
        }
        double[] result = new double[nums.length - k + 1];
        TreeMap<Integer,Integer> minHeap = new TreeMap<>();
        TreeMap<Integer,Integer> maxHeap = new TreeMap<>(Collections.reverseOrder());
        
        int minCapacity = k/2;
        int maxCapacity = k - minCapacity;
        
        int[] maxSize = new int[]{0};
        int[] minSize = new int[]{0};
        
        for(int i = 0 ; i < k ; i++){
            add(nums[i],maxHeap,maxSize);
        }
        for(int i = 0 ; i < minCapacity ; i++){
            move1To(maxHeap,maxSize,minHeap,minSize);
        }
        result[0] = getMedian(maxHeap,maxSize,minHeap,minSize);
        
        int resIndex = 1;
        for(int i = k ; i < nums.length ; i++){
            int addVal = nums[i];
            if(addVal <= maxHeap.keySet().iterator().next()){
                add(addVal,maxHeap,maxSize);
            }else{
                add(addVal,minHeap,minSize);
            }
            
            int removeVal = nums[i-k];
            if(removeVal <= maxHeap.keySet().iterator().next()){
                remove(removeVal,maxHeap,maxSize);
            }else{
                remove(removeVal,minHeap,minSize);
            }
            
            if(maxSize[0] > maxCapacity){
                move1To(maxHeap,maxSize,minHeap,minSize);
            }else if(maxSize[0] < maxCapacity){
                move1To(minHeap,minSize,maxHeap,maxSize);
            }
            result[resIndex++] = getMedian(maxHeap,maxSize,minHeap,minSize);
        }
        return result;
    }
    private void add(int val,TreeMap<Integer,Integer> heap, int[] heapSize){
        heapSize[0]++;
        if(!heap.containsKey(val)){
            heap.put(val,0);
        }
        heap.put(val,heap.get(val)+1);
    }
    private boolean remove(int val,TreeMap<Integer,Integer> heap, int[] heapSize){
        if(heap.containsKey(val) && heap.get(val) > 0){
            heapSize[0]--;
            heap.put(val,heap.get(val)-1);
            if(heap.get(val) == 0){
                heap.remove(val);
            }
        }
        return false;
    }
    private boolean move1To(TreeMap<Integer, Integer> heap1, int[] heap1Size,
    														TreeMap<Integer, Integer> heap2, int[] heap2Size){
        if(heap1.keySet().size() > 0 && heap1Size[0] > 0){
            int val = heap1.keySet().iterator().next();
            add(val,heap2,heap2Size);
            remove(val,heap1,heap1Size);
            return true;
        }
        return false;
    }
    private double getMedian(TreeMap<Integer, Integer> maxHeap, int[] maxSize, 
    														TreeMap<Integer, Integer> minHeap,  int[] minSize){
        return maxSize[0] > minSize[0] ? 
                (double)maxHeap.keySet().iterator().next() : 
                ((double)maxHeap.keySet().iterator().next() + (double)minHeap.keySet().iterator().next())/2.0;
    }
}
