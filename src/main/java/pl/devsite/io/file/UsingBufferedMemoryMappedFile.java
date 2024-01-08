package pl.devsite.io.file;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class UsingBufferedMemoryMappedFile implements Closeable {
    private final MappedByteBuffer mappedByteBuffer;
    private final long fileSize;
    private final RandomAccessFile memoryFile;

    public UsingBufferedMemoryMappedFile(String fileName) throws IOException {
        memoryFile = new RandomAccessFile(fileName, "r");
        fileSize = memoryFile.length();
        mappedByteBuffer = memoryFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
    }

    public int read(byte[] dst, int offset, int length) {
        int toRead = length;
        if (mappedByteBuffer.position() + toRead > mappedByteBuffer.limit()) {
            toRead = mappedByteBuffer.limit() - mappedByteBuffer.position();
        }
        mappedByteBuffer.get(dst, offset, toRead);
        return toRead;
    }

    public void close() throws IOException {
        memoryFile.close();
    }
}
