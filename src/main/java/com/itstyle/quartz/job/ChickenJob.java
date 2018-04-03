package com.itstyle.quartz.job;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ChickenJob implements  Job,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("大吉大利、今晚吃鸡");
	}
}
