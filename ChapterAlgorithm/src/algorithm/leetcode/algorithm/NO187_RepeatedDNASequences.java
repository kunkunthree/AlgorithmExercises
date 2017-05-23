package algorithm.leetcode.algorithm;
/*
 * medium
 * 187. Repeated DNA Sequences
 *  All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
 *  When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

 */
import java.util.*;
public class NO187_RepeatedDNASequences {
	public static void main(String[] args) {
		String s = "AAAAAAAAAAAA";
		System.out.println(findRepeatedDnaSequences2(s));
	}
	//方法1：
	//遍历字符串，用一个set保存出现过的字符串，另一个set保存重复出现的字符串
	public List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        Set<String> result = new HashSet<>();
        String subs;
        for(int i = 0 ; i < s.length()-9 ; i++){
            subs =  s.substring(i,i+10);
            if(set.contains(subs)){
                result.add(subs);
            }else{
                set.add(subs);
            }
        }
        return new ArrayList<String>(result);
    }
	//方法2：
	//
	public static List<String> findRepeatedDnaSequences2(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
    
        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }
}
