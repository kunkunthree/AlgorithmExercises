package algorithm;
import static xie.util.Print.*;
/*
 * 抽象工厂方法模式：
 * 	抽象工厂是应对产品族概念的。比如说，每个汽车公司可能要同时生产轿车，货车，客车，
 * 	那么每一个工厂都要有创建轿车，货车和客车的方法。
 * 应对产品族概念而生，增加新的产品簇很容易，如增加ProductA3可以直接扩展，
 * 但是无法增加新的产品，如增加ProductC。
 */
public class AdbstractFactoryMethodPattern {
	public static void main(String[] args) {
		AbstractAutoFactory2 aaf = new CarFactory2();
		Auto car = aaf.createAuto();
		Wheel carWheel = aaf.createWheel();
	}
}
abstract class Wheel{
	private int size = 0;
}
class CarWheel extends Wheel{
	private int size = 1;
}
class TruckWheel extends Wheel{
	private int size = 2;
}

interface CreateWheelAble{
	public abstract Wheel createWheel();
}
abstract class AbstractAutoFactory2 implements CreateWheelAble{
	public abstract Auto createAuto();
}
class CarFactory2 extends AbstractAutoFactory2{
	@Override
	public Auto createAuto() {
		return new Car();
	}
	@Override
	public Wheel createWheel() {
		return new CarWheel();
	}
}
class TruckFactory2 extends AbstractAutoFactory2{
	@Override
	public Auto createAuto() {
		return new Truck();
	}
	@Override
	public Wheel createWheel() {
		return new TruckWheel();
	}
}