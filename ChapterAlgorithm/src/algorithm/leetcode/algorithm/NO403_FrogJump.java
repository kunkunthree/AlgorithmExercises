package algorithm.leetcode.algorithm;
/*
 * hard
 * 403. Frog Jump 
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. 
 * The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, 
determine if the frog is able to cross the river by landing on the last stone. Initially, 
the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. 
Note that the frog can only jump in the forward direction.

Note:
    The number of stones is ≥ 2 and is < 1,100.
    Each stone's position will be a non-negative integer < 231.
    The first stone's position is always 0.

Example 1:
[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.

Example 2:
[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.

 */
import java.util.*;
public class NO403_FrogJump {
	public static void main(String[] args) {
		int[] stones = new int[]{0,1,3,6,10,15,16,21};
		System.out.println(canCross(stones));
	}
	//方法1：
	//利用HashMap<Integer,Set>记录每一个石头所能走的步数
	//初始化：map.get(stones[0]).add(1); 		HashSet<Integer> steps;
	//从0~n-2开始遍历，如果当前石头没有能够走的步数，那么返回false
	//如果有，先判断是否到达最后一个石头，可以则返回true
	//否则判断走该步数后是否有石头，即map中是否有对应的key，更新走该步数后的石头所能走的步数
	public static boolean canCross(int[] stones) {
		if(stones == null || stones.length < 2){
            return false;
        }
        int n = stones.length;
        HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            map.put(stones[i],new HashSet<Integer>());
        }
        map.get(stones[0]).add(1);
        HashSet<Integer> steps;
        for(int i = 0 ; i < n-1 ; i++){
            steps = map.get(stones[i]);
            if(steps.size() == 0){
                return false;
            }
            for(int step : steps){
                if(step == 0)continue;
                int nextStone = stones[i]+step;
                if(nextStone == stones[n-1]){
                    return true;
                }
                if(map.containsKey(nextStone)){
                    map.get(nextStone).add(step-1);
                    map.get(nextStone).add(step);
                    map.get(nextStone).add(step+1);
                }
            }
        }
        return false;
    }
}
