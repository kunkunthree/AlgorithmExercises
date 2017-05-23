package algorithm.leetcode.algorithm;
/*
 * medium
 * 462. Minimum Moves to Equal Array Elements II 
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, 
 * where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:
Input:
[1,2,3]
Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

 */
import java.util.*;
public class NO462_MinimumMovestoEqualArrayElementsII {
	//方法1：
	//先排序，再从两端进行增减，哪端相同数值少的就对那边操作
	public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int left = 0,right = nums.length-1,length = nums.length,count = 0;
        while(left+1 < right && nums[left] == nums[left+1]){
            left++;
        }
        while(right-1 > left && nums[right] == nums[right-1]){
            right--;
        }
        while(left < right && nums[left] < nums[right]){
            if(left+1 < length-right){
                count+=(left+1)*(nums[left+1]-nums[left]);
                left++;
                while(left+1 < right && nums[left] == nums[left+1]){
                    left++;
                }
            }else{
                count+=(length-right)*(nums[right]-nums[right-1]);
                right--;
                while(right-1 > left && nums[right] == nums[right-1]){
                    right--;
                }
            }
        }
        return count;
    }
	//方法2：
	//思路和方法1一样，不过是用Map和List来实现
	public int minMoves2_2(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        Arrays.sort(nums);
        int length = nums.length,count = 0;
        for(int i = 0 ; i < length ; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],0);
            }
            map.put(nums[i],map.get(nums[i])+1);
        }
        List<int[]> list = new ArrayList<>();
        for(int key : map.keySet()){
            list.add(new int[]{key,map.get(key)});
        }
        Collections.sort(list,new Comparator<int[]>(){
            public int compare(int[] a1,int[] a2){
                return a1[0]-a2[0];
            }
        });
        int[] first,second,last,lastButOne;
        while(list.size() > 1){
            first = list.get(0);
            second = list.get(1);
            last = list.get(list.size()-1);
            lastButOne = list.get(list.size()-2);
            if(first[1] < last[1]){
                count+=first[1]*(second[0]-first[0]);
                second[1]+=first[1];
                list.remove(0);
            }else{
                count+=last[1]*(last[0]-lastButOne[0]);
                lastButOne[1]+=last[1];
                list.remove(list.size()-1);
            }
        }
        return count;
    }
	//方法3：
	//最佳算法
	//先对整个数组排序，然后进行加减运算，假设最终的数组的值都为k
	//则最小的元素min需要增加k-min，而最大的元素需要减少max-k，
	//所以最小的元素和最大的元素进行加减运算的总次数为max-min，和最终结果无关
	//依次类推，不断累加得到加减的次数
	//当元素个数为奇数时，最终的k为排序后的最中间的元素
	//当元素个数为偶数时，最终的k为排序后最中间的两个元素的和的一半
	public int minMoves2_3(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length,count = 0;
        for(int i = 0,j = length-1; i < j ; i++,j--){
            count+=nums[j]-nums[i];
        }
        return count;
    }
}
