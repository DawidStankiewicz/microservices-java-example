package dev.stankiewicz.example.poolservice.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "polls")
public class Pool {

    @Id
    private String id;
    private String title;
    private String description;
    private List<Option> options;

}
