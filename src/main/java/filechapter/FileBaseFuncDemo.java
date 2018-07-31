package filechapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

//358
/*
 * 字符流（16位 UTF-16）主要基于文本；
 * 读取器reader；写入器writer
 * 
 * 字节流（通常 8位）主要基于二进制数据（例如：图片）
 * 输入流input stream; 输出流 output stream;
 * 
 * InputStreamReader 和  OutputStreamWriter ：使用本地系统默认的编码机制
 * 把字节流转换成字符流。好处就是不用关心平台
 * 
 */
public class FileBaseFuncDemo {
	
	StringBuilder sb = new StringBuilder();
	Reader reader = null;
	Writer writer = null;
	InputStream in = null;
	OutputStream out = null;
	int total = 0;

	void testInputStreamReader() throws IOException {
		in = new FileInputStream(FileConstant.READFILEPATHGBK);
		reader = new InputStreamReader(in, "GBK");
		
		int ch = 0;
		int spaces = 0;
		char[] cbuf = new char[10];
		try {
			reader.read(cbuf);
			System.out.println("ddd");
			System.out.println(cbuf);
			System.out.println("ddd");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			for (total = 0; (ch = reader.read()) != -1; total += 2) {
				if (Character.isWhitespace((char) ch))
					spaces++;
				sb.append((char) ch);
			}
			System.out.println(sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(total + " chars, " + spaces + "spaces");
	}
	
	//文件阅读 字符流
	void testFileReader() {
		try {
			reader = new FileReader(FileConstant.READFILEPATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int ch = 0;
		int spaces = 0;
		char[] cbuf = new char[10];
		try {
			reader.read(cbuf);
			System.out.println("ddd");
			System.out.println(cbuf);
			System.out.println("ddd");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			for (total = 0; (ch = reader.read()) != -1; total += 2) {
				if (Character.isWhitespace((char) ch))
					spaces++;
				sb.append((char) ch);
			}
			System.out.println(sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(total + " chars, " + spaces + "spaces");
	}

	//文件写入 字符流
	void testWriter() {
		try {
			writer = new FileWriter(FileConstant.OUTFILEPATH);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String str = "wo da xi wa zui qiang";
		try {
			writer.write(str.toCharArray());
			str = "The Tky is Mine";
			writer.write(str);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			writer.flush();
			writer.close();
			Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler file://" + FileConstant.OUTFILEPATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 字节流输出 10对应ANSII换行
	void testFileOutputStream() throws IOException {
		String str = "The King Of Figter  ";
		out = new FileOutputStream(FileConstant.OUTFILEPATH);
		out.write(str.getBytes(), 0, str.length());
		out.write(new byte[] { 'b', 'e', 'g', 'i', 'n', 10 });
		for (int i = 0; i < 256; i++)
			out.write(i);
		out.flush();
		out.close();
		Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler file://" + FileConstant.OUTFILEPATH);
	}

	// 字节流输入
	void testFileInputStream() throws IOException {
		try {
			in = new FileInputStream(FileConstant.TESTFILEPATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("可以读取有效字节数即文件大小：" + in.available());
		try {
			int value;
			StringBuilder sb = new StringBuilder();
			while ((value = in.read()) != -1) {
				if (value < 255) {
					// 从当前游标进行跳转
					in.skip(5);
					continue;
				}
				total++;
				sb.append((char) value);
			}
			System.out.println(sb + " skip");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(total + " bytes");
	}

	public static void main(String[] args) throws IOException {
		FileBaseFuncDemo fbfd = new FileBaseFuncDemo();
		// fbfd.testFileOutputStream();
		// fbfd.testFileInputStream();
		// fbfd.testFileReader();
//		fbfd.testWriter();
		fbfd.testInputStreamReader();

	}

}
