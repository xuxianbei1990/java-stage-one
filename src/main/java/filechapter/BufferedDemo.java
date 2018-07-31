package filechapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;

/**
 * Buffered 缓存读写都在缓存中进行。减少磁盘访问速度，提升速度 buffered 增加一个方法读取一行。写入newline来换行
 * 
 * @author xuxb
 *
 */
public class BufferedDemo {

	private BufferedReader bfin;
	private BufferedWriter bfout;

	private Reader in;

	StringBuilder sb = new StringBuilder();

	void testRead() throws IOException, URISyntaxException {
		// bfout.newLine();
		in = new FileReader(this.getClass().getResource("/").toURI().getPath() + FileConstant.READFILEPATH);
		bfin = new BufferedReader(in);
		bfin.mark(100);
		long start = System.nanoTime();
		readBuffered();
		System.out.println(System.nanoTime() - start);
		bfin.reset();
		start = System.nanoTime();
		readBuffered();
		System.out.println(System.nanoTime() - start);
	}

	private void readBuffered() throws IOException {
		int ch;
		sb.append("\n");
		while ((ch = bfin.read()) != -1) {
			sb.append((char) ch);
		}
		System.out.println(sb.toString());
	}

	public static void main(String args[]) throws IOException, URISyntaxException {
		BufferedDemo db = new BufferedDemo();
		db.testRead();
	}

}
