package com.yfrao.tools.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    @Autowired
    @Qualifier("commodityJdbcTemplate")
    private JdbcTemplate commodityJdbcTemplate;

    //3.添加定时任务
    @Scheduled(cron = "0 5,35 9,10,11,13,14,15,16,17,18,19,20 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        String sql = "update b_commodity_price set is_deleted = 0 where id = 173029";
        commodityJdbcTemplate.execute(sql);
        System.out.println("执行成功");
    }
}
