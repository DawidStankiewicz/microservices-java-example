package dev.stankiewicz.example.poolservice.repository;

import dev.stankiewicz.example.poolservice.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "votes", path = "votes")
public interface VoteRepository extends MongoRepository<Vote, String> {
}