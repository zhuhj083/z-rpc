package com.zhj.spi.impl;

import com.zhj.spi.ActivateExt;
import org.apache.dubbo.common.extension.Activate;

/**
 * @author zhj on 2019-08-20.
 */
@Activate(group = {"default_group"})
public class DefaultActivateExtImpl implements ActivateExt {

    @Override
    public String echo(String s) {
        System.out.println("active 1 : echo " + s);
        return s;
    }
}
