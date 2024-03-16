package co.com.sofka.api.controllers;

import co.com.sofka.model.Client;
import co.com.sofka.usecase.client.ClientUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientUseCase clientUseCase;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllClients() {
    }

    @Test
    void findByIdClient() {
        ClientUseCase clientUseCase = mock(ClientUseCase.class);


        ClientController clientController = new ClientController(clientUseCase);

        Client expectedClient = Client.builder()
                .clientId(1)
                .password("password")
                .personId(1)
                .state(true)
                .build();


        when(clientUseCase.findClientById(anyString())).thenReturn(Mono.just(expectedClient));
        StepVerifier.create(clientController.findByIdClient("1"))
                .expectNext(expectedClient)
                .verifyComplete();
    }


    @Test
    void createClient() {

        ClientUseCase clientUseCase = Mockito.mock(ClientUseCase.class);
        ClientController clientController = new ClientController(clientUseCase);

        Client client = Client.builder()
                .clientId(1)
                .password("password")
                .personId(1)
                .state(true)
                .build();

        when(clientUseCase.createClient(any(Client.class))).thenReturn(Mono.just(client));

        StepVerifier.create(clientController.createClient(client))
                .expectNext(client)
                .verifyComplete();
    }


    @Test
    void deleteClient() {
    }

    @Test
    void updateClient() {
    }
}