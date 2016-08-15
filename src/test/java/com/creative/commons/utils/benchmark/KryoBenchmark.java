package com.creative.commons.utils.benchmark;

import com.creative.commons.utils.unittest.KryoCodecTest;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author xinyuzhou.zxy
 */
public class KryoBenchmark extends KryoCodecTest{

    @Benchmark
    public void kryoCodecMultiTest() throws Exception {
        kryoEncodeAndDecode();
    }
}
