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
            //offer 和 add 最大差别  offer 新增如果失败返回false。 而add如果失败会报错。因为容量的问题
            availableBuffers.offer(byteBuffer);
        }
    }

    public void returnBuffer(ByteBuffer byteBuffer) {
        byteBuffer.position(0);
        byteBuffer.limit(fileSize);
        availableBuffers.offerFirst(byteBuffer);
    }

    public ByteBuffer borrowBuffer() {

        return availableBuffers.pollFirst();
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

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(20);
        byteBuffer.mark();
        byteBuffer.put("qwertfghi".getBytes(), 0, "abcdefghi".length());
        System.out.println(byteBuffer.position());
        ByteBuffer byteBuffer2 = byteBuffer.slice();
        byteBuffer2.put("ascdfg".getBytes(), 0, "ascdfg".length());
        byte[] bytes = new byte["abcdefghi".length()];
        byteBuffer.position(0);
//        byteBuffer.reset();
        byteBuffer.get(bytes, 0, "abcdefghi".length());
        System.out.println(new String(bytes));
        byte[] bytes2 = new byte[4];
        byteBuffer2.rewind();
        byteBuffer2.get(bytes2, 0, 4);
        System.out.println(new String(bytes2));

        byteBuffer.get(bytes2, 0, 4);
        System.out.println(new String(bytes2));
    }

}
