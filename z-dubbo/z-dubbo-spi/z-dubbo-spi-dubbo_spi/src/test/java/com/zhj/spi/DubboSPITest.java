package com.zhj.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @author zhj on 2019-08-19.
 */
public class DubboSPITest {

    @Test
    public void PrintInfo() throws Exception {

        ExtensionLoader<PrintService> extensionLoader = ExtensionLoader.getExtensionLoader(PrintService.class);
        URL url = URL.valueOf("test://localhost/test?x=ad2");
        PrintService printService ;

        printService = extensionLoader.getExtension("impl1");
        printService.printInfo(url);

        //test default extension
//        printService = extensionLoader.getExtension("true");
//        printService.printInfo(url);

        printService = extensionLoader.getAdaptiveExtension();
        printService.printInfo(url);


        //@Adaptive test
        SimpleExt ext = ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
        URL simpleUrl = URL.valueOf("dubbo://192.1.1.4:1010/path1?simple.ext=hhh");
        System.out.println(ext.getClass().getName());
        ext.echo(simpleUrl,"hello");



    }
}
