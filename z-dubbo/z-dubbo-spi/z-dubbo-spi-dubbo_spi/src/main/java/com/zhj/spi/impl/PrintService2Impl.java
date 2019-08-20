package com.zhj.spi.impl;

import com.zhj.spi.PrintService;
import org.apache.dubbo.common.URL;

/**
 * @author zhj on 2019-08-19.
 */
public class PrintService2Impl implements PrintService {

    @Override
    public void printInfo(URL url) {
        System.out.println("2 print info");
    }
}
