package algorithm.microsoft.beauty.of.programming.chapter2;

import java.util.Arrays;

/*
 * 2.20	程序理解和时间分析：
 * 
 */
public class ProgramComprehension {
	public static void main(String[] args) {
		int[] rg = new int[]{
				2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 
				19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
//		int count = 0;
		for(long i = 1 ; i < Long.MAX_VALUE ; i++){
			int hit = 0 ; 
			int hit1 = -1;
			int hit2 = -1;
			for(int j = 0 ; j < rg.length && hit <= 2 ; j++){
				if( i % rg[j] != 0){
					hit++;
					if(hit == 1){
						hit1 = j;
					}else if(hit == 2){
						hit2 = j;
					}else{
						break;
					}
				}
			}
			if(hit == 2 && hit1+1 == hit2){
				System.out.println("found : " + i);
//				count++;
			}
//			if(count > 10){
//				break;
//			}
		}
	}
}
