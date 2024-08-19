package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.model.Option;
import dev.stankiewicz.example.resultsservice.model.Pool;
import dev.stankiewicz.example.resultsservice.model.Result;
import dev.stankiewicz.example.resultsservice.model.Vote;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ResultsService {

    private final PoolVotesFeignClient poolVotesFeignClient;

    public ResultsService(PoolVotesFeignClient poolVotesFeignClient) {
        this.poolVotesFeignClient = poolVotesFeignClient;
    }

    public Result getPoolResults(Long poolId) {
        Pool pool = poolVotesFeignClient.getPool(poolId);
        CollectionModel<Option> options = poolVotesFeignClient.getPoolOptions(poolId);
        Map<Option, Collection<Vote>> votes = options.getContent().stream()
                .collect(Collectors.toMap(
                        Function.identity(), option -> poolVotesFeignClient.getVotes(option.getLink("votes").get().toUri()).getContent()
                ));
        long sumOfAllVotes = votes.values()
                .stream()
                .mapToLong(Collection::size)
                .sum();
        Map<String, Float> votesPercent = votes.keySet().stream().collect(Collectors.toMap(
                Option::getDescription,
                option -> (float) votes.get(option).size() / sumOfAllVotes
        ));

        return Result.builder()
                .poolTitle(pool.getTitle())
                .optionPercentResults(votesPercent)
                .build();
    }

}
