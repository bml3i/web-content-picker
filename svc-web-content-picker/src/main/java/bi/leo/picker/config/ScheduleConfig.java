package bi.leo.picker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Scheduled(fixedDelay = 5000)
    public void ExtractTaskJob1() {
        System.out.println("ExtractTaskJob1 is executed - " + System.currentTimeMillis() / 1000);
    }

}
