package algorithm.netease.spring2017;
/*
 * 8
 * [编程题] 奇怪的表达式求值:
 * 		常规的表达式求值，我们都会根据计算的优先级来计算。比如\*的优先级就高于+-。
 * 但是小易所生活的世界的表达式规则很简单，从左往右依次计算即可，而且小易所在的世界没有除法，
 * 意味着表达式中没有/，只有(+, - 和 *)。现在给出一个表达式，需要你帮忙计算出小易所在的世界这个表达式的值为多少
输入描述:
		输入为一行字符串，即一个表达式。其中运算符只有-,+,*。参与计算的数字只有0~9.
		保证表达式都是合法的，排列规则如样例所示。
输出描述:
		输出一个数，即表达式的值
输入例子:
3+5*7
输出例子:
56
 */
import java.util.*;
public class StringExpressionEvaluation {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		System.out.println(getResult(s));
	}
	public static int getResult(String s){
		int result = 0;
		int type = 0;
		char[] array = s.toCharArray();
		int length = array.length;
		for(int i = 0 ; i < length ; i++){
			if(array[i] == '+'){
				type = 1;
			}else if(array[i] == '-'){
				type = 2;
			}else if(array[i] == '*'){
				type = 3;
			}else if(array[i] >= '0' && array[i] <= '9'){
				if(type == 0){
					result = array[i] - '0';
				}else{
					switch(type){
						case 1: result+=(array[i] - '0');break;
						case 2: result-=(array[i] - '0');break;
						case 3: result*=(array[i] - '0');break;
					}
				}
			}
		}
		return result;
	}
}
/*
public class StringExpressionEvaluation {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		System.out.println(getReuslt(s));
	}
	public static int getReuslt(String s){
		char[] array = s.toCharArray();
		int length = array.length;
		int result = 0;
		Type type;
		Element tmpElement;
		Stack<Element> stack1 = new Stack<Element>();		//最后存的就是逆波兰表达式
		Stack<Element> stack2 = new Stack<Element>();		//用于暂时存放运算符
		for(int i = 0 ; i< length ; i++){
			if(array[i] >= '0' && array[i] <= '9'){
				stack1.push(new Element(Type.NUMBER, array[i] - '0')); 
			}else if(array[i] == '('){
				stack2.push(new Element(Type.LEFTBRACKET));
			}else if(array[i] == ')'){
				tmpElement = stack2.pop();
				while(tmpElement.type != Type.LEFTBRACKET){
					stack1.push(tmpElement);
					tmpElement = stack2.pop();
				}
			}else if(array[i] == '+' || array[i] == '-' || array[i] == '*'){
				switch(array[i]){
					case '+': type = Type.PLUS;break;
					case '-': type = Type.MINUS;break;
					case '*': type = Type.MULTIPLY;break;
					default:type = Type.UNKNOWN;
				}
				if(stack2.isEmpty()){
					stack2.push(new Element(type));
				}else{
					tmpElement = new Element(type);
					if(tmpElement.priority >= stack2.peek().priority){
						stack2.push(tmpElement);
					}else{
						while(tmpElement.priority < stack2.peek().priority || !stack2.isEmpty()){
							stack1.push(stack2.pop());
						}
						stack2.push(tmpElement);
					}
				}
			}
		}
		while(!stack2.isEmpty()){
			stack1.push(stack2.pop());
		}
		result = calculate(stack1);
		return result;
	}
	public static int calculate(Stack<Element> stack){
		int result = 0;
		Stack<Integer> stackData = new Stack<Integer>();
		int length = stack.size();
		Element tmpElement;
		for(int i = 0 ; i < length ; i++){
			tmpElement = stack.get(i);
			if(tmpElement.type == Type.NUMBER){
				stackData.push(tmpElement.value);
			}else{
				if (tmpElement.type == Type.MINUS) {
					result = stackData.pop() - stackData.pop();
				} else if (tmpElement.type == Type.PLUS) {
					result = stackData.pop() + stackData.pop();
				} else if (tmpElement.type == Type.MULTIPLY) {
					result = stackData.pop() * stackData.pop();
				}
				stackData.push(result);
			}
		}
		result = stackData.pop();
		return result;
	}
}
 */
/*
enum Type{
	LEFTBRACKET,
	RIGHTBRACKET,
	NUMBER,
	PLUS,
	MINUS,
	MULTIPLY,
	UNKNOWN;
}
class Element{
	int priority;
	Type type;
	int value;
	public Element(Type type) {
		this.type = type;
		if(type == Type.MINUS){
			priority = Type.PLUS.ordinal();
		}else{
			priority = type.ordinal();
		}
	}
	public Element(Type type,int value) {
		this.type = type;
		this.value = value;
		if(type == Type.MINUS){
			priority = Type.PLUS.ordinal();
		}else{
			priority = type.ordinal();
		}
	}
}
*/