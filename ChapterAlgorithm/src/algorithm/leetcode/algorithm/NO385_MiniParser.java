package algorithm.leetcode.algorithm;
/*
 * medium
 * 385. Mini Parser
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Note: You may assume that the string is well-formed:

    String is non-empty.
    String does not contain white spaces.
    String contains only digits 0-9, [, - ,, ].

Example 1:
Given s = "324",
You should return a NestedInteger object which contains a single integer 324.

Example 2:
Given s = "[123,[456,[789]]]",
Return a NestedInteger object containing a nested list with 2 elements:

1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.

 */
import java.util.*;
public class NO385_MiniParser {
	public static void main(String[] args) {
		String s ="[]";
		System.out.println(deserialize(s));
	}
	//方法1：
	//用一个stack存储中间结果，迭代实现
	public static NestedInteger deserialize(String s) {
		Stack<NestedInteger> stack = new Stack<>();
        Integer num = null;
        NestedInteger ni = null;    //用于表示当前结束获得的元素
        int p = 1;
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '['){ //如果遇到'[则表示接下来时一个数组
                stack.push(new NestedIntegerImpl());
                num = null;
            }else if(s.charAt(i) == '-'){   //负数
                p = -1;
            }else if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){     //数字
                if(num == null){
                    num = Integer.valueOf(0);
                }
                num = num*10 + s.charAt(i) - '0';
            }else if(s.charAt(i) == ',' || s.charAt(i) == ']'){     
                //','和']'表示数组的中间和末尾
                if(num != null){
                    ni = new NestedIntegerImpl(num*p);
                    p = 1;
                }
                //如果前面的元素不为空，则添加到列表中
                if(ni != null){
                    stack.peek().add(ni);
                }
                num = null;
                //如果是']'，表示数组，则出栈',若为','，则设置ni为空
                if(s.charAt(i) == ']'){
                    ni = stack.pop();
                }else{
                    ni = null;
                }
            }
        }
        if(num != null){
            ni = new NestedIntegerImpl(num*p);
        }
        return ni;
	}
	//方法2：
	//递归实现
	int i = 1;
    int start = i;
    
    public NestedInteger deserialize2(String s) {
        if (s.charAt(0) != '[') return new NestedIntegerImpl(Integer.valueOf(s));
        NestedInteger res = new NestedIntegerImpl();
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '[') {
                start = ++i;
                NestedInteger ni = deserialize2(s);
                res.add(ni);
            }
            else if (c == ']' || c == ',') {
                String num = s.substring(start, i);
                if (!num.equals("")) {
                    int n = Integer.valueOf(num);
                    NestedInteger ni = new NestedIntegerImpl(n);
                    res.add(ni);
                }
                start = ++i;
                if (c == ']') break;
            } 
            else 
                i++;
        }
        return res;
    }
}
