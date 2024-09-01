package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.model.Option;
import dev.stankiewicz.example.resultsservice.model.Pool;
import dev.stankiewicz.example.resultsservice.model.Result;
import dev.stankiewicz.example.resultsservice.model.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {

    @Mock
    private PoolOptionsFeignClient poolVotesFeignClient;
    @Mock
    private VoteService voteService;
    private ResultsService resultsService;

    @BeforeEach
    public void setUp() {
        resultsService = new ResultsService(poolVotesFeignClient, voteService);
    }

    @Test
    public void shouldCalculateResult() {
        //given
        Long poolId = 1L;
        doReturn(Pool.builder().title("pool").build()).when(poolVotesFeignClient).getPool(poolId);
        Option optionA = Option.builder().description("option A").build().add(Link.of("http://localhost:1234/options/1", IanaLinkRelations.SELF));
        Option optionB = Option.builder().description("option B").build().add(Link.of("http://localhost:1234/options/2", IanaLinkRelations.SELF));
        CollectionModel<Option> options = CollectionModel.of(Arrays.asList(optionA, optionB));
        doReturn(options).when(poolVotesFeignClient).getPoolOptions(poolId);
        Map<Option, Collection<Vote>> votes = new HashMap<>();
        votes.put(optionA, Arrays.asList(
                Vote.builder().userId("user A").build(),
                Vote.builder().userId("user B").build(),
                Vote.builder().userId("user C").build()
        ));
        votes.put(optionB, Arrays.asList(
                Vote.builder().userId("user D").build(),
                Vote.builder().userId("user E").build()
        ));
        doReturn(votes).when(voteService).fetchVotesForOptions(options.getContent());
        //when
        Result result = resultsService.getPoolResults(poolId);
        //then
        assertEquals(0.6f, result.getOptionPercentResults().get("option A"));
        assertEquals(0.4f, result.getOptionPercentResults().get("option B"));
    }
}
