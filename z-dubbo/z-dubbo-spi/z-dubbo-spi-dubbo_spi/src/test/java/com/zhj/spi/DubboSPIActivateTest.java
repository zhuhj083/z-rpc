package com.zhj.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author zhj on 2019-08-20.
 */
public class DubboSPIActivateTest {

    @Test
    public void testDefault() {
        ExtensionLoader<ActivateExt> extensionLoader = ExtensionLoader.getExtensionLoader(ActivateExt.class);
        URL url = URL.valueOf("test://localhost/test");
        //查询组为default_group的ActivateExt1的实现
        List<ActivateExt> list = extensionLoader.getActivateExtension(url, new String[]{}, "default_group");

        System.out.println(list.size());
        System.out.println(list.get(0).getClass());

        for (ActivateExt ext : list) {
            ext.echo("zhj");
        }
    }

    @Test
    public void testGroup() {
        ExtensionLoader<ActivateExt> extensionLoader = ExtensionLoader.getExtensionLoader(ActivateExt.class);
        URL url = URL.valueOf("test://localhost/test");
        //查询组为default_group的ActivateExt1的实现
        List<ActivateExt> list = extensionLoader.getActivateExtension(url, new String[]{}, "group1");

        System.out.println(list.size());
        System.out.println(list.get(0).getClass());

        for (ActivateExt ext : list) {
            ext.echo("zhj");
        }
    }

    @Test
    public void testValue() {
        URL url = URL.valueOf("test://localhost/test");
        //根据   key = value1,group =  value
        //@Activate(value = {"value1"}, group = {"value"})来激活扩展
        url = url.addParameter("val", "val2");
        List<ActivateExt> list = ExtensionLoader.getExtensionLoader(ActivateExt.class).getActivateExtension(url, new String[]{}, "value");
        for (ActivateExt ext : list) {
            ext.echo("zhj");
        }
    }

    /**
     * @Activate注解声明了order，低的排序优先级高
     */
    @Test
    public void testOrder() {
        URL url = URL.valueOf("test://localhost/test");
        List<ActivateExt> list = ExtensionLoader.getExtensionLoader(ActivateExt.class).getActivateExtension(url, new String[]{}, "order");

        System.out.println(list.size());
        System.out.println(list.get(0).getClass());
        System.out.println(list.get(1).getClass());
    }

    @Test
    public void testBeforeAndAfter(){
        URL url = URL.valueOf("test://localhost/test");
        url = url.addParameter("val", "val2");

        ExtensionLoader<ActivateExt> extensionLoader = ExtensionLoader.getExtensionLoader(ActivateExt.class);
        List<ActivateExt> list = extensionLoader.getActivateExtension(url, new String[]{}, "default_group");

        System.out.println(list.size());
        for (ActivateExt ext : list) {
            ext.echo("zhj");
        }
    }
}
