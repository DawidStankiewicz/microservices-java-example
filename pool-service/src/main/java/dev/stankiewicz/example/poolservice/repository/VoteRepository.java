package dev.stankiewicz.example.poolservice.repository;

import dev.stankiewicz.example.poolservice.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "votes", path = "votes")
public interface VoteRepository extends JpaRepository<Vote, Long> {
}