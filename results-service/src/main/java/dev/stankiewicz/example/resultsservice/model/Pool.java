package dev.stankiewicz.example.resultsservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pool extends RepresentationModel<Pool> {

    private String title;
    private String description;

}
