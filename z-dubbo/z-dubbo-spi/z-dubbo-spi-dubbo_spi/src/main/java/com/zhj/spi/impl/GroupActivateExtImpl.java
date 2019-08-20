package com.zhj.spi.impl;

import com.zhj.spi.ActivateExt;
import org.apache.dubbo.common.extension.Activate;

/**
 * @author zhj on 2019-08-20.
 */
@Activate(group = {"group1", "group2"})
public class GroupActivateExtImpl implements ActivateExt {

    @Override
    public String echo(String s) {
        System.out.println("group 2 : echo " + s);
        return s;
    }
}
