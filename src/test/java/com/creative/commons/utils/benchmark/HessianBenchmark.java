package com.creative.commons.utils.benchmark;

import com.creative.commons.utils.unittest.HessianCodecTest;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author xinyuzhou.zxy
 */
public class HessianBenchmark extends HessianCodecTest{
    @Benchmark
    public void hessianCodecMultiTest() throws Throwable {
        hessianEncodeAndDecode();
    }
}
