package com.zhj.echo;

import com.zhj.echo.api.EchoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author zhj on 2019-08-17.
 */
public class EchoApiConsumer {

    public static void main(String[] args) {
        ReferenceConfig<EchoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("java-echo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(EchoService.class);

        EchoService greetingService = referenceConfig.get();

        int count = 0;
        while (true){
            String message = greetingService.echo("Hello count_"+ count++);
            System.out.println(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
