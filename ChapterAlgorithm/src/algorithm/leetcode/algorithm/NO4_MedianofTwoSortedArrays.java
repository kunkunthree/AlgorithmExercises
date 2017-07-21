package algorithm.leetcode.algorithm;
/*
 * hard
 * 4. Median of Two Sorted Arrays 
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
Example 1:
		nums1 = [1, 3]
		nums2 = [2]
		The median is 2.0
Example 2:
		nums1 = [1, 2]
		nums2 = [3, 4]
		The median is (2 + 3)/2 = 2.5

similar problems:

 */
public class NO4_MedianofTwoSortedArrays {
	public static void main(String[] args) {
		int[] nums1 = new int[]{1,3};
//		System.out.println(nums1.length);
		int[] nums2 = new int[]{2};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
	/*
	 * 把数组A(长度为m)和数组B（长度为n）分成两部分，n>=m;
	 * 	left_part			   |				right_part
	 * A[0],A[1],...A[i-1]   |	A[i],A[i+1].....A[m-1]
	 * B[0],B[1],...B[j-1]   |	B[j],B[j+1].....B[n-1]
	 * 当i+j = m+n - i -j or i+j = m+n+1  且 时 A[i-1] <= B[j] && B[j-1] <= A[i] 时，
	 * 当m+n为奇数时，中位数为max(left_part)
	 * 当m+n为偶数时，中位数 (max(left_part)+min(right_part))/2.0
	 * 通过二分法在[0,m]中来找满足上面条件的i的值
	 */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median1 = 0,median2 = 0;
        int length1 = nums1.length;
        int length2 = nums2.length;
        if(length1 > length2){
        	int[] tmpNums = nums1;
        	nums1 = nums2;
        	nums2 = tmpNums;
        	length1 = nums1.length;
        	length2 = nums2.length;
        }
        if(length1 == 0){
        	if(length2 == 0){
        		throw new RuntimeException("two arrays are both empty");
        	}else{
        		if(length2%2 == 1)
        			return nums2[length2/2];
        		else
        			return (nums2[length2/2]+nums2[length2/2-1])/2.0;
        	}
        }
        int iMin = 0,iMax = length1,halfLen = (length1+length2 +1)/2;
        int i,j;
        while(iMin <= iMax){
        	i = (iMin + iMax)/2;
        	j = halfLen -i;
        	if(i < length1 && nums2[j-1] > nums1[i]){
        		iMin = i+1;
        	}else if(i > 0 && nums1[i-1] > nums2[j]){
        		iMax = i-1;
        	}else{
        		double maxOfLeft = 0;
        		if(i == 0){
        			maxOfLeft = nums2[j-1];
        		}else if(j == 0){
        			maxOfLeft = nums1[i-1];
        		}else{
        			maxOfLeft = Math.max(nums1[i-1], nums2[j-1]);
        		}
        		if((length1+length2)%2 == 1){
        			return maxOfLeft;
        		}
        		double minOfRight = 0;
        		if(i == length1){
        			minOfRight = nums2[j];
        		}else if(j == length2){
        			minOfRight = nums1[i];
        		}else{
        			minOfRight = Math.min(nums1[i], nums2[j]);
        		}
        		return (maxOfLeft + minOfRight)/2.0;
        	}
        }
        return 0;
    }
}
