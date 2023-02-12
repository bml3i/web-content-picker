package bi.leo.picker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "web-driver")
@EnableConfigurationProperties
@Data
public class WebDriverConfig {

    private String name;

    private String location;

    private Boolean headless;

    private Integer initialPoolSize;

    private Integer maxPoolSize;

}
