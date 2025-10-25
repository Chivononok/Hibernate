package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name="userSequence", sequenceName = "u_client", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private Long id;
    private String name;
    private String surname;
    private String birthdate;
    @Embedded
    private Address address;

    public User(String name, String surname, String birthdate, Address address){
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.address = address;
    }
}
