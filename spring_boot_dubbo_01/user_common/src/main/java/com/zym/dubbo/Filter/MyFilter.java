package com.zym.dubbo.Filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.extension.SPI;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author wangliang
 * @Date 2019/12/6 0006
 * @Description  myFilter模拟请求白名单
 */
@SPI(MyFilter.NAME)
@Activate(group = {Constants.PROVIDER,Constants.CONSUMER})
public class MyFilter implements Filter {

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
