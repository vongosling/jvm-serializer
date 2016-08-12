package com.creative.commons.utils.benchmark;

import com.creative.commons.utils.unittest.JdkCodecTest;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author xinyuzhou.zxy
 */
public class JdkCodecBencmark extends JdkCodecTest{
    @Benchmark
    public void javaCodecMultiTest() throws Exception {
        javaEncodeAndDecode(msg1);
        javaEncodeAndDecode(msg2);
        javaEncodeAndDecode(msg3);
    }
}
