package co.com.sofka.usecase.person;

import co.com.sofka.model.Person;
import co.com.sofka.model.gateways.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PersonUseCase {

    private final RepositoryCrud<Person, String> repositoryCrud;

    public Flux<Person> finAllPersons() {
        return repositoryCrud.findAll();
    }

    public Mono<Person> findPersonById(String id){
        return  repositoryCrud.findById(id);
    }


    public Mono<Person> createPerson(Person person) {
        return repositoryCrud.create(person);

    }
    public Mono<Person> updatePerson(Person person, String  id){
        return repositoryCrud.update(person, id);
    }

    public Mono<Void> deletePersonById(String id) {
        return repositoryCrud.deleteById(id);
    }






}
