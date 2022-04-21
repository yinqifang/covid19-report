package com.yinqifang.covid19report;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yinqifang.covid19report.mapper")
public class Covid19ReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(Covid19ReportApplication.class, args);
    }

}
