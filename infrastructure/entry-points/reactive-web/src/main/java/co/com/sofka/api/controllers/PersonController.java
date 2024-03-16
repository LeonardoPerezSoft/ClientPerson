package co.com.sofka.api.controllers;

import co.com.sofka.model.Person;
import co.com.sofka.r2dbc.helper.CustomException;
import co.com.sofka.usecase.person.PersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/persons")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class PersonController {
    private final PersonUseCase personUseCase;

    @GetMapping("/{id}")
        public Mono<Person> findByIdPerson(@PathVariable("id") String id) {
        return personUseCase.findPersonById(id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping
    public Flux<Person> findAllPersons() {
        return personUseCase.finAllPersons()
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
    }

    @PostMapping
    public Mono<Person> createPerson(@RequestBody Person person) {
        return personUseCase.createPerson(person);
    }

    @PutMapping("/{id}")
    public Mono<Person> updatePerson(@RequestBody Person person, @PathVariable("id") String id) {
        return personUseCase.updatePerson(person, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePerson(@PathVariable("id") String id) {
        return personUseCase.findPersonById(id)
                .flatMap(person -> personUseCase.deletePersonById(id));
    }


}
