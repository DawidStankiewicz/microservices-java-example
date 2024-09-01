package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.context.UserContextHolder;
import dev.stankiewicz.example.resultsservice.model.Option;
import dev.stankiewicz.example.resultsservice.model.Pool;
import dev.stankiewicz.example.resultsservice.model.Result;
import dev.stankiewicz.example.resultsservice.model.Vote;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static dev.stankiewicz.example.resultsservice.PoolOptionsFeignClient.POOL_SERVICE;

@Slf4j
@Service
public class ResultsService {

    private final PoolOptionsFeignClient poolVotesFeignClient;
    private final VoteService voteService;

    public ResultsService(PoolOptionsFeignClient poolVotesFeignClient, VoteService voteService) {
        this.poolVotesFeignClient = poolVotesFeignClient;
        this.voteService = voteService;
    }

    public Result getPoolResults(Long poolId) {
        Pool pool = poolVotesFeignClient.getPool(poolId);
        CollectionModel<Option> options = poolVotesFeignClient.getPoolOptions(poolId);
        Map<String, Float> votesPercent = getVotesDistribution(options);
        return Result.builder()
                .poolTitle(pool.getTitle())
                .optionPercentResults(votesPercent)
                .build();
    }

    @CircuitBreaker(name = POOL_SERVICE, fallbackMethod = "getEmptyVotes")
    @Bulkhead(name = POOL_SERVICE, fallbackMethod = "getEmptyVotes")
    private Map<String, Float> getVotesDistribution(CollectionModel<Option> options) {
        log.debug("ResultsService CorrelationId: {}", UserContextHolder.getContext().getCorrelationId());
        Map<Option, Collection<Vote>> votes = voteService.fetchVotesForOptions(options.getContent());
        long sumOfAllVotes = votes.values().stream().mapToLong(Collection::size).sum();
        return votes.keySet().stream()
                .collect(Collectors.toMap(Option::getDescription, option -> (float) votes.get(option).size() / sumOfAllVotes));
    }

    @SuppressWarnings("unused")
    private Map<String, Float> getEmptyVotes(CollectionModel<Option> options, Throwable throwable) {
        return options.getContent().stream().collect(Collectors.toMap(Option::getDescription, option -> (float) 0));
    }
}
