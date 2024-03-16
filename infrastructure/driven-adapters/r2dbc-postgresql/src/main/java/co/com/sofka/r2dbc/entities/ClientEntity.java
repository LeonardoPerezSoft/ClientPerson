package co.com.sofka.r2dbc.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class ClientEntity {
    @Id
    @Column("client_id")
    private int clientId;
    private String password;
    @Column("person_id")
    private int personId;
    private boolean state;


}
