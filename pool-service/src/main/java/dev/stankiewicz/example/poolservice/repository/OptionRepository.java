package dev.stankiewicz.example.poolservice.repository;

import dev.stankiewicz.example.poolservice.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "options", path = "options")
public interface OptionRepository extends JpaRepository<Option, Long> {
}
