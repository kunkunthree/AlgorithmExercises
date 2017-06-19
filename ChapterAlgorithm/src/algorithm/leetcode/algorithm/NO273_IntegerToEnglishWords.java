package algorithm.leetcode.algorithm;
/*
 * hard
 * 273. Integer to English Words 
 *  Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than
 *   2^31 - 1.

For example,

123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class NO273_IntegerToEnglishWords {
	//方法1：
	//每三个数进行解析，通过100内的数组
	public String numberToWords(int num) {
        if(num == 0){
            return "Zero";
        }
        String[] bigUnits = new String[]{""," Thousand"," Million"," Billion"};
        String[] smallUnits = new String[]{
            "Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine",
            "Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen",
            "Twenty","Twenty One","Twenty Two","Twenty Three","Twenty Four",
                    "Twenty Five","Twenty Six","Twenty Seven","Twenty Eight","Twenty Nine",
            "Thirty","Thirty One","Thirty Two","Thirty Three","Thirty Four",
                    "Thirty Five","Thirty Six","Thirty Seven","Thirty Eight","Thirty Nine",
            "Forty","Forty One","Forty Two","Forty Three","Forty Four",
                    "Forty Five","Forty Six","Forty Seven","Forty Eight","Forty Nine",
            "Fifty","Fifty One","Fifty Two","Fifty Three","Fifty Four",
                    "Fifty Five","Fifty Six","Fifty Seven","Fifty Eight","Fifty Nine",
            "Sixty","Sixty One","Sixty Two","Sixty Three","Sixty Four",
                    "Sixty Five","Sixty Six","Sixty Seven","Sixty Eight","Sixty Nine",
            "Seventy","Seventy One","Seventy Two","Seventy Three","Seventy Four",
                    "Seventy Five","Seventy Six","Seventy Seven","Seventy Eight","Seventy Nine",
            "Eighty","Eighty One","Eighty Two","Eighty Three","Eighty Four",
                    "Eighty Five","Eighty Six","Eighty Seven","Eighty Eight","Eighty Nine",
            "Ninety","Ninety One","Ninety Two","Ninety Three","Ninety Four",
                    "Ninety Five","Ninety Six","Ninety Seven","Ninety Eight","Ninety Nine",
            "Hundred"};
        StringBuilder result = new StringBuilder();
        String tmpString;
        int count = 0,tmpNum;
        while(num != 0){
            tmpNum = num%1000;
            if(tmpNum != 0){
                tmpString = getSmallRange(num%1000,smallUnits);
                result.insert(0," " + tmpString  + bigUnits[count]);
            }
            num/=1000;
            count++;
        }
        result.deleteCharAt(0);
        return result.toString();
    }
    private String getSmallRange(int num,String[] smallUnits){
        StringBuilder sb = new StringBuilder();
        if(num >= 100){
            sb.append(smallUnits[num/100] + " Hundred");
        }
        num%=100;
        if(!(num == 0 && sb.length() > 0)){
            sb.append(" " + smallUnits[num]);
        }
        if(sb.charAt(0) == ' '){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
    
    //方法2：
    public static final String []oneDigit = {"One", "Two","Three", "Four", "Five", "Six","Seven", "Eight", "Nine", "Ten"};
	public static final String [] twoDigits = { "Eleven", "Twelve", "Thirteen", "Fourteen","Fifteen","Sixteen","Seventeen","Eighteen", "Nineteen"};
	public static final String[] tenDigit = { "Ten", "Twenty", "Thirty" , "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	public static final String [] higherNumber = {"Billion","Million", "Thousand", "Hundred"};
	
	public static int multiplier[] ={1000000000,1000000,1000};
	public String numberToWords2(int num) {
        int mod;
         String str = "";
         if (num == 0)
            return "Zero";
		//while (num>0)
		{
			for(int i =0; i<multiplier.length;i++)
			{
				mod = num/multiplier[i];
				//num%=/
				if (mod!=0)
				{
					if(str.length()!=0)
					{
						str+=" ";
					}
					str += ConvertLowerNumber(mod) + " "+ higherNumber[i];
				}
				num%=multiplier[i];
			}
			if(num != 0)
			{
				String lowVal = ConvertLowerNumber(num);
				if (lowVal.length()>0)
				{	
					if(str.length()!= 0)
					{
						str+=" " +lowVal;
					}
					else
					{
						str = lowVal;
					}
				}
			}
		}

		return str;
    }
	// convert to string for number <1000
	public String ConvertLowerNumber(int num1)
	{ 
		String lowVal ="";
		int mod =0;
		{
			mod = num1/100;
			if (mod> 0)
			{
				lowVal += oneDigit[mod-1] + " "+ "Hundred";
			}
			num1= num1%100;
			if (num1 >10 && num1<=19 )
			{
				if(lowVal.length()!=0)
				{
					lowVal+=" ";
				}
				lowVal += twoDigits[num1-11];
			}
			else
			{
				mod = num1/10;
				
				if (mod> 0)
				{
					if(lowVal.length()!=0)
					{
						lowVal+=" ";
					}
					lowVal +=  tenDigit[mod-1];
				}
				num1= num1%10;
				mod = num1;
				if (mod> 0)
				{
					if(lowVal.length()!=0)
					{
						lowVal+=" ";
					}
					lowVal +=  oneDigit[mod-1];
				}
				num1=num1/10;
			}
		}
		return lowVal;
	}
	
	//方法3：
	//最简洁的方法
	private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

	public String numberToWords3(int num) {
	    if (num == 0) return "Zero";
	    int i = 0;
	    String words = "";
	    while (num > 0) {
	        if (num % 1000 != 0)
	    	    words = helper(num % 1000) +THOUSANDS[i] + " " + words;
	    	num /= 1000;
	    	i++;
	    }
	    return words.trim();
	}

	private String helper(int num) {
	    if (num == 0)
	        return "";
	    else if (num < 20)
	        return LESS_THAN_20[num] + " ";
	    else if (num < 100)
	        return TENS[num / 10] + " " + helper(num % 10);
	    else
	        return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
	}
}
