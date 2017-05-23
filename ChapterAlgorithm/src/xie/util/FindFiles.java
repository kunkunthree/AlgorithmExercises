package xie.util;
/*
 * 搜索目录中的文件
 */
import static xie.util.Print.println;
import java.io.File;
import java.util.*;
public class FindFiles {
	public static void main(String[] args) {
		List<String> list  = new ArrayList<String>();
		for(File file:Directory.walk("/home/xiepuxin/workspace/ChapterAlgorithm/src/algorithm/leetcode/algorithm/", ".*\\.java")){
			String path = file.toString();
			path = path.substring(path.lastIndexOf("/")+1);
//			println(path.substring(path.lastIndexOf("/")+1));
			if(path.startsWith("NO")){
//				System.out.println(path);
				list.add(path);
			}
		}
		System.out.println(list.size());
		Random rand = new Random();
		System.out.println(list.get(rand.nextInt(list.size())));
		Scanner in = new Scanner(System.in);
		String s;
		while(in.hasNextLine()){
			s = in.nextLine();
			System.out.println(list.get(rand.nextInt(list.size())));
		}
	}
}
