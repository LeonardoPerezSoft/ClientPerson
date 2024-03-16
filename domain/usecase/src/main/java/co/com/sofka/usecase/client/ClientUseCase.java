package co.com.sofka.usecase.client;
import co.com.sofka.model.Client;
import co.com.sofka.model.gateways.RepositoryCrud;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ClientUseCase {

    private final RepositoryCrud<Client, String> repositoryCrud;

    public Flux<Client> finAllClients() {
        return repositoryCrud.findAll();
    }

    public Mono<Client> findClientById(String id){
        return  repositoryCrud.findById(id);
    }


    public Mono<Client> createClient(Client client) {
        return repositoryCrud.create(client);

    }

    public Mono<Void> deleteClientById(String id) {
        return repositoryCrud.deleteById(id);
    }

    public Mono<Client> updateClient(Client client, String  id){
        return repositoryCrud.update(client, id);
    }


}
