package com.dev.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // Nombre de threads toujours présents dans le pool
        executor.setCorePoolSize(10);
        // Nombre maximum de threads dans le pool
        executor.setMaxPoolSize(50);
        // Capacité de la file d'attente
        executor.setQueueCapacity(200);
        // Préfixe des noms de threads pour identification
        executor.setThreadNamePrefix("Async-");
        // Timeout pour les threads inactifs supplémentaires
        executor.setKeepAliveSeconds(60);
        executor.initialize();
        return executor;
    }
}
