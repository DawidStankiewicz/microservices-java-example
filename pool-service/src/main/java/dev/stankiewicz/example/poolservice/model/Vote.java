package dev.stankiewicz.example.poolservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "votes")
public class Vote {

    @Id
    private String id;
    private String pollId;
    private String optionId;
    private String userId;
}
