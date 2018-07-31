package filechapter;

import java.io.IOException;

public class FileConstant {
	static final String TESTFILEPATH = "E:\\整理\\Java\\Lifecycle1.png";
	static final String OUTFILEPATH = "E:\\整理\\Java\\delete.txt";
	static final String READFILEPATH = "/filechapter/Read.txt";
	//windows 默认txt文本是GBK
	static final String READFILEPATHGBK = "E:\\整理\\Java\\ReadGBK.txt";
	static final String READFILENCODEEPATH = "E:\\整理\\Java\\ReadEncode.txt";
	
	static void OpenFile(String FileValue) throws IOException {
		Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler file://" + FileValue);
	}
}
