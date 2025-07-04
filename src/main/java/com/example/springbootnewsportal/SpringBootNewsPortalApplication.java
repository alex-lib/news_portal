package com.example.springbootnewsportal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootNewsPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNewsPortalApplication.class, args);
    }
}