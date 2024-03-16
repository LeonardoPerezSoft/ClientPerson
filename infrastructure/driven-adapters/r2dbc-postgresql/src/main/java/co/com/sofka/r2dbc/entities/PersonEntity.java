package co.com.sofka.r2dbc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity {
    @Id
    private int id;
    private String name;
    private String gender;
    private int age;
    private String documentId;
    private String address;
    private String phoneNumber;
    private boolean state;

}
