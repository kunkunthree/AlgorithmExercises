package algorithm;
/*
 * 洗牌：
 * 		洗牌在生活中十分常见，现在需要写一个程序模拟洗牌的过程。 现在需要洗2n张牌，
 * 从上到下依次是第1张，第2张，第3张一直到第2n张。首先，我们把这2n张牌分成两堆，
 * 左手拿着第1张到第n张（上半堆），右手拿着第n+1张到第2n张（下半堆）。
 * 接着就开始洗牌的过程，先放下右手的最后一张牌，再放下左手的最后一张牌，
 * 接着放下右手的倒数第二张牌，再放下左手的倒数第二张牌，直到最后放下左手的第一张牌。
 * 接着把牌合并起来就可以了。 例如有6张牌，最开始牌的序列是1,2,3,4,5,6。首先分成两组，
 * 左手拿着1,2,3；右手拿着4,5,6。在洗牌过程中按顺序放下了6,3,5,2,4,1。把这六张牌再次合成一组牌之后，
 * 我们按照从上往下的顺序看这组牌，就变成了序列1,4,2,5,3,6。 现在给出一个原始牌组，
 * 请输出这副牌洗牌k次之后从上往下的序列。
输入描述:
		第一行一个数T(T ≤ 100)，表示数据组数。对于每组数据，第一行两个数n,k(1 ≤ n,k ≤ 100)，
		接下来一行有2n个数a1,a2,...,a2n(1 ≤ ai ≤ 1000000000)。表示原始牌组从上到下的序列。
输出描述:
		对于每组数据，输出一行，最终的序列。数字之间用空格隔开，不要在行末输出多余的空格。
输入例子:
3
3 1
1 2 3 4 5 6
3 2
1 2 3 4 5 6
2 2
1 1 1 1
输出例子:
		1 4 2 5 3 6
		1 5 4 3 2 6
		1 1 1 1

 */
import java.io.*;
import java.util.*;
public class Shuffle {
	public static void main(String[] args) {
//		Random rand = new Random();
//		int groupNum = rand.nextInt(99)+1;
//		System.out.println("初始化");
//		System.out.println(groupNum);
		Scanner in = new Scanner(System.in);
		int groupNum = Integer.parseInt(in.nextLine());
		int[][] groupInfo = new int[groupNum][2];		//groupInfo[i][0]表示第i组的n值，groupInfo[i][1]表示第i组的洗牌次数
		long[][] groupData = new long [groupNum][];
		for(int i = 0 ; i < groupNum ; i++){
//			groupInfo[i][0] = rand.nextInt(99)+1;
//			groupInfo[i][1] = rand.nextInt(99)+1;
//			System.out.println(groupInfo[i][0] + " " + groupInfo[i][1]);
//			groupData[i] = new long[2 * groupInfo[i][0] + 1];
//			for(int j = 1 ; j < 2 * groupInfo[i][0] + 1 ; j++){
//				long tmp = nextLong(rand,999999999)+1;
//				groupData[i][j] = tmp;
//				System.out.print(groupData[i][j] + " ");
//			}
//			System.out.println();
			String[] s = in.nextLine().split(" ");
			groupInfo[i][0] = Integer.parseInt(s[0]);
			groupInfo[i][1] = Integer.parseInt(s[1]);
			s = in.nextLine().split(" ");
			groupData[i] = new long[s.length+1];
			groupData[i][0] = Long.MAX_VALUE;			//设置data[i][0]为占位，不存数据
			for(int j = 1 ; j < s.length+1 ; j++){
				groupData[i][j] = Integer.parseInt(s[j-1]);
			}
		}
		for(int i = 0 ; i < groupNum ; i++){
			shuffleWithTime(groupData[i],groupInfo[i][1]);
		}
	}
	public static void shuffleWithTime(long[] array,int time){
		for(int i = 0 ; i < time ; i++){
			shuffle(array);
		}
		for(int i = 1 ; i < array.length ; i++){
			System.out.print(array[i]);
			if(i != array.length-1){
				System.out.print(" ");
			}
		}
		System.out.println();
	}
	public static void shuffle(long[] array){
		if(array.length % 2 == 0)return;
		int n = array.length/2;
		long[] copy = new long[array.length];
		for(int i = 0 ; i < copy.length ;i++){
			copy[i] = array[i];
		}
		for(int i = 1 ; i <= n  ; i++){
			array[2 * i -1] = copy[2*n - i + 1];
			array[2 * i] = copy[n - i + 1];
		}
	}
	public static   long nextLong(Random rng, long n) {  
	       // error checking and 2^x checking removed for simplicity.  
	       long bits, val;  
	       do {  
	          bits = (rng.nextLong() << 1) >>> 1;  
	          val = bits % n;  
	       } while (bits-val+(n-1) < 0L);  
	       return val;  
	    }  
}
