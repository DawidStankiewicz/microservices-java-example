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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {

    @Mock
    private PoolVotesFeignClient poolVotesFeignClient;
    private ResultsService resultsService;

    @BeforeEach
    public void setUp() {
        resultsService = new ResultsService(poolVotesFeignClient);
    }

    @Test
    public void shouldCalculateResult() {
        //given
        Long poolId = 1L;
        doReturn(Pool.builder().title("pool").build()).when(poolVotesFeignClient).getPool(poolId);
        doReturn(CollectionModel.of(Arrays.asList(
                Option.builder().description("option A").build().add(Link.of("http://localhost:1234/options/1", IanaLinkRelations.SELF)),
                Option.builder().description("option B").build().add(Link.of("http://localhost:1234/options/2", IanaLinkRelations.SELF)))
        )).when(poolVotesFeignClient).getPoolOptions(poolId);
        doReturn(CollectionModel.of(Arrays.asList(
                Vote.builder().userId("user A").build(),
                Vote.builder().userId("user B").build(),
                Vote.builder().userId("user C").build()
        ))).when(poolVotesFeignClient).getVotes(1L);
        doReturn(CollectionModel.of(Arrays.asList(
                Vote.builder().userId("user D").build(),
                Vote.builder().userId("user E").build()
        ))).when(poolVotesFeignClient).getVotes(2L);
        //when
        Result result = resultsService.getPoolResults(poolId);
        //then
        assertEquals(0.6f, result.getOptionPercentResults().get("option A"));
        assertEquals(0.4f, result.getOptionPercentResults().get("option B"));
    }
}
