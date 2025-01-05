package io.hh_plus.ecommerce.ecommerce.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "io.hh_plus.ecommerce.ecommerce.repository") // 래포지토리단 패키지구조를 스캔하도록 설정
public class JpaConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}