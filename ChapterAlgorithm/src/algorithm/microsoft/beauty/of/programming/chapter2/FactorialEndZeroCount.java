package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 2.2	不要被阶乘吓倒：
 * 1) 给定一个整数N，那么N的阶乘N!末尾有多少个0呢？例如：N=10，N!=3 628 800，N!的末尾有两个0。
 * 2) 求N!的二进制表示中最低位1的位置。 
 * 
 *  方法一，求出N!，对于第一个题目，记录其0的个数即可，时间复杂度=O(N)+O(N!中0的个数)；
 *  对于第二个题目，记录二进制中最低位1右边0的个数即可，时间复杂度=O(N)+O(N!中最低位右边0的个数)。
 *  这个方法时间复杂度问题不大，不过空间有问题，因为N!增长太快了，N不用很大，N!很容易就溢出了。
 *  
 * 方法二，不求N!，从N!的因子入手，第一个题目是计算N!中，因子5的个数，因为每个0都是一个10，10=2*5，
 * 因子2明显远远超过5，因此10的个数（也就是0的个数）与5的个数是相同的；第二个题目是计算N!中因子2的个数，
 * 因子2的个数就是二进制表示中，最低位1后面0的个数，即如果N!有k个因子2，那么最低位1后面就是k个0。
 * 
 * 方法三：
 * 	Z = [N/5]+[N/(5^2)]+[N/(5^3)]+.....
 * 先算5的指数至少为1的倍数的个数，再算5的指数至少为2的倍数的个数....
 */
public class FactorialEndZeroCount {
	public static void main(String[] args) {
		System.out.println(g1(2));
	}
	public static int f1(int x){
		return 0;
	}
	//计算因式分解中5的指数
	public static int f2(int x){
		int ret = 0;
		for(int i = 0 ; i <= x ; i++){
			int tmp = i;
			while(tmp%5 == 0){
				tmp/=5;
				ret++;
			}
		}
		return ret;
	}
	//
	public static int f3(int x){
		int ret = 0;
		while(x != 0){
			ret+=x/5;
			x/=5;
		}
		return ret;
	}
	
	public static int g1(int x){
		int ret = 0;
		while(x != 0){
			x>>=1;
			ret+=x;
		}
		ret++;
		return ret;
	}
}
