package algorithm.leetcode.algorithm;
/*
 * medium
 * 318. Maximum Product of Word Lengths
 *  Given a string array words, find the maximum value of length(word[i]) * length(word[j]) 
 *  where the two words do not share common letters. 
 *  You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:

Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:

Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words. 
 */
public class NO318_MaximumProductofWordLengths {
	public static void main(String[] args) {
		String[] words = new String[]{"abcw","baz","foo","bar","xtfn","abcdef"};
		System.out.println(maxProduct2(words));
	}
	//方法1：
	//遍历所有情况，[0,(1,2,3,....n-1)],[1,(2,3,....n-1)]
	//O(n^2)time,O(1)space
	//用boolean数组存储当前用于比较的字符串含有的字母
	public int maxProduct(String[] words) {
        if(words == null || words.length <= 1){
            return 0;
        }
        int max = 0;
        boolean[] c = new boolean[26];
        for(int i = 0 ; i < words.length-1 ; i++){
            for(int j = 0 ; j < 26 ; j++){
                c[j] = false;
            }
            for(int j = 0 ; j < words[i].length() ; j++){
                c[words[i].charAt(j) - 'a'] = true;
            }
            for(int j = i+1 ; j < words.length ; j++){
                boolean existed = false;
                for(int k = 0 ; k < words[j].length() ; k++){
                    if(c[words[j].charAt(k)-'a'] == true){
                        existed = true;
                        break;
                    }
                }
                if(existed == false){
                    max = Math.max(max,words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
	//方法2：
	//同理方法1，不过是先预处理得到所有字符串存在字符的状态，存在一个int数组里
	//比方法1快，减少很多不必要的比较
	public static int maxProduct2(String[] words) {
        if(words == null || words.length <= 1){
            return 0;
        }
        int max = 0;
        int length = words.length;
        int[] mask = new int[length];
        for(int i = 0 ; i < words.length ; i++){
            for(int j = 0 ; j < words[i].length() ; j++){
                mask[i] |= 1<<(words[i].charAt(j) - 'a');
            }
        }
        for(int i = 0 ; i < words.length-1 ; i++){
            for(int j = i+1 ; j < words.length ; j++){
                if((mask[i] & mask[j]) == 0){
                    max = Math.max(max,words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
