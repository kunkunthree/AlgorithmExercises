package algorithm.leetcode.algorithm;
/*
 * medium
 * 388. Longest Absolute File Path
 * Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext

The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext

The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext 
and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory 
subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. 
For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", 
and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path 
to file in the abstracted file system. If there is no file in the system, return 0.

Note:

    The name of a file contains at least a . and an extension.
    The name of a directory or sub-directory will not contain a ..

Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 */
import java.util.*;
public class NO388_LongestAbsoluteFilePath {
	public static void main(String[] args) {
		String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//		System.out.println("\t".length());
		System.out.println(lengthLongestPath2(input));
	}
	//方法1：
	//迭代，直接法
	//stack先压进栈0，表示初始长度为0
	//先用"\n"分割字符串，再统计首部"\t"的个数tCount，用stack记录前面每层的长度
	//stack出栈直到stack长度等于"\t"个数tCount+1
	//若当前字符串含有"."，则说明是文件路径，得到绝对路径长度，与max对比
	//若当前字符串不含有"."，则说明是文件夹，计算文件夹绝对路径长度，长度为stack.peek()+length-tCount+1，压进栈
	//注意：
	//如果stack长度为1，则说明是根目录，没有斜杠符
	//如果stack长度大于1，则说明不是根目录，有斜杠符，需要总长度+1.
	public static int lengthLongestPath(String input) {
		String[] ss = input.split("\n");
        Stack<Integer> stack = new Stack<>();
        int tCount = 0,i,length,path;
        stack.push(0);
        int max = 0;
        for(String s : ss){
            tCount = 0;
            length = s.length();
            i = 0;
            while(i < length && s.charAt(i) == '\t'){
                tCount++;
                i++;
            }
            while(stack.size() > tCount+1){
                stack.pop();
            }
            path = stack.peek()+length-tCount+1;
            if(stack.size() == 1){
                path--;
            }
            if(s.contains(".")){
                max = Math.max(max,path);
            }else{
                stack.push(path);
            }
        }
        return max;
    }
	//方法2：
	//方法1的简化写法
	public static int lengthLongestPath2(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for(String s:input.split("\n")){
            int lev = s.lastIndexOf("\t")+1; // number of "\t"
            while(lev+1<stack.size()) stack.pop(); // find parent
            int len = stack.peek()+s.length()-lev+1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if(s.contains(".")) maxLen = Math.max(maxLen, len-1); 
        }
        return maxLen;
    }
}
