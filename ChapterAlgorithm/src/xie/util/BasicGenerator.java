package xie.util;
/*
 * 这个类提供了一个基本实现，用以生成某个类的对象，这个类必须具备两个特点：
 * 1）它必须声明为public。（因为BasicGenerator与要处理的类在不同的包中，所以该类必须声明为public）
 * 2）它必须具备默认的构造器（无参构造器）。
 * 	泛型化的create方法允许执行BasicGenerator.create(MyType.class)，
 * 而不必执行麻烦的new BasicGenerator<MyType>(MyType.class)。
 */
public class BasicGenerator<T> implements Generator<T> {
	private Class<T> type;
	public BasicGenerator(Class<T> type){
		this.type = type;
	}
	public T next(){
		try{
			return type.newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public static <T> Generator<T> create(Class<T> type){
		return new BasicGenerator<T>(type);
	}
}
