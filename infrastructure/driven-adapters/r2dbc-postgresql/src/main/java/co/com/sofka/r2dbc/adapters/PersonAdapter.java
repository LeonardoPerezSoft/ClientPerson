package co.com.sofka.r2dbc.adapters;

import co.com.sofka.model.Client;
import co.com.sofka.model.Person;
import co.com.sofka.model.gateways.RepositoryCrud;
import co.com.sofka.r2dbc.entities.PersonEntity;
import co.com.sofka.r2dbc.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonAdapter implements RepositoryCrud<Person, String> {

    private final PersonRepository personRepository;


    @Override
    public Mono<Person> findById(String id) {
        return personRepository.findById(id)
                .map(personEntity -> Person.builder()

                        .name(personEntity.getName())
                        .gender(personEntity.getGender())
                        .age(personEntity.getAge())
                        .documentId(personEntity.getDocumentId())
                        .address(personEntity.getAddress())
                        .phoneNumber(personEntity.getPhoneNumber())
                        .state(personEntity.isState())
                        .build());
    }

    @Override
    public Flux<Person> findAll() {
        return personRepository.findAll()
                .map(personEntity -> Person.builder()
                        .id(personEntity.getId())
                        .name(personEntity.getName())
                        .gender(personEntity.getGender())
                        .age(personEntity.getAge())
                        .documentId(personEntity.getDocumentId())
                        .address(personEntity.getAddress())
                        .phoneNumber(personEntity.getPhoneNumber())
                        .state(personEntity.isState())
                        .build());
    }

    @Override
    public Mono<Person> create(Person person) {

        PersonEntity entity = PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .gender(person.getGender())
                .age(person.getAge())
                .documentId(person.getDocumentId())
                .address(person.getAddress())
                .phoneNumber(person.getPhoneNumber())
                .state(person.isState())
                .build();

        return personRepository.save(entity)
                .map(personEntity -> Person.builder()
                        .id(personEntity.getId())
                        .name(personEntity.getName())
                        .gender(personEntity.getGender())
                        .age(personEntity.getAge())
                        .documentId(personEntity.getDocumentId())
                        .address(personEntity.getAddress())
                        .phoneNumber(personEntity.getPhoneNumber())
                        .state(personEntity.isState())
                        .build());
    }



    @Override
    public Mono<Person> update(Person person, String id) {
        return personRepository.findById(id)
                .flatMap(existingPerson -> {

                    existingPerson.setName(person.getName());
                    existingPerson.setGender(person.getGender());
                    existingPerson.setAge(person.getAge());
                    existingPerson.setDocumentId(person.getDocumentId());
                    existingPerson.setAddress(person.getAddress());
                    existingPerson.setPhoneNumber(person.getPhoneNumber());
                    existingPerson.setState(person.isState());


                    return personRepository.save(existingPerson)
                            .map(updatedPersonEntity -> Person.builder()
                                    .id(updatedPersonEntity.getId())
                                    .name(updatedPersonEntity.getName())
                                    .gender(updatedPersonEntity.getGender())
                                    .age(updatedPersonEntity.getAge())
                                    .documentId(updatedPersonEntity.getDocumentId())
                                    .address(updatedPersonEntity.getAddress())
                                    .phoneNumber(updatedPersonEntity.getPhoneNumber())
                                    .state(updatedPersonEntity.isState())
                                    .build());
                });
    }


    @Override
    public Mono<Void> deleteById(String id) {
        return personRepository.deleteById(id);
    }


}
