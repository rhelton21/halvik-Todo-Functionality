package com.halvik.rest.webservices.restfulwebservices.configuration.job;

import com.halvik.rest.webservices.restfulwebservices.configuration.JobConfiguration;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class PrintJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(PrintJob.class);

    @Autowired
    JobConfiguration jobConfiguration;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());
       jobConfiguration.job();
    }

}
