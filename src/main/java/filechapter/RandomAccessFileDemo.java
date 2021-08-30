package filechapter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.lang3.CharSet;

//http://blog.csdn.net/bobo8945510/article/details/52958738
// 不过有意思的是，他不是reader和writer的子类
// * 1、只能访问文件，不能操作其他io设备  
// * 2、支持随机访问
// * 3、在读写等长记录文件有优势
public class RandomAccessFileDemo {
    static class RunableFile implements Runnable {
        /*
         *
         * 使用RandomAccessFile向数据库写入中文的时候， 使用write(String.getBytes()), 能够正常写入
         * 使用writeBytes(String), writeChars(String), writeUTF(String)均产生乱码。
         */
        File file;
        int modules;

        RunableFile(File file, int modules) {
            this.file = file;
            this.modules = modules;
        }

        @Override
        public void run() {
            try {
                RandomAccessFile rA = new RandomAccessFile(file, "rw");
                String str = "没有我斩舰刀，斩不断的---+\r\n";
                // 根据ss内容来计算指针的位置
                rA.seek(modules * str.getBytes().length);
                rA.write(str.getBytes("UTF-8"));
                rA.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File(FileConstant.DIR_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
//        file = new File(FileConstant.OUTFILEPATH);
        for (int i = 0; i < 5; i++) {
            new Thread(new RunableFile(file, i)).start();
        }
        FileConstant.OpenFile(FileConstant.OUTFILEPATH);
    }

}
