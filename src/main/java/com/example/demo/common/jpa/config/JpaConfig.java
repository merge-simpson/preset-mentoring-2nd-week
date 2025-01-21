package com.example.demo.common.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // createdAt, updatedAt
public class JpaConfig {
}
