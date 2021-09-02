package filechapter;

import java.io.IOException;

public class FileConstant {
    public static final String DIR_PATH = "E:\\整理\\Java";
    public static final String TESTFILEPATH = DIR_PATH + "\\Lifecycle1.png";
    public static final String OUTFILEPATH = DIR_PATH + "\\delete.txt";
    public static final String READFILEPATH = "/filechapter/Read.txt";
    //windows 默认txt文本是GBK
    public static final String READFILEPATHGBK = DIR_PATH + "\\ReadGBK.txt";
    public static final String READFILENCODEEPATH = DIR_PATH + "\\ReadEncode.txt";

    public static void OpenFile(String FileValue) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler file://" + FileValue);
    }
}
