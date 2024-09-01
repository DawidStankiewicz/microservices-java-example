package dev.stankiewicz.example.resultsservice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.stankiewicz.example.resultsservice.model.Option;
import dev.stankiewicz.example.resultsservice.model.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(VoteService.class)
public class VoteServiceTest {


    public static final String VOTES_URL = "http://localhost:8080/options/1/votes";
    @Autowired
    private VoteService voteService;
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        String votesString = objectMapper.writeValueAsString(CollectionModel.of(Arrays.asList(
                Vote.builder().userId("123").build(),
                Vote.builder().userId("456").build()
        )));
        this.server.expect(requestTo(VOTES_URL))
                .andRespond(withSuccess(votesString, MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldFetchVotes() {
        //given
        Option option = Option.builder().build();
        option.add(Link.of(VOTES_URL, "votes"));
        List<Option> options = List.of(option);
        //when
        Map<Option, Collection<Vote>> result = voteService.fetchVotesForOptions(options);
        //then
        assertEquals(1, result.size());
        assertTrue(result.containsKey(option));
        assertEquals(2, result.get(option).size());
    }

}
