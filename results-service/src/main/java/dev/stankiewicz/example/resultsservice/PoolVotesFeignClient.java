package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.model.Option;
import dev.stankiewicz.example.resultsservice.model.Pool;
import dev.stankiewicz.example.resultsservice.model.Vote;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = PoolVotesFeignClient.POOL_SERVICE)
public interface PoolVotesFeignClient {

    String POOL_SERVICE = "pool-service";

    @CircuitBreaker(name = POOL_SERVICE)
    @RequestMapping(method = RequestMethod.GET,
            value = "/pools/{poolId}")
    Pool getPool(@PathVariable("poolId") Long poolId);

    @CircuitBreaker(name = POOL_SERVICE)
    @RequestMapping(method = RequestMethod.GET,
            value = "/pools/{poolId}/options")
    CollectionModel<Option> getPoolOptions(@PathVariable("poolId") Long poolId);

    @CircuitBreaker(name = POOL_SERVICE)
    @RequestMapping(method = RequestMethod.GET, value = "/options/{optionId}/votes")
    CollectionModel<Vote> getVotes(@PathVariable("optionId") Long optionId);

}
