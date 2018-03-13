package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhoutaoo on 09/11/2017.
 */
public class FileNio {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("nio-data.txt", "r");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = channel.read(buf);
        while (bytesRead != -1) {
            System.out.print("read");
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print( buf.get());
            }
            System.out.println("");
            buf.clear();
            bytesRead = channel.read(buf);
        }
        file.close();
    }
}
