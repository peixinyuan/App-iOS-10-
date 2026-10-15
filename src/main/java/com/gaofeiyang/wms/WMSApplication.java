package com.gaofeiyang.wms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(WMSApplication.class, args);
        log.info("Warehouse Management System STARTED!");
        log.info("===================================================");
    }
}
