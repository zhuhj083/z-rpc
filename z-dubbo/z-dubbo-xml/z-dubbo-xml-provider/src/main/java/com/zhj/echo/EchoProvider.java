package com.zhj.echo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author zhj on 2019-08-15.
 */
public class EchoProvider {
    public static void main(String[] args) throws IOException {
        //指定服务暴露配置文件
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"spring/echo-provider.xml"});
        //启动Spring容器并暴露服务
        context.start();

        System.in.read();
    }
}
