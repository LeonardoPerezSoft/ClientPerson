package co.com.sofka.r2dbc.repositories;

import co.com.sofka.r2dbc.entities.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, String> {
}
