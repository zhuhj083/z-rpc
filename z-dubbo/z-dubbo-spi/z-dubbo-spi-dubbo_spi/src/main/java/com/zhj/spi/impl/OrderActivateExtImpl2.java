package com.zhj.spi.impl;

import com.zhj.spi.ActivateExt;
import org.apache.dubbo.common.extension.Activate;

/**
 * @author zhj on 2019-08-20.
 */
@Activate(order = 2,group = {"order"})
public class OrderActivateExtImpl2 implements ActivateExt {

    @Override
    public String echo(String s) {
        System.out.println("order 2 : echo " + s);
        return s;
    }
}
