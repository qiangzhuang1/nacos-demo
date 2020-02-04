package com.springboot.nacosdemo.nacosdemo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

public class NacosSdkDemo {
    public static void main(String[] args) {
        //连接到目标服务的地址
        //指定dataId、 groupId
        String serverAddr = "192.168.32.129:8848";
        String dataId="nacos-demo";
        String groupId="DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, groupId, 3000);
            System.out.println(content);
            //注册监听，每当配置中心有变化是触发此执行
            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {
                    System.out.println("configInfo:"+s);
                }
            });
            System.in.read();
        } catch (NacosException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}