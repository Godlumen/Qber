package com.godlumen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
//@MapperScan("com.godlumen.mapper")//不用在每个mapper类上@Mapper
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
