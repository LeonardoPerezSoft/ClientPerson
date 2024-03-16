package co.com.sofka.api.controllers;


import co.com.sofka.model.Client;
import co.com.sofka.r2dbc.helper.CustomException;
import co.com.sofka.usecase.client.ClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ClientController {

    private final ClientUseCase clientUseCase;


    @GetMapping
    public Flux<Client> findAllClients() {
        return clientUseCase.finAllClients()
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));

    }

    @GetMapping("/{id}")
    public Mono<Client> findByIdClient(@PathVariable("id") String id) {
        return clientUseCase.findClientById(id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/name/{id}")
    public Mono<String> findClientName(@PathVariable("id") String id) {

        return clientUseCase.findClientById(id)
                .map(client -> client.getPerson().getName());

    }


    @PostMapping
    public Mono<Client> createClient(@RequestBody Client client) {
        return clientUseCase.createClient(client)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));

    }


    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable("id") String id) {
        return clientUseCase.findClientById(id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(product -> clientUseCase.deleteClientById(id));

    }


    @PutMapping("/{id}")
    public Mono<Client> updateClient(@RequestBody Client client, @PathVariable("id") String id) {
        return clientUseCase.updateClient(client, id)
                .onErrorResume(e -> Mono.error(new CustomException(e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }


}
