package cn.edu.jsu.zjj.running;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.edu.jsu.zjj.running.*.dao")
@SpringBootApplication
public class RunningApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningApplication.class, args);
    }

}
