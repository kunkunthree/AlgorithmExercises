package algorithm.leetcode.algorithm;
/*
 * hard
 * 68. Text Justification 
 *  Given an array of words and a length L, format the text such that each line has exactly L characters 
 *  and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line 
do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots 
on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:

[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Note: Each word is guaranteed not to exceed L in length. 
 */
import java.util.*;
public class NO68_TextJustification {
	//方法1：
	//直接法，记录每一行能承受的单词，当超过能承受的长度，则把当前记录的单词组成一行加入list，清空记录和长度
	public List<String> fullJustify(String[] words, int maxWidth) {
        int wordLength = 0;
        List<String> tmpList = new ArrayList<>();
        List<String> result = new ArrayList<>();
        if(maxWidth == 0){
            for(String word : words){
                result.add(word);
            }
            return result;
        }
        for(int i = 0 ; i < words.length ; i++){
            if(wordLength + tmpList.size() + words[i].length() > maxWidth){
                int spaceCount = maxWidth-wordLength;
                String s = "";
                if(tmpList.size() == 1){
                    for(int j = 0 ; j < spaceCount ; j++){
                        s+=" ";
                    }
                    s = tmpList.get(0) + s;
                }else{
                    for(int j = tmpList.size()-1 ; j >= 0 ; j--){
                        s = tmpList.get(j) + s;
                        if(j > 0){
                            int k = spaceCount/j;
                            while(k>0){
                                s = " "+s;
                                k--;
                            }
                            spaceCount -= spaceCount/j;
                        }
                    }
                }
                result.add(s);
                tmpList.clear();
                wordLength = 0;
                i--;
            }else{
                tmpList.add(words[i]);
                wordLength+=words[i].length();
            }
        }
        if(tmpList.size() != 0){
            int spaceCount = maxWidth-wordLength;
            String s = "";
            if(tmpList.size() == 1){
                for(int j = 0 ; j < spaceCount ; j++){
                    s+=" ";
                }
                s = tmpList.get(0) + s;
            }else{
                for(int i = 0 ; i < spaceCount-tmpList.size(); i++){
                    s+=" ";
                }
                for(int j = tmpList.size()-1 ; j >= 0 ; j--){
                    s = tmpList.get(j) + " " +s;
                }
            }
            result.add(s);
            tmpList.clear();
        }
        return result;
    }
	//方法2：
	public List<String> fullJustify2(String[] words, int L) {
        List<String> lines = new ArrayList<String>();
        
        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                if (words[last].length() + count + 1 > L) break;
                count += words[last].length() + 1;
                last++;
            }
            
            StringBuilder builder = new StringBuilder();
            int diff = last - index - 1;
            // if last line or number of words in the line is 1, left-justified
            if (last == words.length || diff == 0) {
                for (int i = index; i < last; i++) {
                    builder.append(words[i] + " ");
                }
                builder.deleteCharAt(builder.length() - 1);
                for (int i = builder.length(); i < L; i++) {
                    builder.append(" ");
                }
            } else {
                // middle justified
                int spaces = (L - count) / diff;
                int r = (L - count) % diff;
                for (int i = index; i < last; i++) {
                    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        
        
        return lines;
    }
}
