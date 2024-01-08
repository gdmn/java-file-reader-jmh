package pl.devsite.io.file;

import java.io.*;

public class UsingBufferedInputStream implements Closeable {
    private final BufferedInputStream bufferedInputStream;

    public UsingBufferedInputStream(String fileName) throws IOException {
        this.bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(fileName)));
    }

    public int read(byte[] dst, int offset, int length) throws IOException {
        return bufferedInputStream.read(dst, offset, length);
    }

    public void close() throws IOException {
        bufferedInputStream.close();
    }
}