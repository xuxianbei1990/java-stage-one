package filechapter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * 过滤流
 * 
 * @author xuxb 过滤流(装饰类)并没有改变实际的读写行为，只是扩展了进一步处理数据的功能
 */

public class FilterDemo {
	static final int KEY = 125;
	private Reader in;
	private Writer out;
	private DecodeFilterReader dfilterin;
	private EncodeFilterWriter efilterout;
	private StringBuilder sb = new StringBuilder();

	// 解密文件
	class DecodeFilterReader extends FilterReader {

		protected DecodeFilterReader(Reader in) {
			super(in);
		}

		public int read() throws IOException {
			int c = super.read() - KEY;
			return (c == -1-KEY ? -1: c );
		}
	}

	// 加密文件
	class EncodeFilterWriter extends FilterWriter {

		protected EncodeFilterWriter(Writer out) {
			super(out);
			// TODO Auto-generated constructor stub
		}

		public void write(int c) throws IOException {
			c = c + KEY;
			super.write(c);
		}
	}

	void readerFile(Reader in) throws IOException {
		int ch;
		while ((ch = in.read()) != -1) {
			sb.append((char) ch);
		}
		System.out.println(sb);
		in.close();
	}

	void testReader() throws IOException {
		in = new FileReader(FileConstant.READFILENCODEEPATH);
		dfilterin = new DecodeFilterReader(in);
		readerFile(dfilterin);
	}

	void testWriter() throws IOException {
		out = new FileWriter(FileConstant.READFILENCODEEPATH);
		efilterout = new EncodeFilterWriter(out);
		char[] chs = "超神杀：天上天玺无敌斩。刀剑善于。U型思维".toCharArray();
		for (int i : chs) {
			efilterout.write(i);
		}
		efilterout.close();
	}

	public static void main(String args[]) throws IOException {
		FilterDemo fd = new FilterDemo();
		fd.testWriter();
		fd.testReader();
		FileConstant.OpenFile(FileConstant.READFILENCODEEPATH);

	}

}
