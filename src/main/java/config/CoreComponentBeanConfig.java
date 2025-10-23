package config;

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

    @Bean
    public TaskDecorator otelTaskDecorator() {
        return (runnable) ->
                ContextSnapshotFactory.builder().build().captureAll(new Object[0]).wrap(runnable);
    }

    @Bean(name = "smsClassifierExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(TaskDecorator otelTaskDecorator) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maximumPoolSize);
        executor.setTaskDecorator(otelTaskDecorator);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }


}
