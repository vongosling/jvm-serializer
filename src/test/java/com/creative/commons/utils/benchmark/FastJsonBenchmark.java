package com.creative.commons.utils.benchmark;

import com.creative.commons.utils.unittest.FastJsonCodecTest;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;

/**
 * @author xinyuzhou.zxy
 */
public class FastJsonBenchmark extends FastJsonCodecTest{
    @Benchmark
    public void jsonCodecMultiTest() throws IOException {
        jsonEncodeAndDecode();
    }
}
