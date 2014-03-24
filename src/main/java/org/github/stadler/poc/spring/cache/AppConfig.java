package org.github.stadler.poc.spring.cache;

import java.util.Arrays;
import org.mockito.Mockito;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class AppConfig {

  @Bean
  public ServiceToCache serviceToCache() {
    return new ServiceToCache();
  }

  @Bean
  public CacheManager cacheManager() {
    // configure and return an implementation of Spring's CacheManager SPI
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(Arrays.asList(cache()));
    return cacheManager;
  }

  @Bean
  public Cache cache() {
    ConcurrentMapCache concurrentMapCache = concurrentMapCache();
    Cache cacheMock = Mockito.spy(concurrentMapCache);
    return cacheMock;
  }

  private ConcurrentMapCache concurrentMapCache() {
    return new ConcurrentMapCache("mycache");
  }


}
