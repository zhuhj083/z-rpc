package com.zhj.echo;

import com.zhj.echo.api.EchoService;
import com.zhj.echo.api.impl.EchoServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author zhj on 2019-08-17.
 */
public class EchoApiProvider {

    public static void main(String[] args) throws IOException {
        ServiceConfig<EchoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("java-echo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        serviceConfig.setInterface(EchoService.class);
        serviceConfig.setRef(new EchoServiceImpl());
        serviceConfig.export();//暴露服务
        System.out.println("java-echo-provider is running.");
        System.in.read();
    }
}
