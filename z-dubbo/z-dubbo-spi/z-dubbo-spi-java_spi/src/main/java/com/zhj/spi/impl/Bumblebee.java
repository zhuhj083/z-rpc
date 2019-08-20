package com.zhj.spi.impl;

import com.zhj.spi.Robot;

/**
 * @author zhj on 2019-08-19.
 */
public class Bumblebee implements Robot {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
