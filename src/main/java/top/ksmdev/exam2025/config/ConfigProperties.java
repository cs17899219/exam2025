package top.ksmdev.exam2025.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "exam")
public class ConfigProperties {

    private String test;


}
