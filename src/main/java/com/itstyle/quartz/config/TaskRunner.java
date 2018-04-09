package com.itstyle.quartz.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/**
 * 任务延时启动(废弃)
 * 创建者 科帮网
 * 创建时间	2018年4月3日
 */
@Component
public class TaskRunner implements ApplicationRunner{

    @Override
    public void run(ApplicationArguments var) throws Exception{
    	//Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		//scheduler.startDelayed(5);//延时5秒启动
    }

}