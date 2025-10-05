package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity {
    @Id
    @SequenceGenerator(name="opportunitySequence", sequenceName = "s_opportunity", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opportunitySequence")
    private Long id;
    private String opportunityName;
    private Double price;

    public Opportunity(String opportunityName, Double price){
        this.opportunityName = opportunityName;
        this.price = price;
    }
}
