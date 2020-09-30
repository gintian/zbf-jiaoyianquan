package com.es.esconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 作者: LCG
 * 日期: 2020/7/28 09:03
 * 描述:
 */
@ConfigurationProperties(prefix = "es.zk")
@Data
@Component
public class EsZkConfig {

    private String list;

    private String namespace;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
