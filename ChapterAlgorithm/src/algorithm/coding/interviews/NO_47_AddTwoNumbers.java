package algorithm.coding.interviews;

public class NO_47_AddTwoNumbers {
	public static void main(String[] args) {
		int i = 1;
		while(true){
			
		}
	}
	public static int add(int num1,int num2){
		int sum,carry;
		do{
			sum = num1 ^ num2;
			carry = (num1 & num2)<<1;
			
			num1 = sum;
			num2 = carry;
		}while(num2 != 0);
		return sum;
	}
	public static String getFullBinaryString(int n){
		StringBuilder sb = new StringBuilder();
		int mask = 1<<31;
		while(mask != 0){
			if((n & mask) == 0){
				sb.append(0);
			}else{
				sb.append(1);
			}
			mask>>>=1;
		}
		return sb.toString();
	}
}
