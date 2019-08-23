package com.zhj.service.impl;

import com.zhj.service.HelloService;

/**
 * @author zhj on 2019-08-22.
 */
public class HelloServiceImplA implements HelloService {

    @Override
    public void doSomething() {
        System.out.println("Hello A");
    }
}
