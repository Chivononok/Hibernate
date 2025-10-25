package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @SequenceGenerator(name="clientsSequence", sequenceName = "s_client", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsSequence")
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String phone;
    private String status;
    private Double sum;
    @Embedded
    private Address address;


    public Client(String name,String surname, Integer age, String phone, String status, Double sum, Address address){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.status = status;
        this.sum = sum;
        this.address = address;
    }

}
