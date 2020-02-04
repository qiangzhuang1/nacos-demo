package com.springboot.nacosdemo.nacosdemo;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@NacosPropertySource(dataId = "nacos-demo",autoRefreshed = true)
@RestController
public class NacosConfigController {

    /**
     * 当前的info这个属性，回到 nacos-server 找到对应的 nacos.test.txt 这个属性
     * 111 表示本地属性（降级属性），为了防止 Nocos 挂掉之后获取不到值，所以采用默认的值 111
     */
    @NacosValue(value = "${nacos.test.txt:111}",autoRefreshed = true)
    private String info;

    @GetMapping("/getConfig")
    public String getConfig(){
        return info;
    }
}