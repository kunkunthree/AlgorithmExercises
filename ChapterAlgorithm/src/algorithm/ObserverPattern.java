package algorithm;
import static xie.util.Print.*;
import java.util.*;
/*
 * 观察者模式（监听者模式）：
 * 		观察者模式有很多种形式，比较直观的是“注册、通知、撤销注册”的形式：
 * 1）观察者将自己注册到被观察者对象中，被观察对象将观察者存放在一个容器中
 * 		观察者们需要实现某一个接口或继承自某个抽象类，便于被观察者统一对待且可以调用某个方法，以便做出响应
 * 2）被观察对象发生了某种变化（如按钮被单击、商品价格发生改变等），从容器中得到所有注册过的观察者，
 * 将变化通知观察者。在代码中，需要做的就是遍历容器中的观察者，调用它们的回调方法。
 * 3）观察者告诉被观察者要撤销观察，被观察者从容器中将观察者去除。
 */
public class ObserverPattern {
	 public static void main(String[] args) {
		Product p = new Product("《java核心技术》", 103.00);
		Observer ob1 = new WebObserver();
		Observer ob2 = new MailObserver();
		p.addObserver(ob1);
		p.addObserver(ob2);
		println("===第一次价格改动===");
		p.setPrice(80);
		println("===第二次价格改动===");
		p.setPrice(100);
	}
}
interface Observer{										//观察者接口
	public void update(Product p);				//价格修改的接口方法
	public void unreg(Product p);				//撤销注册方法
}
class WebObserver implements Observer{
	public void update(Product p) {
		println("更新页面价格： " + p.getName() + ":" + p.getPrice());
	}
	@Override
	public void unreg(Product p) {
		p.getObservers().remove(this);		//去除本观察者
	}
}
class MailObserver implements Observer{
	public void update(Product p) {
		println("为所有会员发送价格变化信息：" + p.getName() + ":" + p.getPrice());
	};
	@Override
	public void unreg(Product p) {
		p.getObservers().remove(this);		//去除本观察者
	}
}
class Product{
	private double price;
	private String name;
	private HashSet<Observer> observers;
	public Product(String name , double price) {
		this.price = price;
		this.name  = name;
		observers = new HashSet<Observer>();
	}
	public void addObserver(Observer ob){
		observers.add(ob);
	}
	public void notifyObserver(){
		for(Observer ob: observers){
			ob.update(this);			//回调所有观察者的观察方法
		}
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		//当价格变动时通知观察者
		notifyObserver();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setObservers(HashSet<Observer> observers) {
		this.observers = observers;
	}
	public HashSet<Observer> getObservers() {
		return observers;
	}
}