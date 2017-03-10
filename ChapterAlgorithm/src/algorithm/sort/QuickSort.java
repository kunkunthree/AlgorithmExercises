package algorithm.sort;
import static xie.util.Print.*;

import java.util.*;
/*
 * http://www.cnblogs.com/luchen927/archive/2012/02/29/2368070.html
 * 快速排序:
 * 	挖坑填数+分治法
 * 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
 * 2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
 * 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
 * 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
 */
public class QuickSort {
	public static void main(String[] args) {
		Random rand = new Random();
		int[] array = new int[rand.nextInt(50)+1];
		println("size = "  + array.length);
		for(int i = 0 ; i < array.length ; i++){
			array[i] = rand.nextInt(100);
		}
//		int[] array = new int[]{65, 70, 41, 59, 67, 91, 62, 62, 62, 1, 28, 58, 50};
		println("sort before:");
		println(Arrays.toString(array));
		quickSort(array, 0, array.length-1);
		println("sort after:");
		println(Arrays.toString(array));
	}
	//升序
	public static void quickSort(int[] array,int low,int high){
		if(low >= high){
			return;
		}
		//如果low和high相差大于等于2，即low + 2 <= high
		int pivot = array[low];		//取第一个元素为中间数据
		int left = low;			
		int right = high;
		while(left < right){		//左右循环
			while(left < right && array[right] >= pivot){		//从右向左找第一个小于x的数  
				right--;
			}
//			if(left < right){
				array[left]=array[right];		
				//如果找到一个小于x的数，则赋给left位置，则进入下一个while循环，必然满足循环条件，使left++
//			}
			while(left < right && array[left] < pivot ){			//从左向右找第一个大于等于x的数
				left++;
			}
//			if(left < right){	
				array[right] = array[left];
				//如果找到一个大于等于x的数，则赋给left位置，则进入下一个while循环，若left<right,
				//必然满足循环条件，使left++
//			}
		}
		array[left] = pivot;
		quickSort(array, low, right-1);		//排序前面数组
		quickSort(array, right+1, high);	//排序后面数组
	}
	public static void quick_sort(int s[], int l, int r)  
	{  
	    if (l < r)  
	    {  
	        //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1  
	        int i = l, j = r, x = s[l];  
	        while (i < j)  
	        {  
	            while(i < j && s[j] >= x) // 从右向左找第一个小于x的数  
	                j--;    
	            if(i < j)   
	                s[i++] = s[j];  
	              
	            while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数  
	                i++;    
	            if(i < j)   
	                s[j--] = s[i];  
	        }  
	        s[i] = x;  
	        quick_sort(s, l, i - 1); // 递归调用   
	        quick_sort(s, i + 1, r);  
	    }  
	}  
}
