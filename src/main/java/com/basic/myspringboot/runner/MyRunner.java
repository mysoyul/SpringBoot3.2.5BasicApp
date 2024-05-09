package com.basic.myspringboot.runner;

import com.basic.myspringboot.config.vo.CustomerVO;
import com.basic.myspringboot.property.MybootProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Autowired
    private Environment environment;

    @Autowired
    private MybootProperties properties;

    @Autowired
    private CustomerVO customer;

    private Logger logger = LoggerFactory.getLogger(MyRunner.class);

    public void run(ApplicationArguments args) throws Exception {
        logger.info("Logger 구현객체 클래스 이름 = {}",logger.getClass().getName());
        logger.info("Environment ${spring.profiles.active} = {}",
                environment.getProperty("spring.profiles.active"));
        logger.info("CustomerVO Bean = {}", customer);
        logger.info("MybootProperties = " + properties.getFullName());
        logger.info("@Value ${myboot.name} = " + name);
        logger.info("@Value ${myboot.age} = " + age);
        logger.info("Environment ${myboot.fullName} = " +
                environment.getProperty("myboot.fullName"));
        logger.info("Environment ${local.server.port} = " +
                environment.getProperty("local.server.port"));

        logger.debug("VM argument foo : " + args.containsOption("foo"));
        logger.debug("Program argument bar : " + args.containsOption("bar"));

        args.getOptionNames()//Set<String>
                .forEach(name -> logger.info("환경변수 name = {}", name));  //Lambda Expression
                //.forEach(System.out::println);  //Method Reference

    }
}