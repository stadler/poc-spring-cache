package org.github.stadler.poc.spring.cache;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

  public static void main(String[] args) throws InterruptedException {
    try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);) {
      ServiceToCache obj = (ServiceToCache) context.getBean("serviceToCache");
      Cache cache = (Cache) context.getBean("cache");

      String key = "1";
      String result1 = obj.returnSomeString(key);
      // Before it calls the method the cache is checked
      verify(cache, times(1)).get(key);

      String result2 = obj.returnSomeString(key);
      // Here we would expect that the cache was only called twice, but it is being called 3 times.
      verify(cache, times(3)).get(key);
      Assert.assertThat("The returned values must be the same, as they are cached.", result1, Matchers.is(result2));

      String result3 = obj.returnSomeString(key);
      // Here we would expect that the cache was only called three time, but it is being called 5 times.
      verify(cache, times(5)).get(key);
      Assert.assertThat("The returned values must be the same, as they are cached.", result1, Matchers.is(result3));
    }
  }
}
