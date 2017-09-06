package algorithm.didi.autumn2017;
import java.util.*;
public class NO2 {
	public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
       	String[] ss = in.nextLine().split(" ");
       	for(String s : ss){
       		if(s.length() > 0){
       			list.add(Integer.valueOf(s));
       		}
       	}
        int k = Integer.valueOf(in.nextLine());
        int result = findKthLargestHelper(list,0,list.size()-1,k-1);
        System.out.println(result);
    }
	public static int findKthLargestHelper(List<Integer> nums,int start,int end, int k){
        if(start > end){
            return Integer.MAX_VALUE;
        }
        int pivot = nums.get(start);
        int left = start;
        for(int i = start+1 ; i <= end ; i++){
            if(nums.get(i)>pivot){
                swap(nums,i,++left);
            }
        }
        swap(nums,start,left);
        if(left == k){
            return nums.get(left);
        }else if(left > k){
            return findKthLargestHelper(nums,start,left-1,k);
        }else{
            return findKthLargestHelper(nums,left+1,end,k);
        }
    }
    private static void swap(List<Integer> nums,int i,int j){
        int tmp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, tmp);
    }
}
