package algorithm;
import static xie.util.Print.*;
import java.util.*;
/*
 * 得到某一天的下一天的日期
 */
public class NextDaty {
	public static Date getNextDay1(Date d){
		return new Date(d.getTime() + 24 * 60 * 60 * 1000);
	}
//	public static void getNextDay2(Date d){
//		int y = d.getYear();
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(d);
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH);
//		int day = cal.get(Calendar.DAY_OF_MONTH);
//		boolean isLeapYear =  (year%400 == 0 || year%100 !=0 && year % 4 ==0) ? true:false;
//	}
	public static void main(String[] args) {
		println(getNextDay1(new Date()));
	}
}
