package co.com.sofka.model;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Person {

    private int id;
    private String name;
    private String gender;
    private int age;
    private String documentId;
    private String address;
    private String phoneNumber;
    private boolean state;
}
