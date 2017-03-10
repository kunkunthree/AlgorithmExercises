package algorithm;
import static xie.util.Print.*;
/*
 * 工厂方法模式：
 * 	工厂方法是针对每一种产品提供一个工厂类。通过不同的工厂实例来创建不同的产品实例。
 * 	在同一等级结构中，支持增加任意产品。
 * ★工厂模式中，重要的是工厂类，而不是产品类。产品类可以是多种形式，多层继承或者是单个类都是可以的。
 * 	但要明确的，工厂模式的接口只会返回一种类型的实例，这是在设计产品类的时候需要注意的，
 * 	最好是有父类或者共同实现的接口。
 * ★使用工厂模式，返回的实例一定是工厂创建的，而不是从其他对象中获取的。
 * ★工厂模式返回的实例可以不是新创建的，返回由工厂创建好的实例也是可以的。
 * 
 * 工厂模式 ：用来生产同一等级结构中的固定产品。（支持增加任意产品）   
 */
public class FactoryMethodPattern {
	public static void main(String[] args) {
		AbstractAutoFactory factory1 = new CarFactory();
		AbstractAutoFactory factory2 = new TruckFactory();
		
		Auto car = factory1.createAuto();
		Auto truck = factory2.createAuto();
	}
}
abstract class AbstractAutoFactory{
	public abstract Auto createAuto();
}
class CarFactory extends AbstractAutoFactory{
	@Override
	public Auto createAuto() {
		return new Car();
	}
}
class TruckFactory extends AbstractAutoFactory{
	@Override
	public Auto createAuto() {
		return new Truck();
	}
}
