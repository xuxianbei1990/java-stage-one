package filechapter.randow;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 映射文件
 *
 * @author: xuxianbei
 * Date: 2021/9/2
 * Time: 19:54
 * Version:V1.0
 */
public class MappedFile {

    private File file;
    private String fileName;
    private int fileSize;
    private MappedByteBuffer mappedByteBuffer;
    private FileChannel fileChannel;

    protected final AtomicInteger wrotePosition = new AtomicInteger(0);

    public MappedFile(final String fileName, final int fileSize) {
        this.file = new File(fileName);
        ensureDirOk(file.getParent());
        try {
            this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
            this.mappedByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void destroy() {
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean appendMessage(final byte[] data) {
        try {
            fileChannel.position(this.wrotePosition.get());
            fileChannel.write(ByteBuffer.wrap(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.wrotePosition.addAndGet(data.length);
        return true;
    }

    private void ensureDirOk(String parent) {
        File file = new File(parent);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
