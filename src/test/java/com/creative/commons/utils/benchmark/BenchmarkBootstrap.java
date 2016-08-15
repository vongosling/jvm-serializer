package com.creative.commons.utils.benchmark;

import com.creative.commons.utils.unittest.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * @author xinyuzhou.zxy
 */
public class BenchmarkBootstrap {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MsgPackCodecTest.class.getSimpleName())
                .include(KryoCodecTest.class.getSimpleName())
                .include(FastJsonCodecTest.class.getSimpleName())
                .include(JdkCodecTest.class.getSimpleName())
                .include(HessianCodecTest.class.getSimpleName())
                .forks(1)
                .measurementIterations(10).measurementTime(new TimeValue(5, TimeUnit.SECONDS))
                .warmupIterations(10).warmupTime(new TimeValue(5, TimeUnit.SECONDS))
                .build();

        new Runner(opt).run();
    }
}
