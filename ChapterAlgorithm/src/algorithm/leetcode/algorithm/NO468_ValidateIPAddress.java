package algorithm.leetcode.algorithm;
/*
 * medium
 * 468. Validate IP Address 
 *  Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, 
each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. 
The groups are separated by colons (":"). 
For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. 
Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters 
in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address
(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive 
colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, 
the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:

Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".

Example 2:

Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".

Example 3:

Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.

 */
public class NO468_ValidateIPAddress {
	//方法1：
	//直接法，迭代
	public String validIPAddress(String IP) {
        int length = IP.length();
        char c;
        if(IP.contains(".")){	//含有"."
            int countDot = 0;		//"."的个数
            Integer num = null;		//ip的每个数值
            for(int i = 0 ; i < length ; i++){
                c = IP.charAt(i);
                if(c == '.'){		
                	//如果"."前面没有数字，或者数字大于255，或者".”的个数大于等于3，则不符合ipv4
                    if(num == null || num > 255 || countDot >= 3){
                        return "Neither";
                    }
                    countDot++;		
                    num = null;			//置num为null，表示还没有遇到数字
                }else if(isDecimal(c)){		//十进制数字
                    if(num == null){		//如果还没有遇到数字，则直接赋值
                        num = c - '0';
                    }else{							
                    	//如果已经遇到数字，则判断是否以0为首，或者超出255，则不符合ipv4
                        if(num == 0 || num > 255){
                            return "Neither";
                        }else{			//计算num的值
                            num = num*10 + c - '0';
                        }
                    }
                }else{		//如果既不是"."也不是十进制数，则不符合ipv4
                    return "Neither";
                }
            }
            //如果末尾不是数字，或者该数字大于255，或者点的个数不为3，则不符合ipv4
            if(num == null || num > 255 || countDot != 3){
                return "Neither";
            }
            return "IPv4";
        }else if(IP.contains(":")){	//如果不含有"."，且含有":"
            int countColon = 0;		//表示":"的个数
            int countDigit = 0;		//表示16进制数字的个数
            for(int i = 0 ; i < length ; i++){
                c = IP.charAt(i);
                if(c == ':'){		//如果":"前面没有16进制数字，或者前面已经有7个以上的":"，则不符合ipv6
                    if(countDigit == 0 || countColon >= 7){
                        return "Neither";
                    }
                    countDigit = 0;		//置16进制数字个数为0
                    countColon++;		//统计冒号":"个数
                }else if(isHexadecimal(c)){		//如果该字符时16进制数字
                    if(countDigit >= 4){		//如果前面的16进制数字大于等于4，则不符合ipv6
                        return "Neither";
                    }
                    countDigit++;
                }else{	//如果遇到":"和16进制数字以外的其他字符，则不符合ipv6
                    return "Neither";
                }
            }
            if(countColon != 7){		//如果":"个数不为7，则不符合ipv6
                return "Neither";
            }
            return "IPv6";
        }else{
            return "Neither";
        }
    }
    private boolean isDecimal(char c){	//判断是否为10进制数字
        return c >= '0' && c <= '9';
    }
    private boolean isHexadecimal(char c){		//判断是否为16进制数字
        return c >= '0' && c <= '9' || c >= 'A' && c <= 'F' || c >= 'a' && c <= 'f';
    }
    //方法2：
    //利用正则表达式
    public String validIPAddress2(String IP) {
        if(IP.matches("(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])"))return "IPv4";
        if(IP.matches("(([0-9a-fA-F]{1,4}):){7}([0-9a-fA-F]{1,4})"))return "IPv6";
        return "Neither";
    }
}
