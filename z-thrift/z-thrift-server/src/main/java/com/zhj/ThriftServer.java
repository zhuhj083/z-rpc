package com.zhj;

import com.zhj.handler.CalculatorHandler;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import shared.SharedStruct;
import tutorial.Calculator;

/**
 * @author zhj on 2019-08-15.
 */
public class ThriftServer {

    public static CalculatorHandler handler;
    public static Calculator.Processor processor;

    public static void main(String[] args) {
        try {
            handler = new CalculatorHandler();
            processor = new Calculator.Processor(handler);

            Runnable simple = new Runnable() {
                @Override
                public void run() {
                    simple(processor);
                }
            };
            Runnable secure = new Runnable() {
                @Override
                public void run() {
                    secure(processor);
                }
            };

            new Thread(simple).start();
            new Thread(secure).start();
        } catch (Exception x) {
            x.printStackTrace();
        }

    }

    public static void simple(Calculator.Processor processor){
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void secure(Calculator.Processor processor) {
        try {
            /*
             * Use TSSLTransportParameters to setup the required SSL parameters. In this example
             * we are setting the keystore and the keystore password. Other things like algorithms,
             * cipher suites, client auth etc can be set.
             */
            TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();

            System.out.println(SharedStruct.class.getClassLoader().getResource("."));

            // The Keystore contains the private key
            params.setKeyStore( ThriftServer.class.getClassLoader().getResource(".").getPath()+ ".keystore", "thrift", null, null);

            /*
             * Use any of the TSSLTransportFactory to get a server transport with the appropriate
             * SSL configuration. You can use the default settings if properties are set in the command line.
             * Ex: -Djavax.net.ssl.keyStore=.keystore and -Djavax.net.ssl.keyStorePassword=thrift
             *
             * Note: You need not explicitly call open(). The underlying server socket is bound on return
             * from the factory class.
             */
            TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(9091, 0, null, params);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            // Use this for a multi threaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the secure server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
