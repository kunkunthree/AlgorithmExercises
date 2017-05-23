package xie.util;
import java.io.*;
/*
 * 进程控制：
 * 		要想运行程序，需要向OSExecute.command()传递一个command字符串，它与你在
 * 控制台上运行该程序所键入的命令相同。这个命令被传递给java.lang.ProcessBuilder构造器
 * （它要求这个命令作为一个String对象序列而被传递），然后所承诺书的ProcessBuilder对象被启动。
 * 		为了捕获程序执行时产生的标准输出流，需要调用getInputStream()，因为InputStream是我们
 * 可以从中读取信息的流。从程序中产生的结果每次输出一行，因此要使用readLine()来读取。
 * 这里这些行只是直接被打印了出来，但是你还可能希望从command()中捕获和返回它们。该程序的错误
 * 被发送到了标准错误流，并且通过调用getErrorStream()得以捕获。如果存在任何错误，它们都会被打印
 * 并且会抛出OSExecuteException，因此调用程序需要处理这个问题。
 */
public class OSExecute {
	public static void command(String command){
		boolean err = false;
		try{
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while((s = results.readLine()) != null){
				System.out.println(s);
			}
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while((s = errors.readLine()) !=null){
				System.err.println(s);
				err = true;
			}
		}catch(Exception e){
			if(!command.startsWith("CMD /C")){
				command("CMD /C " + command);
			}else{
				throw new RuntimeException(e);
			}
		}
		if(err){
			throw new OSExecuteException("Errors executing " + command);
		}
	}
}
