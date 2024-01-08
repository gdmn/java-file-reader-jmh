# java-file-reader-jmh

[See my blog for description](https://devsite.pl/blog.2024-01-08.java-file-reader-jmh.html)

## Benchmark result

```
# JMH version: 1.37
# VM version: JDK 21, OpenJDK 64-Bit Server VM, 21+35
# VM invoker: /usr/lib/jvm/java-21-openjdk/bin/java
# VM options: -Dvisualvm.id=39367283557059 -javaagent:/home/dmn/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/lib/idea_rt.jar=41717:/home/dmn/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: 1 iterations, 1 s each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: pl.devsite.FileReaderBenchmark.bufferedInputStream

# Run progress: 0.00% complete, ETA 00:00:48
# Fork: 1 of 1
# Warmup Iteration   1: 0.139 s/op
Iteration   1: 0.140 s/op
Iteration   2: 0.139 s/op
Iteration   3: 0.145 s/op
Iteration   4: 0.141 s/op
Iteration   5: 0.144 s/op


Result "pl.devsite.FileReaderBenchmark.bufferedInputStream":
0.142 ±(99.9%) 0.010 s/op [Average]
(min, avg, max) = (0.139, 0.142, 0.145), stdev = 0.003
CI (99.9%): [0.132, 0.151] (assumes normal distribution)


# JMH version: 1.37
# VM version: JDK 21, OpenJDK 64-Bit Server VM, 21+35
# VM invoker: /usr/lib/jvm/java-21-openjdk/bin/java
# VM options: -Dvisualvm.id=39367283557059 -javaagent:/home/dmn/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/lib/idea_rt.jar=41717:/home/dmn/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: 1 iterations, 1 s each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: pl.devsite.FileReaderBenchmark.bufferedMemory

# Run progress: 33.33% complete, ETA 00:00:34
# Fork: 1 of 1
# Warmup Iteration   1: 0.093 s/op
Iteration   1: 0.093 s/op
Iteration   2: 0.091 s/op
Iteration   3: 0.092 s/op
Iteration   4: 0.093 s/op
Iteration   5: 0.094 s/op


Result "pl.devsite.FileReaderBenchmark.bufferedMemory":
0.093 ±(99.9%) 0.005 s/op [Average]
(min, avg, max) = (0.091, 0.093, 0.094), stdev = 0.001
CI (99.9%): [0.088, 0.098] (assumes normal distribution)


# JMH version: 1.37
# VM version: JDK 21, OpenJDK 64-Bit Server VM, 21+35
# VM invoker: /usr/lib/jvm/java-21-openjdk/bin/java
# VM options: -Dvisualvm.id=39367283557059 -javaagent:/home/dmn/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/lib/idea_rt.jar=41717:/home/dmn/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: 1 iterations, 1 s each
# Measurement: 5 iterations, 3 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: pl.devsite.FileReaderBenchmark.readRandom

# Run progress: 66.67% complete, ETA 00:00:19
# Fork: 1 of 1
# Warmup Iteration   1: 1.404 s/op
Iteration   1: 1.698 s/op
Iteration   2: 2.073 s/op
Iteration   3: 2.108 s/op
Iteration   4: 2.008 s/op
Iteration   5: 2.047 s/op


Result "pl.devsite.FileReaderBenchmark.readRandom":
1.987 ±(99.9%) 0.637 s/op [Average]
(min, avg, max) = (1.698, 1.987, 2.108), stdev = 0.165
CI (99.9%): [1.350, 2.624] (assumes normal distribution)


# Run complete. Total time: 00:01:00

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

NOTE: Current JVM experimentally supports Compiler Blackholes, and they are in use. Please exercise
extra caution when trusting the results, look into the generated code to check the benchmark still
works, and factor in a small probability of new VM bugs. Additionally, while comparisons between
different JVMs are already problematic, the performance difference caused by different Blackhole
modes can be very significant. Please make sure you use the consistent Blackhole mode for comparisons.

Benchmark                                Mode  Cnt  Score   Error  Units
FileReaderBenchmark.bufferedInputStream  avgt    5  0.142 ± 0.010   s/op
FileReaderBenchmark.bufferedMemory       avgt    5  0.093 ± 0.005   s/op
FileReaderBenchmark.readRandom           avgt    5  1.987 ± 0.637   s/op

```