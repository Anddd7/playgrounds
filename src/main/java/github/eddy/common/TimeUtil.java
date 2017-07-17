package github.eddy.common;

import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {

  private static final Logger log = LoggerFactory.getLogger(TimeUtil.class);

  public static void execute(Runnable task) {
    Instant start = Instant.now();
    log.info("Task is start.");
    task.run();
    log.info("Task is down , during [{}]ms", Duration.between(start, Instant.now()).toMillis());
  }

  public static void execute(String title, Runnable... tasks) {
    log.info("Task is start ,{}.", title);
    for (int i = 0; i < tasks.length; i++) {
      Instant start = Instant.now();
      tasks[i].run();
      Duration duration = Duration.between(start, Instant.now());
      log.info("Task-{} is down , during [{}]ms / [{}]ns", i, duration.toMillis(),
          duration.toNanos());
    }
    log.info("Task is down .");
  }
}
