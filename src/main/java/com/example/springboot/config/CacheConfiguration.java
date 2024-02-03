package com.example.springboot.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@EnableCaching
@ConfigurationProperties(prefix = "cache")
@Getter
@Setter
public class CacheConfiguration {

  private Map<String, String> specs;

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();

    if (specs != null) {
      cacheManager.setCaches(specs.entrySet()
          .stream()
          .map(spec -> new CaffeineCache(spec.getKey(), Caffeine.from(spec.getValue()).build()))
          .toList());
    }

    return cacheManager;
  }
}
