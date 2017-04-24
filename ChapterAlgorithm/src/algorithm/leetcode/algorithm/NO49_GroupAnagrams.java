package algorithm.leetcode.algorithm;
/*
 * medium
 * 49. Group Anagrams
 * Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note: All inputs will be in lower-case.
 */
import java.util.*;
public class NO49_GroupAnagrams {
	public static void main(String[] args) {
		String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(groupAnagrams(strs));
	}
	//方法1：
	//用一个list把所有字符串存起来，从其中移除一个字符串存到新的列表newList中，在剩余的list里找该字符串的排列，移除这些排列
	//找完后，如果list还有字符串剩余，那么继续上面的步骤直到list为空
	//效率太低，需要不断循环不断对比
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ll = new ArrayList<List<String>>();
        List<String> ss = new ArrayList<String>(Arrays.asList(strs));
        while(ss.size() > 0){
            List<String> l = new ArrayList<String>();
            int i = 0;
            String s = ss.get(i);
            ss.remove(i);
            l.add(s);
            while(i < ss.size()){
                String tmp = ss.get(i);
                if(ss.get(i).length() == s.length() && isAnagram(s,tmp)){
                    l.add(tmp);
                    ss.remove(i);
                }else{
                    i++;
                }
            }
            ll.add(l);
        }
        return ll;
        
    }
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] c = new int[26];
        for(int i = 0 ; i < s.length() ; i++){
            c[s.charAt(i)-'a']++;
        }
        for(int i = 0 ; i < t.length() ; i++){
            if(c[t.charAt(i)-'a'] == 0){
                return false;
            }else{
                c[t.charAt(i)-'a']--;
            }
        }
        return true;
    }
    //方法2：
    //用一个map把同类的字符串list存起来，key为字典序排序后的字符串，value为排序后相同的字符串list
    //最后用把map中的list全部提出来
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> ll = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0){
            return ll;
        }
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for(int i = 0 ; i < strs.length ; i++){
            char[] array = strs[i].toCharArray();
            Arrays.sort(array);
            String s = Arrays.toString(array);
            if(!map.containsKey(s)){
               map.put(s,new ArrayList<String>());
            }
            map.get(s).add(strs[i]);
        }
        for(List<String> l : map.values()){
            ll.add(l);
        }
        return ll;
    }
    
    //方法3：
    //用素数代表字符串中的字母，相同构成的字符串的所有字母所代表的数字的乘积是唯一的
    //优点：不需要排序，直接计算，时间复杂度为O(n)，n为所有字符串的字符总数
    //缺点：如果字符串很长的话，很容易乘积过大而溢出
    public static List<List<String>> groupAnagrams3(String[] strs) { 
    	   int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个z
    	    
    	            List<List<String>> res = new ArrayList<>();
    	            HashMap<Integer, Integer> map = new HashMap<>();
    	            for (String s : strs) {
    	                int key = 1;
    	                for (char c : s.toCharArray()) {
    	                    key *= prime[c - 'a'];
    	                }
    	                List<String> t;
    	                if (map.containsKey(key)) {
    	                    t = res.get(map.get(key));
    	                } else {
    	                    t = new ArrayList<>();
    	                    res.add(t);
    	                    map.put(key, res.size() - 1);
    	                }
    	                t.add(s);
    	            }
    	            return res;
    	    }
}
