package algorithm.leetcode.algorithm;

/*
 * easy
 * 414. Third Maximum Number 
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

 Example 1:
 Input: [3, 2, 1]
 Output: 1
 Explanation: The third maximum is 1.

 Example 2:
 Input: [1, 2]
 Output: 2
 Explanation: The third maximum does not exist, so the maximum (2) is returned instead.

 Example 3:
 Input: [2, 2, 3, 1]
 Output: 1
 Explanation: Note that the third maximum here means the third maximum distinct number.
 Both numbers with value 2 are both considered as second maximum.

 */
import java.util.*;

public class NO414_ThirdMaximumNumber {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] array = new int[n];
			for (int i = 0; i < n; i++) {
				array[i] = in.nextInt();
			}
			System.out.println(thirdMax(array));
		}
	}

	public static int thirdMax(int[] nums) {
        int[] max = new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE};
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0 ; i < nums.length ; i++){
            if(!set.contains(nums[i])){
                for(int j = 0 ; j < max.length ; j++){
                    set.add(nums[i]);
                    if(nums[i] > max[j]){
                        for(int k = max.length-1 ; k > j ; k--){
                            max[k] = max[k-1];
                        }
                        max[j] = nums[i];
                        break;
                    }
                }
            }
        }
        if(nums.length <= 2 || max[1] == max[2] || set.size() <= 2){
            return max[0];
        }
        return max[2];
    }
	
	//不用HashSet，O(1)space,O(n)time
    public int thirdMax2(int[] nums) {
        Integer[] max = new Integer[3];
        for(int i = 0 ; i < nums.length ; i++){
            for(int j = 0 ; j < max.length ; j++){
                if(max[j] != null && nums[i] == max[j]){
                    break;
                }
                if(max[j] == null || nums[i] > max[j]){
                    for(int k = max.length-1 ; k > j ; k--){
                        max[k] = max[k-1];
                    }
                    max[j] = nums[i];
                    break;
                }
            }
        }
        return max[2] == null ? max[0] : max[2];
    }
    
    //利用PriorityQueue
    public int thirdMax3(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.contains(i)) {
                pq.offer(i);
                set.add(i);
                if (pq.size() > 3) {
                    set.remove(pq.poll());
                }
            }
        }
        if (pq.size() < 3) {
            while (pq.size() > 1) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}
