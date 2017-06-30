package algorithm.coding.interviews;

public class NO_46_Accumulate {
	public static void main(String[] args) {
		NO_46_Accumulate test =  new NO_46_Accumulate();
		int n = 5;
		System.out.println(f(100));
	}
	public static int getSum(int n){
		Temp[] a = new Temp[n];
		return Temp.SUM;
	}
	public static int f(int n) {
        int t = 0;
        boolean b = (n > 0) && ((t = f(n - 1)) >0);
        return n + t;
    }
}
class Temp{
	public static  int SUM = 0;
	public static int COUNT = 1;
	public Temp(){
		COUNT++;
		SUM+=COUNT;
	}
}
