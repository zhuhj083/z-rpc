package com.zhj.spi.impl;

import com.zhj.spi.PrintService;
import org.apache.dubbo.common.URL;

/**
 * @author zhj on 2019-08-19.
 */
public class PrintServiceImpl implements PrintService {

    @Override
    public void printInfo(URL url) {
        System.out.println("PrintServiceImpl print info");
    }
}
