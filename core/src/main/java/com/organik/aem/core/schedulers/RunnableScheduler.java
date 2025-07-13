package com.organik.aem.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AEM Sling Scheduler using Runnable interface
 * This job will run periodically based on CRON expression
 */

@Component(service = Runnable.class, immediate = true,
           configurationPolicy = ConfigurationPolicy.REQUIRE,
           configurationPid = "com.organik.aem.core.schedulers.RunnableScheduler")
           
@Designate(ocd = RunnableScheduler.Config.class)
public class RunnableScheduler implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(RunnableScheduler.class);

    @ObjectClassDefinition(name = "Product API Scheduler Config")
    public @interface Config {

        @AttributeDefinition(name = "CRON Expression", description = "e.g., 0 0 0 * * ? (every day at midnight)")
        String scheduler_expression() default "0 0 0 * * ?";

        @AttributeDefinition(name = "Enable Scheduler")
        boolean scheduler_enabled() default true;
    }

    @Reference
    private Scheduler scheduler;

    private int schedulerId;
    private boolean enabled;
    private String cronExpression;

    @Activate
    @Modified
    protected void activate(Config config) {
        this.enabled = config.scheduler_enabled();
        this.cronExpression = config.scheduler_expression();
        this.schedulerId = this.hashCode();

        if (enabled) {
            ScheduleOptions options = scheduler.EXPR(cronExpression);
            options.name("RunnableSchedulerJob");
            options.canRunConcurrently(false);
            scheduler.schedule(this, options);
            log.info("✅ Scheduler registered with CRON: {}", cronExpression);
        } else {
            log.info("❌ Scheduler is disabled via configuration.");
        }
    }

    @Deactivate
    protected void deactivate() {
        scheduler.unschedule(String.valueOf(schedulerId));
        log.info("🛑 Scheduler unscheduled successfully.");
    }

    @Override
    public void run() {
        log.info("▶️ Running scheduled task to call Product API...");
        try {
            // Simulated external API call logic
            Thread.sleep(1000); // Simulate delay
            log.info("✅ Product API called successfully.");
        } catch (InterruptedException e) {
            log.error("❌ Error in scheduler run()", e);
            Thread.currentThread().interrupt();
        }
    }
}
