package algorithm.leetcode.algorithm;
/*
 * medium
 * 649. Dota2 Senate 
 *  In the world of Dota2, there are two parties: the Radiant and the Dire.

The Dota2 senate consists of senators coming from two parties. 
Now the senate wants to make a decision about a change in the Dota2 game. 
The voting for this change is a round-based procedure. 
In each round, each senator can exercise one of the two rights:
    1.	Ban one senator's right:
    		A senator can make another senator lose all his rights in this and all the following rounds.
    2.	Announce the victory:
    		If this senator found the senators who still have rights to vote are all from the same party, 
    		he can announce the victory and make the decision about the change in the game.

Given a string representing each senator's party belonging.
 The character 'R' and 'D' represent the Radiant party and the Dire party respectively.
  Then if there are n senators, the size of the given string will be n.

The round-based procedure starts from the first senator to the last senator in the given order. 
This procedure will last until the end of voting. 
All the senators who have lost their rights will be skipped during the procedure.

Suppose every senator is smart enough and will play the best strategy for his own party,
 you need to predict which party will finally announce the victory and make the change in the Dota2 game. 
 The output should be Radiant or Dire.

Example 1:
Input: "RD"
Output: "Radiant"
Explanation: The first senator comes from Radiant and he can just ban the next senator's right in the round 1. 
And the second senator can't exercise any rights any more since his right has been banned. 
And in the round 2, the first senator can just announce the victory since he is the only guy in the senate 
who can vote.

Example 2:

Input: "RDD"
Output: "Dire"
Explanation: 
The first senator comes from Radiant and he can just ban the next senator's right in the round 1. 
And the second senator can't exercise any rights anymore since his right has been banned. 
And the third senator comes from Dire and he can ban the first senator's right in the round 1. 
And in the round 2, the third senator can just announce the victory
 since he is the only guy in the senate who can vote.

Note:
    The length of the given string will in the range [1, 10,000].

 */
import java.util.*;
public class NO649_Dota2Senate {
	public static void main(String[] args) {
		String senate = "RD";
		StringBuilder sb = new StringBuilder();
		System.out.println(predictPartyVictory(senate));
	}
	//方法1：
	//O(n)space
	//贪婪算法：
	//遇到其中一个阵营，就把他下一个最近的阵营的坐标所在的位置禁掉
	//用两个队列，把所有坐标遍历一边，并放入各自对应的队列
	//然后分别对两个队列出列比较那个队列的下标小，下标小的可以再次加入其原来的队列，不过要增加n，
	//表示进入下一轮，直到其中一个队列出现空位置
	public static String predictPartyVictory(String senate) {
        Queue<Integer> queueD = new LinkedList<>(), queueR = new LinkedList<>();
        int n = senate.length();
        for(int i = 0 ; i < n ; i++){
            if(senate.charAt(i) == 'D'){
                queueD.offer(i);
            }else if(senate.charAt(i) == 'R'){
                queueR.offer(i);
            }
        }
        while(!queueD.isEmpty() && !queueR.isEmpty()){
            int indexD = queueD.poll();
            int indexR = queueR.poll();
            if(indexD < indexR){
                queueD.offer(indexD+n);
            }else{
                queueR.offer(indexR+n);
            }
        }
        return queueD.isEmpty() ? "Radiant" : "Dire";
    }
	//方法2：
	//O(n)space
	//用两个数组，分别记录每个阵营当前需要剔除的个数和下一轮仍然剩余的个数
	//用一个StringBuilder记录剩下的字符串，以便下一次遍历
	//每一轮遍历：
	//得到当前字符阵营的index，
	//如果eliminated[index] > 0，说明前面有另一个阵营把当前禁掉，则eliminated[index]减一
	//如果eliminated[index] <=  0，则说明前面另一个阵营把当前阵营禁掉的个数已经抵消，则该字符会留到下一轮
	//遍历完：
	//next数组表示下一轮剩下的个数，如果都大于0，则进入下一轮遍历，否则剩下的大于0的就是胜利的阵营
	public String predictPartyVictory2(String senate) {
        int[] eliminated = new int[2], next = new int[2];
        while(true){
            StringBuilder sb = new StringBuilder();
            int n = senate.length();
            for(int i = 0 ; i < n ; i++){
                int index = getIndex(senate.charAt(i));
                if(eliminated[index] > 0){
                    eliminated[index]--;
                }else{
                    next[index]++;
                    eliminated[index^1]++;
                    sb.append(senate.charAt(i));
                }
            }
            if(next[0] > 0 && next[1] > 0){
                next[0] = 0;
                next[1] = 0;
                senate = sb.toString();
            }else{
                return next[0] > 0 ? "Radiant" : "Dire";
            }
        }
    }
    private int getIndex(char c){
        if(c == 'R'){
            return 0;
        }else if(c == 'D'){
            return 1;
        }else {
            return -1;
        }
    }
}
