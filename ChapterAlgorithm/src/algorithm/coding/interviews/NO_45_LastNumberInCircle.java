package algorithm.coding.interviews;
/*
 * 45 圆圈中最后剩下的数字：
 * 	0,1,2,.....,n-1这n个数排成一个圆圈，从数字0开始每次从这个圈里删除第m个数字，求剩下的最后一个数字
 */
public class NO_45_LastNumberInCircle {
	public static void main(String[] args) {
		int n = 5;
		int m = 3;
		System.out.println(getLastNumberInCircle(n, m));
	}
	//设f(n,m)为每次在n个数字0,1,2,...n-1中每次删除第m个数后最后剩下的数字
	//假设n = 1,f(n,m) = 0;
	//假设n > 1，f(n,m)删除第m个数后，剩下0,1,2,....m-2,m,....n-1
	//对于f(n-1,m)的0,1,2,3,....,n-2，这n-1个数能映射到 m,m+1,....,n-1,0,1,2,....m-2 的这n-1个数
	//映射公式为g(x) = (x+m)%n
	//那么只要从最后剩下的1个数0，映射到f(n,m)n个数中的某个位置就可以得到最后的数字
	//可得到推导公式f(n,m) =  {0,									i = 1;
	//													(f(n-1,m) + m)%n,	i > 1;
	
	//迭代实现：
	public static int getLastNumberInCircle(int n,int m){
		if(n < 0 || m < 0){
			return -1;
		}
		int last = 0;
		for(int i = 2 ; i <= n ; i++){
			last = (last + m) % i;
		}
		return last;
	}
	//递归实现：
	public static int getLastNumberInCircle2(int n,int m){
		if(n < 0 || m < 0){
			return -1;
		}
		if(n == 1){
			return 0;
		}
		return (getLastNumberInCircle(n-1, m) + m)%n;
	}
}
