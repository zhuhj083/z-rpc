package com.zhj.spi.impl;

import com.zhj.spi.SimpleExt;
import org.apache.dubbo.common.URL;

/**
 * @author zhj on 2019-08-20.
 */
public class SimpleExtImpl2 implements SimpleExt {

    @Override
    public String echo(URL url, String s) {
        System.out.println("simple ext impl 2,echo: "+s);
        return s;
    }
}
