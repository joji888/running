package cn.edu.jsu.zjj.running;

import cn.edu.jsu.zjj.running.utils.MailSend;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RunningApplicationTests {

    @Resource
    private MailSend mailSend;

    @Test
    void contextLoads() {
        mailSend.send("963805941@qq.com","测试");
    }

}
