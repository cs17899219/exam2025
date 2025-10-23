package top.ksmdev.exam2025.config;

import io.micrometer.context.ContextSnapshotFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class CoreComponentBeanConfig {

    private static final int corePoolSize = Math.min(Runtime.getRuntime().availableProcessors() * 8, 64);
    private static final int maximumPoolSize = corePoolSize * 2;
    private static final int queueCapacity = 100;




}
