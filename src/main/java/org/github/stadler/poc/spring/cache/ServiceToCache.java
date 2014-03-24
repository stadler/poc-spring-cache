package org.github.stadler.poc.spring.cache;

import org.joda.time.LocalDateTime;
import org.springframework.cache.annotation.Cacheable;


public class ServiceToCache {

  @Cacheable(value = {"mycache"})
  public String returnSomeString(String key) throws InterruptedException {
    System.out.println("returnSomeString is being called.");
    Thread.sleep(1000);
    return key + LocalDateTime.now();
  }
}
