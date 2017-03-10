package algorithm;
import static xie.util.Print.*;
/*
 * 简单工厂模式：
 * 	简单工厂模式的工厂类一般是使用静态方法，通过接收的参数的不同来返回不同的对象实例。
 * 简单工厂 ： 用来生产同一等级结构中的任意产品。（对于增加新的产品，无能为力）
 */
public class SimpleFactoryPattern {
	public static void main(String[] args) {
		Auto car = AutoFactry.createAuto(1);
		Auto truck = AutoFactry.createAuto(2);
	}
}
abstract class Auto{
	private int type = 0;
}
class Car extends Auto{
	private int type = 1;
}
class Truck extends Auto{
	private int type = 2;
}
class AutoFactry{
	public static Auto createAuto(int type){		//传递不同的参数，获得不同的产品
		switch(type){
			case 1: return new Car();
			case 2: return new Truck();
			default: return null;
		}
	}
}