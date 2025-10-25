package entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@ToString
public class Address {
    public String city;
    public String street;
    public String houseNumber;
    public String zipcode;
}
