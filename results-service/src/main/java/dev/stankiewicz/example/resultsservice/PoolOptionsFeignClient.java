package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.model.Option;
import dev.stankiewicz.example.resultsservice.model.Pool;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = PoolOptionsFeignClient.POOL_SERVICE)
public interface PoolOptionsFeignClient {

    String POOL_SERVICE = "pool-service";

    @CircuitBreaker(name = POOL_SERVICE)
    @Retry(name = POOL_SERVICE)
    @RateLimiter(name = POOL_SERVICE)
    @Bulkhead(name = POOL_SERVICE)
    @RequestMapping(method = RequestMethod.GET,
            value = "/pools/{poolId}")
    Pool getPool(@PathVariable("poolId") Long poolId);

    @CircuitBreaker(name = POOL_SERVICE)
    @Retry(name = POOL_SERVICE)
    @RateLimiter(name = POOL_SERVICE)
    @Bulkhead(name = POOL_SERVICE)
    @RequestMapping(method = RequestMethod.GET,
            value = "/pools/{poolId}/options")
    CollectionModel<Option> getPoolOptions(@PathVariable("poolId") Long poolId);

}
