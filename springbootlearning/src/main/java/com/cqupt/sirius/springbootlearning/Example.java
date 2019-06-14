package com.cqupt.sirius.springbootlearning;
import com.mysql.jdbc.Driver;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.sql.DriverManager;

@RestController
@EnableAutoConfiguration
public class Example {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
        Driver
        DriverManager.getConnection();
    }
}
