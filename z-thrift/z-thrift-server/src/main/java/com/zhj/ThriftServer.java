package com.zhj;

import com.zhj.handler.CalculatorHandler;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.*;
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
                    simpleServer(processor);
                }
            };

            Runnable nonblocking = new Runnable() {
                @Override
                public void run() {
                    nonblokingServer(processor);
                }
            };

            Runnable threadPool = new Runnable() {
                @Override
                public void run() {
                    threadPoolServer(processor);
                }
            };

            Runnable hsha = new Runnable() {
                @Override
                public void run() {
                    hsHaServer(processor);
                }
            };

            Runnable threadpoolSelector = new Runnable() {
                @Override
                public void run() {
                    threadedSelectorServer(processor);
                }
            };

            Runnable secure = new Runnable() {
                @Override
                public void run() {
                    secure(processor);
                }
            };

            new Thread(simple).start();
            new Thread(nonblocking).start();
            new Thread(threadPool).start();
            new Thread(hsha).start();
            new Thread(threadpoolSelector).start();
            new Thread(secure).start();
        } catch (Exception x) {
            x.printStackTrace();
        }

    }

    public static void simpleServer(Calculator.Processor processor){
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server in port 9090...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void nonblokingServer(Calculator.Processor processor){
        try {
            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(9091);
            TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
            TNonblockingServer.Args serverArgs = new TNonblockingServer.Args(serverSocket)
                    .processor(processor)
                    .protocolFactory(protocolFactory);
            TServer server = new TNonblockingServer(serverArgs);
            System.out.println("Starting the Nonbloking server in port 9091...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void threadPoolServer(Calculator.Processor processor){
        try {
            TServerSocket serverSocket = new TServerSocket(9092);
            TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverSocket)
                    .processor(processor)
                    .protocolFactory(protocolFactory);
            TServer server = new TThreadPoolServer(serverArgs);
            System.out.println("Starting the threadpool server in port 9092...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void hsHaServer(Calculator.Processor processor){
        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(9093);
            TTransportFactory transportFactory = new TFramedTransport.Factory();
            TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
            THsHaServer.Args serverArgs = new THsHaServer.Args(serverTransport)
                    .processor(processor)
                    .protocolFactory(protocolFactory)
                    .transportFactory(transportFactory);
            TServer server = new THsHaServer(serverArgs);
            System.out.println("Starting the hsha server in port 9093...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void threadedSelectorServer(Calculator.Processor processor){
        try {
            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(9094);
            TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
            TThreadedSelectorServer.Args serverArgs = new TThreadedSelectorServer.Args(serverSocket)
                    .processor(processor)
                    .protocolFactory(protocolFactory);
            TServer server = new TThreadedSelectorServer(serverArgs);
            System.out.println("Starting the thread Selector server in port 9094...");
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
            TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(9095, 0, null, params);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            // Use this for a multi threaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the secure server in port 9095...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
