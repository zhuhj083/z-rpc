package com.zhj.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author zhj on 2019-08-20.
 */
@SPI
public interface ActivateExt {
    String echo(String s);
}
