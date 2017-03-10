package algorithm;
import static xie.util.Print.*;
import java.util.*;
import java.text.*;
/*
 * 将某个时间以固定的格式转化成字符串
 * SimpleDateFormat：
 * 	用特定的匹配字符代替时间，如果比有效数字位数少，则扩展为有效位数，如果比有效位数多则在前面填充0
 */

public class DateFormat {
	public static void main(String[] args) {
		println(date2FormatStr(new Date()));
	}
	public static String date2FormatStr(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyyy M d HHHHHH:mm:ss");
		String str = sdf.format(d);
		return str;
	}
}
