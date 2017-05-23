package xie.util;
import java.util.*;
/*
 * 集合
 */
public class Sets {
	//并运算	A∪B
	public static <T> Set<T> union(Set<T> a,Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.addAll(b);
		return result;
	}
	//交运算	A∩B
	public static <T> Set<T> intersection(Set<T> a,Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.retainAll(b);
		return result;
	}
	//减运算运算	A-B
	public static <T> Set<T> difference(Set<T> a,Set<T> b){
		Set<T> result = new HashSet<T>(a);
		result.removeAll(b);
		return result;
	}
	//异或运算	（A∪B）－（A∩B）
		public static <T> Set<T> complement(Set<T> a,Set<T> b){
			return difference(union(a,b),intersection(a,b));
		}
}
