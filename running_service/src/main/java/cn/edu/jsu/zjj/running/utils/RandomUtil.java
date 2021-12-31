package cn.edu.jsu.zjj.running.utils;

import java.util.Random;

/**
 * 随机生成八位数的房间号
 */
public class RandomUtil {

    public static String random(){
        Random random = new Random();
        Integer num = 100000 + random.nextInt(900000);
        return String.valueOf(num);
    }

}
