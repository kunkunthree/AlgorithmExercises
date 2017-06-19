package algorithm.leetcode.algorithm;
/*
 * hard
 * 295. Find Median from Data Stream 
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
 * So the median is the mean of the two middle value.
Examples:
[2,3,4] , the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:
    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.

For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

 */
import java.util.*;
public class NO295_FindMedianFromDataStream {
	public static void main(String[] args) {
		double x = 0.1;
		x++;
		System.out.println(x);
	}
	//方法1：
	//利用两个PriorityQueue，保留从小到大排序后的前半部分firstHalf和后半部分lastHalf
	//每当添加一个新元素时，先加入到前半部分firstHalf，然后将前半部分firstHalf的最小值加入到后半部分lastHalf
	//如果此时前半部分firstHalf的长度小于后半部分lastHalf的长度，为了平衡两个队列的长度
	//则将后半部分lastHalf的最大元素添加到前半部分firstHalf中，保持前半部分firstHalf的长度大于等于后半部分lastHalf的长度
	PriorityQueue<Integer> firstHalf;
    PriorityQueue<Integer> lastHalf;
    /** initialize your data structure here. */
    public NO295_FindMedianFromDataStream() {
        firstHalf = new PriorityQueue<>();
        lastHalf = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        firstHalf.offer(num);
        lastHalf.offer(-firstHalf.poll());
        if(firstHalf.size() < lastHalf.size()){
            firstHalf.offer(-lastHalf.poll());
        }
    }
    
    public double findMedian() {
        return firstHalf.size() == lastHalf.size() ? (firstHalf.peek()-lastHalf.peek())/2.0 : firstHalf.peek();
    }
}
