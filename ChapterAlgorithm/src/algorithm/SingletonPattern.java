package algorithm;
import static xie.util.Print.*;
/*
 * 单例模式：
 * 1、私有的构造方法。保证外部无法创建类实例。
 * 2、私有的静态的类型引用。因为静态就可以保证只有一个变量引用。
 * 3、提供获取实例的方法，方法名一般为getInstance()。
 * 有两种实现形式：
 * 	懒汉式和恶汉式。
 * 懒汉式：在用第一次获取实例的时候才创建对象。
 * 恶汉式：在类加载时就已经创建对象
 */
public class SingletonPattern {
	public static void main(String[] args) {
		ConnectionPoolA cp1 = ConnectionPoolA.getInstance();
		ConnectionPoolA cp2 = ConnectionPoolA.getInstance();
		println(cp1 == cp2);
	}
}
//恶汉式单例模式：	优点：实现简单；缺点：在不需要的时候，白创造了对象，造成资源浪费
class ConnectionPoolA{
	private static ConnectionPoolA cp = new ConnectionPoolA();
	public static ConnectionPoolA getInstance(){
		return cp;
	}
}
//懒汉式单例模式：	优点：需要对象时候才创建	缺点：线程不安全
class ConnectionPoolB{
	private static ConnectionPoolB cp;
	public static synchronized ConnectionPoolB getInstance(){
		if(cp == null){
			cp  = new ConnectionPoolB();
		}
		return cp;
	}
}

