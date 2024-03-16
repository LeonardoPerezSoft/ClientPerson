package co.com.sofka.r2dbc;

import co.com.sofka.model.Client;
import co.com.sofka.model.Person;
import co.com.sofka.r2dbc.entities.ClientEntity;
import co.com.sofka.r2dbc.entities.PersonEntity;
import reactor.core.publisher.Mono;

public interface EntityMapper  {

    public static Mono<Client> mapClientEntityToClient(ClientEntity clientEntity, PersonEntity personEntity) {
        Person person = mapPersonEntityToPerson(personEntity);

        return Mono.just(Client.builder()
                .personId(clientEntity.getPersonId())
                .clientId(clientEntity.getClientId())
                .password(clientEntity.getPassword())
                .state(clientEntity.isState())
                .person(person)
                .build());
    }

    public static Person mapPersonEntityToPerson(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .gender(personEntity.getGender())
                .age(personEntity.getAge())
                .documentId(personEntity.getDocumentId())
                .address(personEntity.getAddress())
                .phoneNumber(personEntity.getPhoneNumber())
                .state(personEntity.isState())
                .build();
    }
}