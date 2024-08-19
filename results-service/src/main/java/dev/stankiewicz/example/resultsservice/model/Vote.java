package dev.stankiewicz.example.resultsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends RepresentationModel<Vote> {

    private String userId;
    private LocalDateTime voteDate;

}
