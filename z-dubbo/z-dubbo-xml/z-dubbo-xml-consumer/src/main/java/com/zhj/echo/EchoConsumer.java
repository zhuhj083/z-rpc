package com.zhj.echo;

import com.zhj.echo.api.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhj on 2019-08-16.
 */
public class EchoConsumer {

    public static void main(String[] args) {
        //指定服务暴露配置文件
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"spring/echo-consumer.xml"});
        //启动Spring容器并暴露服务
        context.start();

        EchoService echoService = context.getBean("echoService",EchoService.class);
        String status = echoService.echo("zhj");
        System.out.println("echo result: "+ status);
    }

}
