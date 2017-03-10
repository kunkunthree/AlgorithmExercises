package algorithm.microsoft.beauty.of.programming.chapter2;
/*
 * 2.8	找符合条件的整数：
 * 		任意给定一个正整数N，求最小的正整数M（M>1），使得N*M的十进制表示形式里只含有0和1.
 * 
 */
import java.util.*;
public class GetAProperPositiveInteger {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n =  in.nextInt();
		System.out.println(getProperPositiveInteger(n));
	}
	public static String getProperPositiveInteger(int N){
		ArrayList<Integer>[] bigInt = new ArrayList[N];
		for(int i = 0 ; i < N ; i++){
			bigInt[i] = new ArrayList<Integer>();
			bigInt[i].clear();
		}
		bigInt[1].add(0);
		int noUpdate = 0;
		for(int i = 1,j = 10%N ; ; i++ , j = (j*10)%N){
			boolean flag = false;
			if(bigInt[j].size() == 0){
				System.out.println("bigInt["+j+"].size() == 0 , i = " + i);
				bigInt[j].clear();
				bigInt[j].add(i);
			}
			for(int k = 1 ; k < N ; k++){
				if(bigInt[k].size() > 0 
						&& i > bigInt[k].get(bigInt[k].size()-1) 
						&& bigInt[(k+j) % N].size() == 0){
//					bigInt[(k+j)%N].add(10^i + bigInt[k].get(0));
					flag = true;
					for(int t = 0 ; t < bigInt[k].size() ; t++){
						bigInt[(k+j)%N].add(bigInt[k].get(t));
					}
					bigInt[(k+j)%N].add(i);
					System.out.println(k + "    " + j + "     " + (k+j)%N + "  " + bigInt[(k+j)%N].toString());
				}
			}
			if(flag == false){
				noUpdate++;
			}
			if(noUpdate == N || bigInt[0].size() > 0){
				System.out.println("noUpdate: " + noUpdate + "  , bigInt[0].size(): " + bigInt[0].size());
				break;
			}
		}
		if(bigInt[0].size() == 0){
			return "Not exists";
		}else{
			String result = "";
//			System.out.println(bigInt[0].toString());
			for(int t = bigInt[0].size()-1,index = bigInt[0].get(t); t >= 0 ; t--){
				index--;
				result+='1';
				int pos = -1;
				if(t >= 1){
					pos = bigInt[0].get(t-1);
				}
				while(index > pos && index >=0){
					result+='0';
					index--;
				}
			}
			return result;
		}
	}
}
