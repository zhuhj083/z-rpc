package com.zhj.spi.impl;

import com.zhj.spi.Robot;

/**
 * @author zhj on 2019-08-19.
 */
public class OptimusPrime implements Robot {

    /**
     * Optimus 是擎天柱的英文名
     */
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Optimus Prime.");
    }
}
