# SPI
Service Provider Interface

## Java_SPI
模块z-dubbo-spi-java_spi

## dubbo_SPI
模块z-dubbo-spi-dubbo_spi

## dubbo_SPI的改进
* JDK标准的SPI会一次性实例化扩展点的所有实现，浪费资源
* 如果扩展失败，则连扩展的名称都获取不到了
* 增加了对扩展的IoC和AOP的支持，一个扩展可以直接setter注入其他扩展
    * java.util.ServiceLoader会一次把接口下的所有实现类全部初始化，用户直接调用即可
    * dubbo SPI只是加载配置文件中的类，并分成不同的种类缓存在内存中，而不会立即全部初始化，在性能上有更好的表现