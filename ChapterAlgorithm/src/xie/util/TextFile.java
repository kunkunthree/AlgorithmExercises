package xie.util;
import java.io.*;
import static xie.util.Print.*;
import java.util.*;
/*
 * 文件读写的使用工具：
 * 
 */
public class TextFile extends ArrayList<String> {
	//read a file as a single string:
	public static String read(String fileName){
		StringBuilder sb = new StringBuilder();
		try{
			BufferedReader in = new BufferedReader(
					new FileReader(
							new File(fileName).getAbsoluteFile()));
			try{
				String s;
				while((s = in.readLine()) != null){
					sb.append(s);
					sb.append("\n");
				}
			}finally{
				in.close();
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	//write a single file in one method call:
	public static void write(String fileName,String text){
		try{
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try{
				out.print(text);
			}finally{
				out.close();
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	//read a file,split by any regular expression:
	public TextFile(String fileName, String splitter){
		super(Arrays.asList(read(fileName).split(splitter)));
		if(get(0).equals(""))remove(0);
	}
	//normally read by lines:
	public TextFile(String fileName){
		this(fileName,"\n");
	}
	public void write(String fileName){
		try{
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try{
				for(String item:this){
					out.println(item);
				}
			}finally{
				out.close();
			}
		}catch(IOException e){
			throw new  RuntimeException(e);
		}
	}
	//simple test:
	public static void main(String[] args) {
		String file = read("src/xie/util/TextFile.java");
		write("src/io/test.txt",file);
		TextFile text = new TextFile("src/io/test.txt");
		text.write("src/io/test2.txt");
		TreeSet<String> words = new TreeSet<String>(new TextFile("src/xie/util/TextFile.java","\\W+"));
		println(words.headSet("a"));
	}
}
