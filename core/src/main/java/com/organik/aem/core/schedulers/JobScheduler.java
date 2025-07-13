package com.organik.aem.core.schedulers;

import org.apache.sling.event.jobs.JobManager;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

@Component(immediate = true)
public class JobScheduler {

    @Reference
    private Scheduler scheduler;

    @Reference
    private JobManager jobManager;

    private static final String JOB_TOPIC = "country/api/job";

    @Activate
    protected void activate() {
        scheduleJob("India", "0 0 0 * * ?");     // 12 AM India
        scheduleJob("USA", "0 0 0 * * ?");       // 12 AM USA (server time based)
        scheduleJob("Russia", "0 0 0 * * ?");    // 12 AM Russia (server time based)
    }

    private void scheduleJob(String country, String cronExpr) {
    ScheduleOptions options = scheduler.EXPR(cronExpr);
    options.name("country.job." + country).canRunConcurrently(false);

    scheduler.schedule(new Runnable() {
        @Override
        public void run() {
            Map<String, Object> jobProps = new HashMap<>();
            jobProps.put("country", country);
            jobManager.addJob(JOB_TOPIC, jobProps);
        }
    }, options);
    
    }
}
