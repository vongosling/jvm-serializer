package com.creative.commons.utils.benchmark;

import com.creative.commons.utils.unittest.MsgPackCodecTest;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;

/**
 * @author xinyuzhou.zxy
 */
public class MsgPackBenchmark extends MsgPackCodecTest {
    @Benchmark
    public void msgPackCodecMultiTest() throws IOException {
        msgPackEncodeAndDecode();
    }
}
