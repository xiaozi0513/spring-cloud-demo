package com.wh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerBusApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerBusApplication.class, args);
    }
}
