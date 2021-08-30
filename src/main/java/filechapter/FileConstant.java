package filechapter;

import java.io.IOException;

public class FileConstant {
    static final String DIR_PATH = "E:\\整理\\Java";
    static final String TESTFILEPATH = DIR_PATH + "\\Lifecycle1.png";
    static final String OUTFILEPATH = DIR_PATH + "\\delete.txt";
    static final String READFILEPATH = "/filechapter/Read.txt";
    //windows 默认txt文本是GBK
    static final String READFILEPATHGBK = DIR_PATH + "\\ReadGBK.txt";
    static final String READFILENCODEEPATH = DIR_PATH + "\\ReadEncode.txt";

    static void OpenFile(String FileValue) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler file://" + FileValue);
    }
}
