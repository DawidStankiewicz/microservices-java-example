package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.model.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ResultControllerTest {

    @Mock
    private ResultsService resultsService;
    private ResultsController resultsController;

    @BeforeEach
    public void setUp() {
        resultsController = new ResultsController(resultsService);
    }

    @Test
    public void shouldGetResults() {
        //given
        Result expectedResult = Result.builder().build();
        doReturn(expectedResult).when(resultsService).getPoolResults(1L);
        //when
        Result result = resultsController.getPoolResults(1L);
        //then
        assertEquals(expectedResult, result);
    }
}
