package com.zhj.spi.impl;

import com.zhj.spi.ActivateExt;
import org.apache.dubbo.common.extension.Activate;

/**
 * @author zhj on 2019-08-20.
 */
@Activate(value = {"val"}, group = {"default_group"},after = {"default"})
public class BeforeActivateExtImpl implements ActivateExt {

    @Override
    public String echo(String s) {
        System.out.println("before 1 : echo " + s);
        return s;
    }
}
