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
    protected ByteBuffer writeBuffer = null;
    protected TransientStorePool transientStorePool = null;

    protected final AtomicInteger wrotePosition = new AtomicInteger(0);

    public MappedFile(final String fileName, final int fileSize, final TransientStorePool transientStorePool) {
        this.file = new File(fileName);
        ensureDirOk(file.getParent());
        try {
            //创建随机读写的通道
            this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
            //进行内存映射
            this.mappedByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
            this.writeBuffer = transientStorePool.borrowBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int flush(final int flushLeastPages) {
        //主要判断是否可以刷盘，如果有内容就刷盘，否则就不刷
        if (this.isAbleToFlush(flushLeastPages)) {
            //也不知道干啥，可能为了并发
            if (hold()) {
                try {
                    if (this.fileChannel.position() != 0) {
                        /**
                         * 这里涉及到了操作系统知识：一般无论是fileChannel，mappedByteBuffer。
                         * fileChannel.write(ByteBuffer.wrap(data));
                         * byteBuffer.put(this.msgStoreItemMemory.array(), 0, maxBlank);
                         * 之后，实际是给了操作系统，真正落盘的动作是操作系统完成的。
                         * 所以可以自己手动force
                         */
                        this.fileChannel.force(false);
                    } else {
                        this.mappedByteBuffer.force();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                release();
            }
        }
        return -1;
    }

    private void release() {


    }

    public int getReadPosition() {
//        return this.writeBuffer == null ? this.wrotePosition.get() : this.committedPosition.get();
        return 0;
    }

    private boolean hold() {
        return false;
    }


    private boolean isAbleToFlush(int flushLeastPages) {
        return false;
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

    /**
     * 添加消息
     *
     * @param data
     * @return
     */
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

    public boolean appendMessagesInner(final byte[] data) {
        try {
            // 创建一个前面共享区域的buffer
            ByteBuffer byteBuffer = writeBuffer != null ? writeBuffer.slice() : mappedByteBuffer.slice();
            byteBuffer.put(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void ensureDirOk(String parent) {
        File file = new File(parent);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
