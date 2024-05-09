package com.basic.myspringboot.runner;

import com.basic.myspringboot.config.vo.CustomerVO;
import com.basic.myspringboot.property.MybootProperties;
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

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Environment ${spring.profiles.active} = " +
                environment.getProperty("spring.profiles.active"));
        System.out.println("CustomerVO Bean = " + customer);
        System.out.println("MybootProperties = " + properties.getFullName());
        System.out.println("@Value ${myboot.name} = " + name);
        System.out.println("@Value ${myboot.age} = " + age);
        System.out.println("Environment ${myboot.fullName} = " +
                environment.getProperty("myboot.fullName"));
        System.out.println("Environment ${local.server.port} = " +
                environment.getProperty("local.server.port"));

        System.out.println("VM argument foo : " + args.containsOption("foo"));
        System.out.println("Program argument bar : " + args.containsOption("bar"));

        args.getOptionNames()//Set<String>
                //.forEach(name -> System.out.println(name));  //Lambda Expression
                .forEach(System.out::println);  //Method Reference

    }
}