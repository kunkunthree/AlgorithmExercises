package algorithm.leetcode.algorithm;
/*
 * medium
 * 373. Find K Pairs with Smallest Sums
 *  You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
Define a pair (u,v) which consists of one element from the first array and one element from the second array.
Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
Return: [1,2],[1,4],[1,6]
The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
Return: [1,1],[1,1]
The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 
Return: [1,3],[2,3]
All possible pairs are returned from the sequence:
[1,3],[2,3]

 */
import java.util.*;
public class NO373_FindKPairswithSmallestSums {
	//方法1：
	//使用PriorityQueue
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(1,new Comparator<int[]>(){
           public int compare(int[] pair1,int[] pair2){
               return pair1[0]+pair1[1]-pair2[0]-pair2[1];
           } 
        });
        for(int i = 0 ; i < nums1.length ; i++){
            for(int j = 0 ; j < nums2.length ; j++){
                queue.offer(new int[]{nums1[i],nums2[j]});
            }
        }
        while(k > 0 && !queue.isEmpty()){
            list.add(queue.poll());
            k--;
        }
        return list;
    }
	//方法2：
	//O(klogk)time
	//see NO373_FindKPairswithSmallestSums.png
	//把{nums1[i],nums2[0],0}所有0<=i<=nums1.length-1,加入PriorityQueue，
	//若{nums1[i],nums2[j],j}从PriorityQueue中取出后，则加入{nums1[i],nums2[j+1],j+1}，0<=j+1<=nums2.length-1
	public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<int[]> list = new ArrayList<>();
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0){
            return list;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(1,new Comparator<int[]>(){
           public int compare(int[] pair1,int[] pair2){
               return pair1[0]+pair1[1]-pair2[0]-pair2[1];
           } 
        });
        for(int i = 0 ; i < k && i < nums1.length ; i++){
                queue.offer(new int[]{nums1[i],nums2[0],0});
        }
        while(k > 0 && !queue.isEmpty()){
            int[] cur = queue.poll();
            list.add(new int[]{cur[0],cur[1]});
            if(cur[2] < nums2.length-1){
                queue.offer(new int[]{cur[0],nums2[cur[2]+1],cur[2]+1});
            }
            k--;
        }
        return list;
    }
}
