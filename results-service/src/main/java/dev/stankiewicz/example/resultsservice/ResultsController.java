package dev.stankiewicz.example.resultsservice;

import dev.stankiewicz.example.resultsservice.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultsController {

    private final ResultsService resultsService;

    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping(path = "/results/{poolId}")
    public Result getPoolResults(@PathVariable Long poolId) {
        return resultsService.getPoolResults(poolId);
    }

}
