package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "status='PREMIUM'")
@Table(name = "client", schema = "public")

@ToString
public class ClientPremium {
    @Id
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String phone;
    private String status;
    private Double sum;
    @Embedded
    private Address address;
}
