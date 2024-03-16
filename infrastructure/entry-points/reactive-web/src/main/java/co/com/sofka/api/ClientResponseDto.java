package co.com.sofka.api;


import co.com.sofka.model.Client;
import co.com.sofka.model.Person;
import lombok.Builder;

@Builder(toBuilder = true)
public record ClientResponseDto (
         int id,
         Integer personId,
        boolean state,
         String password,
         PersonResponseDto person


) {
    public static ClientResponseDto from(Client client) {
        return ClientResponseDto.builder()
                .id(client.getClientId())
                .state(client.isState())
                .password(client.getPassword())
                .person(PersonResponseDto.from(client.getPerson()))
                .build();
    }
}
