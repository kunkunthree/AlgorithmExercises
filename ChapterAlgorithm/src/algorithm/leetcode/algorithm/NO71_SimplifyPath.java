package algorithm.leetcode.algorithm;
/*
 * medium
 * 71. Simplify Path
 * Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"


 */
import java.util.*;
public class NO71_SimplifyPath {
	public static void main(String[] args) {
		String path = "/a/./b/../../c/";
		System.out.println(simplifyPath(path));
	}
	//方法1：
	//利用stack记录当前’/'的最后的位置，当遇到/../时，pop()并跳到新的peek()位置上
    public static String simplifyPath(String path) {
        int length = path.length();
        char[] p = path.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        int index = 0,point = 0;
        for(int i = 0 ; i < length ; i++){
            switch(p[i]){
                case '/':   
                    if(i > 0 && p[i] == p[i-1])break;
                    if(point == 1 && index == stack.peek()+2){
                        index = stack.peek();
                    }else if(point == 2 && index == stack.peek()+3){
                        if(stack.peek() == 0){
                            index = stack.peek();
                        }else{
                            stack.pop();
                            index = stack.peek();
                        }
                    }else{
                        stack.push(index);
                        p[index] = p[i];
                    }
                    index++;
                    point = 0;
                    break;
                case '.':   point++;
                default:    p[index++] = p[i];break;
            }
        }
        if(point == 1 && index == stack.peek()+2){
            if(stack.size() == 1){
                index = stack.peek()+1;
            }else{
                index = stack.peek();
            }
        }
        if(point == 2 && index == stack.peek()+3){
            if(stack.size() == 1){
                index = stack.peek()+1;
            }else{
                stack.pop();
                if(stack.size() == 1){
                    index = stack.peek()+1;
                }else{
                    index = stack.peek();
                }
            }
        }
        if(index > 1 && index <= length && p[index-1] == '/')index--;
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < index ; i++){
            result.append(p[i]);
        }
        return result.toString();
    }
    //方法2：
    //在原路径末尾上添加一个‘/'，以免出现最后没有’/‘的处理
    public String simplifyPath2(String path) {
        path+='/';
        int length = path.length();
        char[] p = path.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        int index = 0,point = 0;
        for(int i = 0 ; i < length ; i++){
            switch(p[i]){
                case '/':   
                    if(i > 0 && p[i] == p[i-1])break;
                    if(point == 1 && index == stack.peek()+2){
                        index = stack.peek();
                    }else if(point == 2 && index == stack.peek()+3){
                        if(stack.peek() == 0){
                            index = stack.peek();
                        }else{
                            stack.pop();
                            index = stack.peek();
                        }
                    }else{
                        stack.push(index);
                        p[index] = p[i];
                    }
                    index++;
                    point = 0;
                    break;
                case '.':   point++;
                default:    p[index++] = p[i];break;
            }
        }
        if(index > 1 && index <= length && p[index-1] == '/')index--;
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < index ; i++){
            result.append(p[i]);
        }
        return result.toString();
    }
    //方法3：
    //利用String的split方法，以'/'为边界分割字符串，并用stack来保存需要的子字符串，
    //忽略所有"."和""字符串，如果是".."，则stack.pop()
    //最后再以"/"为边界把所有stack中的字符串逆序穿起来，若最后结果为空，则说明为根目录，即”/"
    public String simplifyPath3(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        String res = "";
        for (String dir : stack) res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }
}
