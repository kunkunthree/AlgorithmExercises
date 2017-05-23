package algorithm.leetcode.algorithm;
/*
 * medium
 * 386. Lexicographical Numbers
 *  Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000. 
 */
import java.util.*;
public class NO386_LexicographicalNumbers {
	//方法1：
	//递归实现
	public static List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        int count = 0,tmp = n;
        while(tmp > 0){
            tmp/=10;
            count++;
        }
        for(int i = 1 ; i <= 9 ; i++){
            helper(list,n,i,count-1);
        }
        return list;
    }
    public static void helper(List<Integer> list,int n,int num,int count){
        if(num > 0 && num <= n){
            list.add(num);
        }
        if(count == 0){
            return;
        }
        for(int i = 0 ; i <= 9 ; i++){
            helper(list,n,num*10+i,count-1);
        }
    }
    //方法2：
    //迭代实现
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> list = new ArrayList<>();
        int cur = 1;
        for(int i = 1 ; i <= n ; i++){
            list.add(cur);
            if(cur*10 <= n){	//只要小于等于n，后面加0
                cur*=10;
            }else if(cur % 10 != 9 && cur+1 <= n){ 	//尾数不为9且下一个数小于等于n，尾数加1，直到尾数为9
                cur++;
            }else {
                while((cur/10) % 10 == 9){		//如果尾数为9，下一个字典序则为尾部第一个不为9的数字加1
                    cur /= 10;
                }
                cur = cur/10 + 1;		//如果尾数不为9，则尾数加1
            }
        }
        return list;
    }
    //方法3：
    //迭代，更简单写法
    public List<Integer> lexicalOrder3(int n) {
        List<Integer> list = new ArrayList<>();
        int cur = 1;
        for (int i = 0; i < n; i++) {
            list.add(cur);
            if (cur * 10 <= n) {	//只要小于等于n，后面加0
                cur *= 10;
            } else {
                if (cur >= n) 		//如果大于等于n，则除去1位
                    cur /= 10;
                cur += 1;		//加1
                while (cur % 10 == 0)		//除去尾部所有连续的0
                    cur /= 10;
            }
        }
        return list;
    }
}
