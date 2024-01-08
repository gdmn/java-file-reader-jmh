package pl.devsite;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import pl.devsite.io.file.UsingBufferedInputStream;
import pl.devsite.io.file.UsingBufferedMemoryMappedFile;
import pl.devsite.io.file.UsingReadRandom;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class FileReaderBenchmark {
    final long size = 1024 * 1024 * 1024;
    private String filename;

    void writeRandomData(String filename, long size) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filename));
        Random r = new Random();
        byte[] buffer = new byte[1024 * 16];
        int chunks = (int) (size / buffer.length);
        r.nextBytes(buffer);
        for (int i = 0; i < chunks; i++) {
            bufferedOutputStream.write(buffer);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    @Setup
    public void setup() throws Exception {
        filename = Files.createTempFile("java_benchmark_", "_" + size).toString();
        writeRandomData(filename, size);
    }

    @TearDown
    public void tearDown() throws Exception {
        Files.delete(Paths.get(filename));
    }

    @Benchmark
    public long readRandom(Blackhole blackhole) throws Exception {
        try (UsingReadRandom reader = new UsingReadRandom(filename)) {
            long pos = 0;
            int c;
            byte buf[] = new byte[1];
            while ((c = reader.read(pos)) != -1) {
                pos++;
                buf[0] = (byte) c;
                blackhole.consume(c);
                blackhole.consume(buf);
            }
        }
        return 0;
    }

    @Benchmark
    public long bufferedMemory(Blackhole blackhole) throws Exception {
        byte[] buffer = new byte[1024 * 16];
        int k;
        try (UsingBufferedMemoryMappedFile reader = new UsingBufferedMemoryMappedFile(filename)) {
            while ((k = reader.read(buffer, 0, buffer.length)) > 0) {
                blackhole.consume(k);
                blackhole.consume(buffer);
            }
        }
        return 0;
    }

    @Benchmark
    public long bufferedInputStream(Blackhole blackhole) throws Exception {
        byte[] buffer = new byte[1024 * 16];
        int k;
        try (UsingBufferedInputStream reader = new UsingBufferedInputStream(filename)) {
            while ((k = reader.read(buffer, 0, buffer.length)) > 0) {
                blackhole.consume(k);
                blackhole.consume(buffer);
            }
        }
        return 0;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + FileReaderBenchmark.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}
