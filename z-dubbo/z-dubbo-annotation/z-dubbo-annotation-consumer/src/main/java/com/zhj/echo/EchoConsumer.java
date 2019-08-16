package com.zhj.echo;

import com.zhj.echo.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author zhj on 2019-08-17.
 */
@Component
public class EchoConsumer {

    @Reference
    private EchoService echoService;

    public String echo(String name){
        return echoService.echo(name);
    }
}
