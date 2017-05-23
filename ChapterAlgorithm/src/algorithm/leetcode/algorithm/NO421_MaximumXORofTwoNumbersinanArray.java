package algorithm.leetcode.algorithm;
/*
 * medium
 * 421. Maximum XOR of Two Numbers in an Array
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

 */
import java.util.*;
public class NO421_MaximumXORofTwoNumbersinanArray {
	//方法1：
	//构造结果：
	//先从最高位开始，判断最后结果是否有最高位，把所有数的最高位情况存再set中，
	//如果存在有一个数与最高位的1异或运算结果仍然在set中，则最大异或的值的这一位必然为1，否则为0
	//依次类推，每一次都保存前n位在set中，判断是否与构造的临时max相异或得到的结果仍然在set中，
	//存在则保留这一位的1，否则max这一位为0，从最高位往最低位，不断迭代得到最终max的前n位
	//O(n)time
	public int findMaximumXOR(int[] nums) {
        int mask = 0,max = 0,tmp;
        Set<Integer> set = new HashSet<>();
        for(int i = 31 ; i >= 0 ; i--){
            mask |= 1<<i;
            set.clear();
            for(int num : nums){
                set.add(num&mask);
            }
            tmp = max | 1<<i;
            for(int prefix : set){
                if(set.contains(tmp^prefix)){
                    max = tmp;
                }
            }
        }
        return max;
    }
	//方法2：
	//构建字典树，然后通过遍历从字典树中得到每一个数异或的最大值
	//O(n)time
	class Trie{
        Trie[] children;
        Trie(int n){
            children = new Trie[n];
        }
    }
    public int findMaximumXOR2(int[] nums) {
        int mask = 0,max = Integer.MIN_VALUE,curBit;
        Trie root = new Trie(2),curNode;
        for(int num : nums){
            curNode = root;
            for(int i = 31 ; i >= 0 ; i--){
                curBit = (num>>>i)&1;
                if(curNode.children[curBit] == null){
                    curNode.children[curBit] = new Trie(2);
                }
                curNode = curNode.children[curBit];
            }
        }
        for(int num : nums){
            curNode = root;
            int curMax = 0;
            for(int i = 31 ; i >= 0 ; i--){
                curBit = (num>>>i)&1;
                if(curNode.children[curBit^1] != null){
                    curMax |= 1<<i;
                    curNode = curNode.children[curBit^1];
                }else{
                    curNode = curNode.children[curBit];
                }
            }
            max = Math.max(max,curMax);
        }
        return max;
    }
}
