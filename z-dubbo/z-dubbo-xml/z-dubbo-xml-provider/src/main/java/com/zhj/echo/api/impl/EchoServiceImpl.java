package com.zhj.echo.api.impl;

import com.zhj.echo.api.EchoService;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhj on 2019-08-16.
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String message) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("["+now+"]Echo " + message + ",request from consumer:"+
                RpcContext.getContext().getRemoteAddress());
        return message;
    }
}
