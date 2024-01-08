package pl.devsite.io.file;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

// https://www.oracle.com/technical-resources/articles/javase/perftuning.html
public class UsingReadRandom implements Closeable {
    private static final int DEFAULT_BUFSIZE = 4096;
    private RandomAccessFile raf;
    private byte inbuf[];
    private long startpos = -1;
    private long endpos = -1;
    private int bufsize;

    public UsingReadRandom(String name) throws FileNotFoundException {
        this(name, DEFAULT_BUFSIZE);
    }

    public UsingReadRandom(String name, int b) throws FileNotFoundException {
        raf = new RandomAccessFile(name, "r");
        bufsize = b;
        inbuf = new byte[bufsize];
    }

    public int read(long pos) {
        if (pos < startpos || pos > endpos) {
            long blockstart = (pos / bufsize) * bufsize;
            int n;
            try {
                raf.seek(blockstart);
                n = raf.read(inbuf);
            } catch (IOException e) {
                return -1;
            }
            startpos = blockstart;
            endpos = blockstart + n - 1;
            if (pos < startpos || pos > endpos) return -1;
        }
        return inbuf[(int) (pos - startpos)] & 0xffff;
    }

    public void close() throws IOException {
        raf.close();
    }
}