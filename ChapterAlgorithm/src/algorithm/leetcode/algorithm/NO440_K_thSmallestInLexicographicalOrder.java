package algorithm.leetcode.algorithm;
/*
 * hard
 * 440. K-th Smallest in Lexicographical Order 
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

Note: 1 ≤ k ≤ n ≤ 109.

Example:

Input:
n: 13   k: 2

Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

 */
public class NO440_K_thSmallestInLexicographicalOrder {
	//方法1：
	//利用一个十进制树（10叉树），对这个树进行先根遍历的顺序就是所求的顺序
	//Initially, image you are at node 1 (variable: curr),
	// the goal is move (k - 1) steps to the target node x. (substract steps
	// from k after moving)
	// when k is down to 0, curr will be finally at node x, there you get the result.
	//
	// we don't really need to do a exact k steps preorder traverse of the
	// denary tree, the idea is to calculate the steps between curr and curr + 1
	// (neighbor nodes in same level), in order to skip some unnecessary moves.
	//
	// Main function
	// Firstly, calculate how many steps curr need to move to curr + 1.
	//
	// 1. if the steps <= k, we know we can move to curr + 1, and narrow down k to k - steps.
	// 2. else if the steps > k, that means the curr + 1 is actually behind the
	// 		target node x in the preorder path, we can't jump to curr + 1. What we
	// 		have to do is to move forward only 1 step (curr * 10 is always next
	// 		preorder node) and repeat the iteration.
	//
	// calSteps function:
	// 1.	how to calculate the steps between curr and curr + 1?
	// 		Here we come up a idea to calculate by level.
	//			Let n1 = curr, n2 = curr + 1.
	// 		n2 is always the next right node beside n1's right most node (who shares
	// 		the same ancestor "curr")(refer to the pic, 2 is right next to 1, 20 is right next to 19, 200 is
	// 		right next to 199).
	// 2.	so, if n2 <= n, what means n1's right most node exists, we can simply add
	// 		the number of nodes from n1 to n2 to steps.
	// 3.	else if n2 > n, what means n (the biggest node) is on the path between n1
	// 		to n2, add (n + 1 - n1) to steps.
	// organize this flow to "steps += Math.min(n + 1, n2) - n1; n1 *= 10; n2 *= 10;"
	public int findKthNumber(int n, int k) {
        int cur = 1;
        k = k-1;
        while(k > 0){
        	//计算当前位数+1后所需要的步骤
            int steps = calSteps(n,cur,cur+1);
            //如果没超过k，则走steps步
            if(steps <= k){
                cur++;
                k-=steps;
            }else{		//如果超过了，那就进入第一个子节点，走1步
                cur*=10;
                k-=1;
            }
        }
        return cur;
    }
    private int calSteps(int n,long n1,long n2){
        int steps = 0;
        while(n1 <= n){
            steps+=Math.min(n+1,n2)-n1;
            n1*=10;
            n2*=10;
        }
        return steps;
    }
}
