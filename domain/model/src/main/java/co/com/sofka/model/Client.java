package co.com.sofka.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client {

    private int clientId;
    private String password;
    private int personId;
    private boolean state;
    private Person person;

}


