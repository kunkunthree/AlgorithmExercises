package algorithm.leetcode.algorithm;
/*
 * medium
 * 274. H-Index 
 *  Given an array of citations (each citation is a non-negative integer) of a researcher,
 *   write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "
A scientist has index h if h of his/her N papers have at least h citations each, 
and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total 
and each of them had received 3, 0, 6, 1, 5 citations respectively. 
Since the researcher has 3 papers with at least 3 citations each 
and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index. 
 */
import java.util.*;
public class NO274_H_Index {
	//方法1：O(nlogn)time,O(1)space
	//先排序，在从小到大找大于等于长度与下标的差的第一个数，数组长度与该下标的差就是h
	public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for(int i = 0 ; i < citations.length ;i++){
            if(citations[i] > 0 && citations.length-i <= citations[i]){
                return citations.length-i;
            }
        }
        return 0;
    }
	//方法2：O(n)time,O(n)space
	//建立一个长度为n+1的数组，统计大于等于i的个数
	//先统计等于i(0~n-1)的数和大于等于n的数，在从大到小累加得到大于等于i的个数
	//当count[i]>=i时，返回i
	public int hIndex2(int[] citations) {
		int length = citations.length;
        int[] count = new int[length+1];
        for(int i = 0 ; i < length ; i++){
            if(citations[i] >=  length){
                count[length]++;
            }else{
                count[citations[i]]++;
            }
        }
        int tmp = 0;
        if(count[length]>=length){
            return length;
        }
        for(int i = length-1 ; i >= 0 ; i--){
            count[i]+=count[i+1];
            if(count[i] >= i){
                return i;
            }
        }
        return 0;
    }
}
