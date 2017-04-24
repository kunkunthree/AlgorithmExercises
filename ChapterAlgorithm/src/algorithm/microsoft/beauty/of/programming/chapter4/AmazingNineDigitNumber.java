package algorithm.microsoft.beauty.of.programming.chapter4;
/*
 * 4.10 神奇的9位数：
 * 		能不能找出符合如下条件的9位数：
 * 这个数包括了1~9这9个数字
 * 这个9位数的前n位都能被n整除，若这个数表示为abcdefghi，则ab可以被2整除，abc可以被3整除
 * ......abcdefghi可以被9整除
 */
import java.util.*;
class Digit{
	private int value;
	public int mod;
	public Queue<Integer> queue;
	public Digit(int mod) {
		queue = new LinkedList<Integer>();
		this.mod = mod;
	}
	public void add(int...num) {
		for(int i : num){
			queue.add(i);
		}
	}
	public void remove(int...num){
		for(int i : num){
			queue.remove(i);
		}
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
public class AmazingNineDigitNumber {
	public static int[][] UNIT= {
		{},
		{1,2,3,4,5,6,7,8,9},		//能被1整除的个位
		{2,4,6,8},						//能被2整除的个位
		{1,2,3,4,5,6,7,8,9},		//能被3整除的个位
		{2,4,6,8},						//能被4整除的个位
		{5},									//能被5整除的个位
		{2,4,6,8},						//能被6整除的个位
		{1,2,3,4,5,6,7,8,9},		//能被7整除的个位
		{2,4,6,8},						//能被8整除的个位
		{1,2,3,4,5,6,7,8,9}		//能被9整除的个位
		};								
	public static int getAmazingNineDigitNumber(){
		int result = 0;
		boolean[] filled = new boolean[10];
		for(int i = 0 ; i < filled.length ; i++){
			filled[i] = false;
		}
		Stack<Digit> stackFilled = new Stack<Digit>();
		Stack<Digit> stackUnfilled = new Stack<Digit>();
		Digit digit;
		for(int i = 9 ; i >= 1 ; i--){
			digit = new Digit(i);
			digit.add(UNIT[i]);
			stackUnfilled.push(digit);
		}
		digit =  stackUnfilled.pop();
		int tmpDigit = 1,tmpResult;
		while(stackFilled.size() < 9){
			if(digit.queue.size() == 0){
				stackUnfilled.push(digit);
				result/=10;
				if(stackFilled.size() == 0){
					return 0;
				}
				digit = stackFilled.pop();
				filled[digit.getValue()] = false;
			}else{
				tmpDigit = digit.queue.poll();
				tmpResult = result * 10 + tmpDigit;
				if(tmpResult % digit.mod == 0){
					result = tmpResult;
					filled[tmpDigit] = true;
					digit.setValue(tmpDigit);
					stackFilled.push(digit);
					if(!stackUnfilled.isEmpty()){
						digit = stackUnfilled.pop();
						digit.queue.clear();
						digit.add(UNIT[digit.mod]);
						for(int i = 1 ; i < filled.length ; i++){
							if(filled[i] == true){
								digit.remove(i);
							}
						}
					}
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(getAmazingNineDigitNumber());
	}
}
