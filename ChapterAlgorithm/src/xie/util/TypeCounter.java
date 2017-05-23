package xie.util;
import static xie.util.Print.*;
import java.util.*;
/*
 * 递归计算：
 * 
 * isAssignableFrom()方法是从类继承的角度去判断，instanceof()方法是从实例继承的角度去判断。
 * isAssignableFrom()方法是判断是否为某个类的父类，instanceof()方法是判断是否某个类的子类。
 * 
 * Class.isAssignableFrom()是用来判断一个类Class1和另一个类Class2是否相同或是另一个类的父类或接口。  
 * 格式为：       Class1.isAssignableFrom(Class2) 
 * 调用者和参数都是java.lang.Class类型。   
 * 
 * Class.instanceof()是用来判断一个对象实例是否是一个类或接口的或其子类子接口的实例。  
 * 格式是：		obj instanceof TypeName  
 *  第一个参数是对象实例名，第二个参数是具体的类名或接口名，例如   String,InputStream。其返回值为boolean。
 * 
 */
public class TypeCounter extends HashMap<Class<?>,Integer>{
	private Class<?> baseType;
	public TypeCounter(Class<?> baseType){
		this.baseType = baseType;
	}
	public void count(Object obj){
		Class<?> type = obj.getClass();
		if(!baseType.isAssignableFrom(type)){
			throw new RuntimeException(obj + " incorrect type " + type + ", should be type or subtype of" + baseType);
		}
		countClass(type);
	}
	private void countClass(Class<?>type){
		Integer quantity = get(type);
		put(type,quantity == null?1:quantity+1);
		Class<?>superClass = type.getSuperclass();
		if(superClass != null && baseType.isAssignableFrom(superClass)){
			countClass(superClass);
		}
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("{");
		for(Map.Entry<Class<?>, Integer> pair:entrySet()){
			result.append(pair.getKey().getSimpleName());
			result.append("=");
			result.append(pair.getValue());
			result.append(", ");
		}
		result.delete(result.length()-2, result.length());
		return super.toString();
	}
}
