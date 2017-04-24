package algorithm.leetcode.algorithm;
/*
 * easy
 * 453. Minimum Moves to Equal Array Elements
 * Given a non-empty integer array of size n, find the minimum number of moves required
 *  to make all array elements equal, where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]
Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

 */
public class NO453_MinimumMovestoEqualArrayElements {
    public int minMoves(int[] nums) {
        if(nums == null || nums.length <=1){
            return 0;
        }
        int min = nums[0];
        int result = 0;
        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i] < min){
                result+=i*(min-nums[i]);
                min = nums[i];
            }else{
                result+=(nums[i]-min);
            }
        }
        return result;
    }
}
