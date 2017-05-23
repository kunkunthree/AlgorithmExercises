package algorithm.leetcode.algorithm;
/*
 * medium
 * 347. Top K Frequent Elements
 *  Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:

    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */
import java.util.*;
public class NO347_TopKFrequentElements {
	public static void main(String[] args) {
	}
	//方法1：
	//利用bucket
	public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],0);
            }
            map.put(nums[i],map.get(nums[i])+1);
        }
        
        List<Integer>[] bucket = new List[nums.length+1];
        for(int key : map.keySet()){
            int freq = map.get(key);
            if(bucket[freq] == null){
                bucket[freq] = new ArrayList<Integer>();
            }
            bucket[freq].add(key);
        }
        
        List<Integer> result = new ArrayList<>();
        for(int i = nums.length ; i > 0 && k > 0 ; i--){
            if(bucket[i] != null){
                result.addAll(bucket[i]);
                k-=bucket[i].size();
            }
        }
        return result;
    }
	//方法2：
	//利用maxHeap，最大堆
	//以频率作为权值，排列在优先队列中
	public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],0);
            }
            map.put(nums[i],map.get(nums[i])+1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = 
            new PriorityQueue<>(11,new Comparator<Map.Entry<Integer, Integer>>(){
                    public int compare(Map.Entry<Integer, Integer> e1,Map.Entry<Integer, Integer> e2){
                        return e2.getValue()-e1.getValue();
                    }
                });
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            maxHeap.add(e);
        }
        
        List<Integer> result = new ArrayList<>();
        while(result.size()<k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            result.add(entry.getKey());
        }
        return result;
    }
	//方法3：
	//用TreeMap，自动对频率排序，把相同频率的值组合在一起
	public List<Integer> topKFrequent3(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],0);
            }
            map.put(nums[i],map.get(nums[i])+1);
        }
        
        TreeMap<Integer,List<Integer>> treeMap = new TreeMap<>();
        for(Integer key : map.keySet()){
            int freq = map.get(key);
            if(!treeMap.containsKey(freq)){
                treeMap.put(freq,new ArrayList<Integer>());
            }
            treeMap.get(freq).add(key);
        }
        
        List<Integer> result = new ArrayList<>();
        while(result.size()<k){
            Map.Entry<Integer, List<Integer>> entry = treeMap.pollLastEntry();
            result.addAll(entry.getValue());
        }
        return result;
    }
}
