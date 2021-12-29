package cn.edu.jsu.zjj.running;

import cn.edu.jsu.zjj.running.user.dao.UserDao;
import cn.edu.jsu.zjj.running.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RunningApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    void contextLoads() {
        User user=userDao.findUserByApplyId(1);
        System.out.println(user);
    }

}
