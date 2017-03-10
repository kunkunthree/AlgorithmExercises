package algorithm;
import java.io.InputStreamReader;
/*
 * 构造队列：
 * 		小明同学把1到n这n个数字按照一定的顺序放入了一个队列Q中。现在他对队列Q执行了如下程序：
while(!Q.empty())              //队列不空，执行循环
{
    int x=Q.front();            //取出当前队头的值x
    Q.pop();                 //弹出当前队头
    Q.push(x);               //把x放入队尾
    x = Q.front();              //取出这时候队头的值
    printf("%d\n",x);          //输出x
    Q.pop();                 //弹出这时候的队头
}
做取出队头的值操作的时候，并不弹出当前队头。
小明同学发现，这段程序恰好按顺序输出了1,2,3,...,n。
现在小明想让你构造出原始的队列，你能做到吗？[注：原题样例第三行5有错，应该为3，以下已修正]
输入描述:
		第一行一个整数T（T ≤ 100）表示数据组数，每组数据输入一个数n（1 ≤ n ≤ 100000），
		输入的所有n之和不超过200000。
输出描述:
		对于每组数据，输出一行，表示原始的队列。数字之间用一个空格隔开，不要在行末输出多余的空格.
输入例子:
		4
		1
		2
		3
		10
输出例子:
		1
		2 1
		2 1 3
		8 1 6 2 10 3 7 4 9 5

 */
import java.util.*;
public class ConstructQueue {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int[] array = new int[num];
		for(int i = 0 ; i < num ; i++){
			array[i] = in.nextInt();
		}
		for(int i = 0 ; i < num ; i++){
			printOriginalQueue2(array[i]);
		}
	}
	public static void printOriginalQueue2(int n){
		int[] array = new int[n+1];
		int count = 1;
		int pos = 1;
		boolean flag = false;
		while(count <= n){
			if(flag == true && array[pos] == 0){
				array[pos] = count;
				count++;
			}
			pos = pos% n + 1;
			if(array[pos] == 0){
				flag = !flag;
			}
		}
		for(int i = 1 ; i <= n ; i++){
			System.out.print(array[i]);
			if(i != n)System.out.print(" ");
		}
		System.out.println();
	}
	public static void printOriginalQueue(int n){
		if(n < 1 || n > 100000){
			System.err.println("Error number: " + n);
			return;
		}
		int[] array = new int[n+1];
		array[0] = 0;
		int tmp = n/2;
		int step = 2;
		int index = 1;
		while(tmp > 0){
			for(int i = 1 ; i <= tmp ; i++){
				System.out.println((step*i) + " " + index);
				array[step*i] = index++;
			}
			tmp/=2;
		}
		array[1] = n;
		System.out.println(Arrays.toString(array));
	}
	/*
	 * 反向操作即可
	public static LinkedList<Integer> func(int n){
        LinkedList<Integer> help=new LinkedList<Integer>();
        for(int i=n;i>=1;i--){
            help.addFirst(i);
            help.addFirst(help.removeLast());
        }
        return help;
    }
    public static void main(String[] args){
        int t;
        Scanner scan = new Scanner(System.in);
        t=scan.nextInt();
        int n;
        LinkedList<Integer> res;
        while(t-->0){
            n=scan.nextInt();
            res=func(n);
            for(int i=0;i<n-1;i++){
                System.out.print(res.removeFirst()+" ");
            }
            System.out.println(res.removeFirst());
        }
    }
    */
}
