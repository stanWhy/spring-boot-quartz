package com.itstyle.quartz.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstyle.quartz.entity.QuartzEntity;
import com.itstyle.quartz.entity.Result;
import com.itstyle.quartz.service.IJobService;
@Api(tags ="Quartz任务")
@RestController
@RequestMapping("/job")
public class JobController {
	private final static Logger LOGGER = LoggerFactory.getLogger(JobController.class);
	

    @Autowired @Qualifier("Scheduler")
    private Scheduler scheduler;
    @Autowired
    private IJobService jobService;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="新建任务")
	@PostMapping("/add")
	public Result save(QuartzEntity quartz){
		LOGGER.info("新增任务");
		try {
			// 获取Scheduler实例
	        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
	        //如果是修改  展示旧的 任务
	        if(quartz.getOldJobGroup()!=null){
	        	JobKey key = new JobKey(quartz.getOldJobName(),quartz.getOldJobGroup());
	        	scheduler.deleteJob(key);
	        }
	        Class cls = Class.forName(quartz.getJobClassName()) ;
	        cls.newInstance();
	        //构建job信息
	        JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
	        		quartz.getJobGroup())
	        		.withDescription(quartz.getDescription()).build();
	        // 触发时间点
	        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
	        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+quartz.getJobName(), quartz.getJobGroup())
	                .startNow().withSchedule(cronScheduleBuilder).build();	
	        //交由Scheduler安排触发
	        scheduler.scheduleJob(job, trigger);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error();
		}
		return Result.ok();
	}
	@ApiOperation(value="任务列表")
	@PostMapping("/list")
	public Result list(QuartzEntity quartz,Integer pageNo,Integer pageSize){
		LOGGER.info("任务列表");
		List<QuartzEntity> list = jobService.listQuartzEntity(quartz, pageNo, pageSize);
		return Result.ok(list);
	}
	@ApiOperation(value="触发任务")
	@PostMapping("/trigger")
	public  Result trigger(QuartzEntity quartz,HttpServletResponse response) {
		try {
			 Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		     JobKey key = new JobKey(quartz.getJobName(),quartz.getJobGroup());
		     scheduler.triggerJob(key);
		} catch (SchedulerException e) {
			 e.printStackTrace();
			 return Result.error();
		}
		return Result.ok();
	}
}
