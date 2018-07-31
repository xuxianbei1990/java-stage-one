package filechapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.util.FileCopyUtils;



public class FileDemo {
	
	//创建目录
	void createDirectory(){
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("D:").append(File.separator).append("卢卡尔").append(File.separator).
		  append("璐璐").append(File.separator);
		String directoryPath = stringbuffer.toString();

		File directory = new File(directoryPath);
		if (!directory.exists())
			directory.mkdirs();
	}
	
	//文件拷贝
	void copyFile() throws IOException {
		String inFile = "C:\\Users\\Administrator\\Desktop\\银行\\苏进沼（居民身份证_130133198106130953）自行申报表_2017.xlsx";
		String outFile = "C:\\Users\\Administrator\\Desktop\\银行\\苏进沼（居民身份证_130133198106130953）自行申报表_2017_01.xlsx";
		File in = new File(inFile);
		File out = new File(outFile);
		FileCopyUtils.copy(in, out);
	}
	
	//文件拷贝；字节流
	void copyFileInputStream() throws IOException {
		String inFile = "C:\\Users\\Administrator\\Desktop\\银行\\苏进沼（居民身份证_130133198106130953）自行申报表_2017.xlsx";
		String outFile = "C:\\Users\\Administrator\\Desktop\\银行\\苏进沼（居民身份证_130133198106130953）自行申报表_2017_02.xlsx";
		FileInputStream in = new FileInputStream(inFile);
		FileOutputStream out = new FileOutputStream(outFile);
		int read;
        byte b[] = new byte[1024];
        read = in.read(b);
        while (read != -1) {
        	out.write(b, 0, read);
            read = in.read(b);
        }
        in.close();
        out.flush();
        out.close();
	}
	
	// 文件转换字节流
	void fileToBytes() throws IOException {
		InputStream input = new FileInputStream("src//main//resources//filechapter//StreamTokenizer.txt");
		byte[] bytes = IOUtils.toByteArray(input);
		System.out.println(new String(bytes));
	}

	public static void main(String[] args) throws IOException {
		FileDemo fd = new FileDemo();
		fd.fileToBytes();
	}

}
