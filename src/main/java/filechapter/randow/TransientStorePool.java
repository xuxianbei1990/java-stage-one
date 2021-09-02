package filechapter.randow;

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

            final long address = ((DirectBuffer) byteBuffer).address();
            //这里涉及到windows操作系统资源冻结
//            Pointer pointer = new Pointer(address);
            availableBuffers.offer(byteBuffer);
        }
    }

    public void destroy() {
        for (ByteBuffer byteBuffer : availableBuffers) {
            final long address = ((DirectBuffer) byteBuffer).address();

        }
    }

}
