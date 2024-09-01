package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.model.Option;
import dev.stankiewicz.example.resultsservice.model.Vote;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static dev.stankiewicz.example.resultsservice.PoolOptionsFeignClient.POOL_SERVICE;

@Service
public class VoteService {

    private final RestClient restClient;

    public VoteService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public Map<Option, Collection<Vote>> fetchVotesForOptions(Collection<Option> options) {
        return options.stream().collect(Collectors.toMap(Function.identity(), this::fetchVotes));
    }

    @Retry(name = POOL_SERVICE)
    @RateLimiter(name = POOL_SERVICE)
    private Collection<Vote> fetchVotes(Option option) {
        // todo this is not returning any votes due to the issue with parsing CollectionModel https://github.com/spring-projects/spring-hateoas/issues/2160
        CollectionModel<Vote> votes = restClient.get().uri(option.getLink("votes").orElseThrow().toUri())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (votes == null) {
            return Collections.emptyList();
        }
        return votes.getContent();

    }
}
