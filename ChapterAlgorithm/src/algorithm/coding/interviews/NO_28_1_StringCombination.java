package algorithm.coding.interviews;
/*
 * 所有字符的组合
 */
public class NO_28_1_StringCombination {
	public static void main(String[] args) {
		String s = "abc";
		combination(s);
		System.out.println(Integer.MAX_VALUE+1 ==Integer.MIN_VALUE);
	}
	public static void combination(String s){
		helper(new StringBuilder(),s,0,s.length());
	}
	private static void helper(StringBuilder builder,String s,int start,int end){
		System.out.println(builder.toString());
		for(int i = start ; i < end ; i++){
			builder.append(s.charAt(i));
			helper(builder,s,i+1,end);
			builder.deleteCharAt(builder.length()-1);
		}
	}
}
