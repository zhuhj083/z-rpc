package com.zhj.spi.impl;

import com.zhj.spi.SimpleExt;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;

/**
 * @author zhj on 2019-08-20.
 */
@Adaptive
public class SimpleExtImpl1 implements SimpleExt {

    @Override
    public String echo(URL url, String s) {
        System.out.println("simple ext impl 1,echo: "+s);
        return s;
    }
}
