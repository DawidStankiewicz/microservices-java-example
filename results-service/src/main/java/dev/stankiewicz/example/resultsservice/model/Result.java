package dev.stankiewicz.example.resultsservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Result {

    private String poolTitle;
    private Map<String, Float> optionPercentResults;
}
