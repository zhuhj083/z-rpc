package com.zhj.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author zhj on 2019-08-19.
 */
public class JavaSPITest {

    @Test
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        for (Robot robot : serviceLoader){
            robot.sayHello();
        }
    }
}
