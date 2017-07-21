package algorithm.leetcode.algorithm;
/*
 * hard
 * 632. Smallest Range 
 * You have k lists of sorted integers in ascending order. 
 * Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:

Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Note:
    1.		The given list may contain duplicates, so ascending order means >= here.
    2.		1 <= k <= 3500
    3.		-105 <= value of elements <= 105.
    4.		For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.

 */
import java.util.*;
public class NO632_SmallestRange {
	//方法1：
	//可以看作是用一个堆来合并n个已经排好序的数组
	//每一次从堆中弹出最小的值，然后把这个值所对应的数组中的下一个值加入到堆中
	//每次操作都求堆中的范围，求每次操作的堆的范围的最小值
	//类似于滑动窗口法，只不过这个窗口覆盖了n个数组
	class Element{
        public int val,index,row;
        public Element(int val,int index,int row){
            this.val = val;
            this.index = index;
            this.row = row;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        int start = Integer.MIN_VALUE,end = Integer.MAX_VALUE;
        if(nums == null || nums.size() == 0){
            return new int[]{start,end};
        }
        PriorityQueue<Element> queue = new PriorityQueue<Element>(1,new Comparator<Element>(){
            public int compare(Element e1,Element e2){
                return e1.val - e2.val;
            }
        });
        long range = Long.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < nums.size() ; i++){
            max = Math.max(max,nums.get(i).get(0));
            queue.offer(new Element(nums.get(i).get(0),0,i));
        }
        while(queue.size() == nums.size()){
            Element e = queue.poll();
            if((long)max - e.val < range){
                range = (long)max - e.val;
                start = e.val;
                end = max;
            }
            if(e.index+1 < nums.get(e.row).size()){
                e.val = nums.get(e.row).get(++e.index);
                max = Math.max(max,e.val);
                queue.offer(e);
            }
        }
        return new int[]{start,end};
    }
}
