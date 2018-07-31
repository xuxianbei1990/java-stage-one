package filechapter;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;
import java.io.Writer;

class WritePiped implements Runnable {

	private Writer out;

	public WritePiped(Writer out) {
		this.out = out;
	}

	@Override
	public void run() {
		try {
			try {
				for (char c = 'a'; c <= 'z'; c++) {
					out.write(c);
				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			// http://blog.csdn.net/yizhenn/article/details/70284090
			System.out.println(Thread.currentThread().getUncaughtExceptionHandler() + e.getMessage());
		}
	}

}

class ReaderPiped implements Runnable {

	private Reader in;
	private StringBuilder sb = new StringBuilder();

	ReaderPiped(Reader in) {
		this.in = in;
	}

	@Override
	public void run() {
		int ch;
		try {
			while ((ch = in.read()) != -1) {
				sb.append((char)ch);
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Thread.currentThread().getUncaughtExceptionHandler() + e.getMessage());
		}

	}

}

public class PipedDemo {

	public static void main(String[] args) throws IOException, InterruptedException {
		PipedWriter out = new PipedWriter();
		PipedReader pipedReader = new PipedReader(out);
	    
	    Runnable inRead = new ReaderPiped(pipedReader);
		Runnable outWrite = new WritePiped(out);
		new Thread(inRead).start();
		Thread.sleep(1000);
		new Thread(outWrite).start();
		
		
	}

}
