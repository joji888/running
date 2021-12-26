package cn.edu.jsu.zjj.running.utils;

import cn.edu.jsu.zjj.running.config.MyFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务工具包
 */

@Component
@EnableScheduling
public class TaskUtils {

    /**
     * 每天零点定时清理无效token
     */
    @Scheduled(cron="0 0 02 * * ?")
    public void TokenClear(){
        System.out.println("开始");
        for (String k: MyFilter.tokenMap.keySet()){
            System.out.println(k);
        }
    }

}
