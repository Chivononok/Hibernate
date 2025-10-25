package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany()
    private List<FitRoom> fitRoomList;

    public Opportunity(String opportunityName, Double price){
        this.opportunityName = opportunityName;
        this.price = price;
    }
}
