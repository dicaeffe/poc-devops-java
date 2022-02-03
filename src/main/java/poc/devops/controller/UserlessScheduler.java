package poc.devops.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/* The lombok's @Slf4j annotation is equivalent to the logger definition for this class.
 *     private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class) */
@Slf4j
@Component
public class UserlessScheduler {
    
	/* The @Scheduled can be configured with many options. Some examples:
	 * - 'fixedRate' specifies the interval between method invocations, measured from the start time of each invocation.
	 * - 'fixedDelay' specifies the interval between invocations measured from the completion of the task.
	 * - 'cron' can be used for sophisticated task scheduling. */
	@Scheduled(fixedDelayString = "${scheduling.fixedDelay.in.milliseconds}")
	public void scheduledProcess() {
		log.info("Start scheduled process");
		
		// Log different log levels
		log.error("1/5");
		log.warn("2/5");
		log.info("3/5");
		log.debug("4/5");
		log.trace("5/5");
		
		log.info("End retention process");
	}
}