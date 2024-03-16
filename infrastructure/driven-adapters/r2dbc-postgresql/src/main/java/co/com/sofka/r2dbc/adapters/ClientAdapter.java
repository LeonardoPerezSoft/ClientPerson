package co.com.sofka.r2dbc.adapters;


import co.com.sofka.model.Client;
import co.com.sofka.model.Person;
import co.com.sofka.model.gateways.RepositoryCrud;
import co.com.sofka.r2dbc.entities.ClientEntity;
import co.com.sofka.r2dbc.entities.PersonEntity;
import co.com.sofka.r2dbc.helper.CustomException;
import co.com.sofka.r2dbc.repositories.ClientRepository;
import co.com.sofka.r2dbc.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientAdapter implements RepositoryCrud<Client, String> {

    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;



    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id)
                .flatMap(this::mapClientEntityToClient)
                .switchIfEmpty(Mono.error(new CustomException("El ID del cliente proporcionado no es válido")));
    }

    @Override
    public Flux<Client> findAll() {
        return clientRepository.findAll()
                .flatMap(this::mapClientEntityToClient);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return clientRepository.deleteById(id);
    }


    @Override
    public Mono<Client> update(Client client, String id) {
        return clientRepository.findById(id)
                .flatMap(existingClient -> {

                    existingClient.setPassword(client.getPassword());
                    existingClient.setState(client.isState());


                    Person person = client.getPerson();
                    PersonEntity personEntity = PersonEntity.builder()
                            .id(existingClient.getPersonId())
                            .name(person.getName())
                            .gender(person.getGender())
                            .age(person.getAge())
                            .documentId(person.getDocumentId())
                            .address(person.getAddress())
                            .phoneNumber(person.getPhoneNumber())
                            .state(person.isState())
                            .build();

                    return personRepository.save(personEntity)
                            .flatMap(savedPerson -> {
                                existingClient.setPersonId(savedPerson.getId());
                                return clientRepository.save(existingClient);
                            });
                })
                .flatMap(this::mapClientEntityToClient);
    }
    @Override
    public Mono<Client> create(Client client) {

        if (client == null || client.getPerson() == null || client.getPassword() == null) {
            return Mono.error(new CustomException("Debe proporcionar todos los campos para crear un nuevo cliente"));
        }

        return mapPersonToPersonEntity(client.getPerson())
                .flatMap(personEntity -> {//

                    return personRepository.save(personEntity)
                            .flatMap(savedPersonEntity -> {

                                ClientEntity clientEntity = ClientEntity.builder()
                                        .personId(savedPersonEntity.getId())
                                        .state(client.isState())
                                        .password(client.getPassword())
                                        .build();


                                return clientRepository.save(clientEntity)
                                        .flatMap(savedClientEntity -> {//
                                          return mapClientEntityToClient(savedClientEntity);
                                        });
                    });
                });
    }



    private Mono<Client> mapClientEntityToClient(ClientEntity clientEntity) {
        return personRepository.findById(String.valueOf(clientEntity.getPersonId()))
                .flatMap(personEntity -> {
                    Person person = Person.builder()
                            .id(personEntity.getId())
                            .name(personEntity.getName())
                            .gender(personEntity.getGender())
                            .age(personEntity.getAge())
                            .documentId(personEntity.getDocumentId())
                            .address(personEntity.getAddress())
                            .phoneNumber(personEntity.getPhoneNumber())
                            .state(personEntity.isState())
                            .build();

                    return Mono.just(Client.builder()
                            .personId(clientEntity.getPersonId())
                            .clientId(clientEntity.getClientId())
                            .password(clientEntity.getPassword())
                            .state(clientEntity.isState())
                            .person(person)
                            .build());
                });
    }



    private Mono<PersonEntity> mapPersonToPersonEntity(Person person) {
        return Mono.just(PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .gender(person.getGender())
                .age(person.getAge())
                .documentId(person.getDocumentId())
                .address(person.getAddress())
                .phoneNumber(person.getPhoneNumber())
                .state(person.isState())
                .build());
    }
}