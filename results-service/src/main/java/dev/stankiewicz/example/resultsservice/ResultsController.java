package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.context.UserContextHolder;
import dev.stankiewicz.example.resultsservice.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ResultsController {

    private final ResultsService resultsService;

    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping(path = "/results/{poolId}")
    public Result getPoolResults(@PathVariable Long poolId) {
        log.debug("ResultsController CorrelationId: {}", UserContextHolder.getContext().getCorrelationId());
        return resultsService.getPoolResults(poolId);
    }

}
