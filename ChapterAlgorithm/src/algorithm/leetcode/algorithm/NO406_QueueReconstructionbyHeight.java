package algorithm.leetcode.algorithm;
/*
 * medium
 * 406. Queue Reconstruction by Height
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 
 * where h is the height of the person and k is the number of people in front of this person 
 * who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

 */
import java.util.*;
public class NO406_QueueReconstructionbyHeight {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		
	}
	//方法1：
	//先对数组排序，身高从高到低排，如果身高相等，则前面比自己高的人少的排前面。
	//然后进行插入排序，从排好序的数组的前面开始，这个人前面应该有多少个比自己高的人，
	//则排在第几个人的后面，后面的所有人往后挪
	//原理：
	//晚排的人都不会比前面的人高，所以插入后不会对后面的人造成任何影响
	//相同身高的人，前面的人多的不会排在前面人少的前面，而且前面人少的比前面人多的要早排，
	//所以相同身高的人，前面人多的插入位置一定再对前面人少的后面，不会造成影响
	public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,new Comparator<int[]>(){
           public int compare(int[] a,int[] b){
               if(a[0] == b[0]){
                   return a[1]-b[1];
               }
               return b[0]-a[0];
           } 
        });
        List<int[]> list = new ArrayList<>();
        for(int[] p : people){
            list.add(p[1],p);
        }
        int[][] result = new int[people.length][2];
        for(int i = 0 ; i < people.length ; i++){
            result[i][0] = list.get(i)[0];
            result[i][1] = list.get(i)[1];
        }
        return result;
    }
}
