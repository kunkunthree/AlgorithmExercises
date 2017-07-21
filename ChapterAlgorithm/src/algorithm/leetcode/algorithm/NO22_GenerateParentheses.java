package algorithm.leetcode.algorithm;
/*
 * medium
 * 22. Generate Parentheses
 *  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

similar problems:
20. Valid Parentheses 
17. Letter Combinations of a Phone Number 
 */
import java.util.*;
public class NO22_GenerateParentheses {
	public static void main(String[] args) {
		System.out.println(generateParenthesis(4));
		
	}
	//方法1：
	//利用HashSet，因为这个方法有重复出现的情况，比较笨拙，而且空间消耗大
    public static Map<Integer,Set<String>> map = new HashMap<Integer,Set<String>>();
    public static List<String> generateParenthesis(int n) {
        Set<String> list = new HashSet<String>();
        if(n == 0){
            list.add("");
            if(!map.containsKey(0)){
                map.put(0,list);
            }
            return new ArrayList<String>(list);
        }
        Set<String> l1 = null,l2 = null;
        if(map.containsKey(n-1)){
            l1 = map.get(n-1);
        }else{
            l1 = new HashSet(generateParenthesis(n-1));
        }
        for(String s : l1){
            list.add("("+s+")");
        }
        for(int i = 1 ; i < n ; i++){
            if(map.containsKey(i)){
                l1 = map.get(i);
            }else{
                l1 = new HashSet(generateParenthesis(i));
            }
            for(String s1 : l1){
                if(map.containsKey(n-i)){
                    l2 = map.get(n-i);
                }else{
                    l2 = new HashSet(generateParenthesis(n-i));
                }
                for(String s2 : l2){
                    list.add(s1+s2);
                }
            }
        }
        if(!map.containsKey(n)){
            map.put(n,list);
        }
        return new ArrayList<String>(list);
    }
    //方法2：方法1的优化, 迭代
    //f(0): ""
	// f(1): "("f(0)")"
	// f(2): "("f(0)")"f(1), "("f(1)")"
	// f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"
	// So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ...
	// "("f(i)")"f(n-1-i) ... "(f(n-1)")"
    public List<String> generateParenthesis2(int n)
    {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));
        for (int i = 1; i <= n; ++i)
        {
            final List<String> list = new ArrayList<>();
            for (int j = 0; j < i; ++j)
            {
                for (final String first : lists.get(j))
                {
                    for (final String second : lists.get(i - 1 - j))
                    {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }
        return lists.get(lists.size() - 1);
    }
    
    
    //方法3：
    //递归，利用右括号')'的数量一定小于等于前面已经出现左括号的次数
    public List<String> generateParenthesis3(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    public void backtrack(List<String> list, String str, int open, int close, int max){
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
}
