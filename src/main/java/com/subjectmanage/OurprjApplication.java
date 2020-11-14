package com.subjectmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching(proxyTargetClass = true)
@EnableScheduling()
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OurprjApplication {

    public static void main(String[] args) {
        SpringApplication.run( OurprjApplication.class, args );
    }

}
