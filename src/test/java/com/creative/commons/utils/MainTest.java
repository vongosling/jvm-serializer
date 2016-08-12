package com.creative.commons.utils;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author xinyuzhou.zxy
 */
public class MainTest {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MsgPackCodecTest.class.getSimpleName())
                .include(KryoCodecTest.class.getSimpleName())
                .include(JsonCodecTest.class.getSimpleName())
                .include(JdkCodecTest.class.getSimpleName())
                .include(HessianCodecTest.class.getSimpleName())
                .forks(2).measurementIterations(
                10).warmupIterations(10).build();

        new Runner(opt).run();
    }
}
