package com.zhj.spi.impl;

import com.zhj.spi.ActivateExt;
import org.apache.dubbo.common.extension.Activate;

/**
 * @author zhj on 2019-08-20.
 */
@Activate(value = {"val"}, group = {"value"})
public class ValueActivateExtImpl implements ActivateExt {

    @Override
    public String echo(String s) {
        System.out.println("value active : echo " + s);
        return s;
    }
}
