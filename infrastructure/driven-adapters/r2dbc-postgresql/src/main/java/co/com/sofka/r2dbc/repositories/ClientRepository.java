package co.com.sofka.r2dbc.repositories;

import co.com.sofka.r2dbc.entities.ClientEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;



public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, String>{



}
