package algorithm.leetcode.algorithm;
/*
 * medium
 * 423. Reconstruct Original Digits from English
 * Given a non-empty string containing an out-of-order English representation of digits 0-9, 
 * output the digits in ascending order.

Note:

    1.Input contains only lowercase English letters.
    2.Input is guaranteed to be valid and can be transformed to its original digits. 
    	That means invalid inputs such as "abc" or "zerone" are not permitted.
    3.Input length is less than 50,000.

Example 1:
Input: "owoztneoer"
Output: "012"

Example 2:
Input: "fviefuro"
Output: "45"

 */
public class NO423_ReconstructOriginalDigitsfromEnglish {
	//方法1：
	//根据各个数字字母个数之间的依赖关系
	//0,2,4,6,8有自己特有的字母
	//zero	z
	//two		w
	//four		u
	//six		x
	//eight	g
	//把上面的数字含有的所有字母剔除后，有自己特殊的字母的数字有1,3,7
	//one		o
	//three	r
	//seven	s
	//把上面的数字含有的所有字母剔除后，有自己特殊的字母的数字有5
	//five		v
	//把上面的数字含有的所有字母剔除后，有自己特殊的字母的数字有9
	//nine	i
	public String originalDigits(String s) {
        int[] digits = new int[10];
        int[] countChar = new int[26];
        int n = s.length();
        for(int i = 0; i < n ; i++){
            countChar[s.charAt(i)-'a']++;
        }
        //zero	0
        decrease(digits,countChar,"zero",'z',0);
        //two		2
        decrease(digits,countChar,"two",'w',2);
        //four		4
        decrease(digits,countChar,"four",'u',4);
        //six		6
        decrease(digits,countChar,"six",'x',6);
        //eight	8
        decrease(digits,countChar,"eight",'g',8);
        //one		1
        decrease(digits,countChar,"one",'o',1);
        //three	3
        decrease(digits,countChar,"three",'r',3);
        //seven	7
        decrease(digits,countChar,"seven",'s',7);
        //five		5
        decrease(digits,countChar,"five",'v',5);
        //nine	9
        decrease(digits,countChar,"nine",'i',9);
        
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < digits.length ; i++){
            while(digits[i] > 0){
                digits[i]--;
                result.append(i);
            }
        }
        return result.toString();
    }
    private void decrease(int[] digits,int[] countChar,String s,char c,int digit){
        while(countChar[c-'a'] > 0){
            digits[digit]++;
            for(int i = 0 ; i < s.length() ; i++){
                countChar[s.charAt(i)-'a']--;
            }
        }
    }
    //方法2：
    //先统计特殊字符的数字的个数，再统计其他数字中一个只出现1次的字母个数
    //再减去其他字母也出现该字母的个数，得到最后的结果
    public String originalDigits2(String s) {
        int[] digits = new int[10];
        int[] countChar = new int[26];
        int n = s.length();
        char c;
        for(int i = 0; i < n ; i++){
            c = s.charAt(i);
            countChar[s.charAt(i)-'a']++;
            if(c == 'z')digits[0]++;    //1
            if(c == 'w')digits[2]++;    //2
            if(c == 'u')digits[4]++;    //4
            if(c == 'x')digits[6]++;    //6
            if(c == 'g')digits[8]++;    //8
            if(c == 'o')digits[1]++;    //1     0,2,4
            if(c == 'r')digits[3]++;    //3     0,4
            if(c == 's')digits[7]++;    //7     6
            if(c == 'f')digits[5]++;    //5     4
            if(c == 'i')digits[9]++;    //9     5,6,8
        }
        digits[1] = digits[1] - digits[0] - digits[2] - digits[4];
        digits[3] = digits[3] - digits[0] - digits[4];
        digits[7] = digits[7] - digits[6];
        digits[5] = digits[5] - digits[4];
        digits[9] = digits[9] - digits[5] - digits[6] - digits[8];
        
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < digits.length ; i++){
            while(digits[i] > 0){
                digits[i]--;
                result.append(i);
            }
        }
        return result.toString();
    }
}
