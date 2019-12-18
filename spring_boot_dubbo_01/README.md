# 简介

本项目主要用于测试springBoot整合dubbo、分布式服务的构建的基本过程、spi的演示(自定义服务访问的过滤器)

# 项目构成

## 模块简介
    1.user-api:dubbo的服务暴露层
    2.user_common:公共层
    3.user-provider:服务提供者
    4.user_consumer:服务消费者
    
## 主要jar包
```xml
<!-- dubbo 2.0.0-->
<dependency>
    <groupId>com.alibaba.spring.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
</dependency>
<!-- zookeeper 0.11-->
<dependency>
    <groupId>com.101tec</groupId>
    <artifactId>zkclient</artifactId>
</dependency>
```
# DUBBO SPI
    本示例主要通过dubbo的spi，自定义过滤器，实现对远程服务访问的控制
    1.自定义一个过滤器实现Filter接口
```java
/**
 * @Author wangliang
 * @Date 2019/12/6 0006
 * @Description  myFilter模拟请求白名单
 */
@SPI(MyFilter.NAME)
//服务提供者和消费者都起效
@Activate(group = {Constants.PROVIDER,Constants.CONSUMER})
public class MyFilter implements Filter {
    //过滤器名称
    public static final String NAME = "myFilter";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        Logger logger = LoggerFactory.getLogger(MyFilter.class);
        //白名单(ipwhitelist.properties)
        Properties properties = new Properties();
        logger.info("to my filter!");
        InputStream in = MyFilter.class.getResourceAsStream("/ipwhitelist.properties");
        //客户端ip
        String clientIP = RpcContext.getContext().getRemoteHost();
        try {
            properties.load(in);
            String ipwhitelist = properties.getProperty("ipwhitelist");
            if (ipwhitelist.contains(clientIP)){
                return invoker.invoke(invocation);
            }else {
                return new RpcResult(new Exception("ip地址："
                        + clientIP + "没有访问权限"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invoker.invoke(invocation);

    }
}
```
    2.在resource文件夹下创建META-INF/dubbo文件夹
    3.在META-INF/dubbo下创建文件，文件名为Filter接口的全路径，例：com.alibaba.dubbo.rpc.Filter
    4.文件的内容为键值对形式，key的值为自定义的过滤器名称,值为自定义过滤器的全路径,例：myFilter=com.zym.dubbo.Filter.MyFilter
    


