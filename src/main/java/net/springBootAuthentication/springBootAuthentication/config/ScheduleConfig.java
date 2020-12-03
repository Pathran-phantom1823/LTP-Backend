package net.springBootAuthentication.springBootAuthentication.config;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import net.springBootAuthentication.springBootAuthentication.repository.RegisterRepository;

@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer{
	
	@Autowired
	RegisterRepository register;
	
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("TaskScheduler");
		scheduler.setPoolSize(10);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		scheduler.setAwaitTerminationSeconds(20);
		return scheduler;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
	    taskRegistrar.setScheduler(taskScheduler());
	    taskRegistrar.addTriggerTask(new Runnable() {
	        @Override
	        public void run() {
	        	System.out.println("------------- T E S T I N G  S C H E D U L E! ---------------");
	        	register.updateExpiration();
	                // Code which which should run at the specified executionTime( specified in nextExecutionTime(TriggerContext triggerContext))
	        }
	    }, new Trigger() {
	        @Override
	        public Date nextExecutionTime(TriggerContext triggerContext) {
	            Calendar nextExecutionTime = new GregorianCalendar();
	            Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
	            nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
	            nextExecutionTime.add(Calendar.DATE, 1); // runs everyday
	            return nextExecutionTime.getTime();
	        }
	    });
	}
}
