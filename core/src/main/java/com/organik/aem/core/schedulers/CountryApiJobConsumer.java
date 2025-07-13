package com.organik.aem.core.schedulers;

import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.apache.sling.event.jobs.Job;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = JobConsumer.class,
           property = JobConsumer.PROPERTY_TOPICS + "=country/api/job")
public class CountryApiJobConsumer implements JobConsumer {

    private static final Logger log = LoggerFactory.getLogger(CountryApiJobConsumer.class);

    @Override
    public JobResult process(Job job) {
        String country = (String) job.getProperty("country");

        log.info("Executing job for country: {}", country);

        switch (country) {
            case "India":
                // API logic for India
                log.info("Calling India API...");
                break;
            case "USA":
                // API logic for USA
                log.info("Calling USA API...");
                break;
            case "Russia":
                // API logic for Russia
                log.info("Calling Russia API...");
                break;
            default:
                log.warn("Unknown country in job: {}", country);
                return JobResult.FAILED;
        }

        return JobResult.OK;
    }
}
