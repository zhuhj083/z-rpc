package com.zhj;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import tutorial.Calculator;

/**
 * @author zhj on 2019-08-15.
 */
public class ThriftClientTest {

    private static final int MAX = 100000;
    private static String[] types = {"simple","nonblocking","threadpool","hsha","threadpollSelector","secure"};

    public static void main(String[] args) throws TException {
        for (String type : types){
            try {
                TTransport transport;

                if (type.contains("simple")) {
                    transport = new TSocket("localhost", 9090);
                    transport.open();
                }else if (type.contains("nonblocking")) {
                    transport = new TSocket("localhost", 9091);
                    transport = new TFramedTransport(transport);
                    transport.open();
                }else if (type.contains("threadpool")) {
                    transport = new TSocket("localhost", 9092);
                    transport.open();
                }else if (type.contains("hsha")) {
                    transport = new TSocket("localhost", 9093);
                    transport = new TFramedTransport(transport);
                    transport.open();
                }else if (type.contains("threadpollSelector")) {
                    transport = new TSocket("localhost", 9094);
                    transport = new TFramedTransport(transport);
                    transport.open();
                }else {
                    TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
                    params.setTrustStore(ThriftClientTest.class.getClassLoader().getResource(".").getPath()+ ".keystore", "thrift", "SunX509", "JKS");
                    transport = TSSLTransportFactory.getClientSocket("localhost", 9095, 0, params);
                }

                TProtocol protocol = new  TBinaryProtocol(transport);
                Calculator.Client client = new Calculator.Client(protocol);
                perform(client,type);
                transport.close();

                Thread.sleep(3000);

            } catch (TException x) {
                x.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void perform(Calculator.Client client,String type) throws TException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX ; i++) {
            client.ping();
            client.add(55,55);
        }
        long end = System.currentTimeMillis();
        long elapse = end - start;
        int perfrom = Double.valueOf( MAX / (elapse / 1000d) ).intValue();

        System.out.println(type + "方式的server，"+"thrift "+ MAX +" 次RPC调用，耗时："+ elapse +"毫秒，平均："+perfrom+"次/秒");
    }

}
