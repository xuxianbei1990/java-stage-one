package filechapter.randow;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author: xuxianbei
 * Date: 2021/8/31
 * Time: 17:04
 * Version:V1.0
 */
public class TransientStorePool {

    private int poolSize;

    private int fileSize;

    private Deque<ByteBuffer> availableBuffers;

    public TransientStorePool(int poolSize, int fileSize) {
        this.poolSize = poolSize;
        this.fileSize = fileSize;
        availableBuffers = new ConcurrentLinkedDeque<>();
    }

    public void init() {
        for (int i = 0; i < poolSize; i++) {
            //直接申请堆外内存
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(fileSize);
            //把堆外的内存在操作系统中锁住 该批内存锁定，避免被置换到交换区，提高存储性能
            lockWinsResource((DirectBuffer) byteBuffer);
            availableBuffers.offer(byteBuffer);
        }
    }

    private void lockWinsResource(DirectBuffer byteBuffer) {
        final long address = byteBuffer.address();
        //这里涉及到windows操作系统资源冻结
        Pointer pointer = new Pointer(address);
        LibC.INSTANCE.mlock(pointer, new NativeLong(fileSize));
    }

    public void destroy() {
        for (ByteBuffer byteBuffer : availableBuffers) {
            final long address = ((DirectBuffer) byteBuffer).address();
            //释放JVM内存锁定
            Pointer pointer = new Pointer(address);
            LibC.INSTANCE.munlock(pointer, new NativeLong(fileSize));

        }
    }

}
