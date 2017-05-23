package algorithm.leetcode.algorithm;
/*
 * medium
 * 275. H-Index II
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm? 
 */
public class NO275_H_IndexII {
	//方法1：O(n)
	//长度与下标的差就是大于等于citations[i]的个数length-i
	//如果citations[i]大于等于这个个数length-i，那么这个个数就是h
	public int hIndex(int[] citations) {
        int length = citations.length;
        for(int i = 0 ; i < length ; i++){
            if(citations[i] >= length-i){
                return length-i;
            }
        }
        return 0;
    }
	//方法2：
	//O(logN)，二分查找
	public int hIndex2(int[] citations) {
        int length = citations.length;
        int left =0,right = length-1,mid;
        while(left <= right){
            mid = left + (right - left)/2;
            if(citations[mid] == length-mid){
                return length-mid;
            }
            if(citations[mid] < length-mid){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return length-left;
    }
}
