package com.tong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName JobStart
 * @Description TODO
 * @Author Administrator
 * @Date 2020/9/28 0028 下午 7:03
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient

public class JobStart {
    public static void main(String[] args) {
        SpringApplication.run(JobStart.class);
    }
}
