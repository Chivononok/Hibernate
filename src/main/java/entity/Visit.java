package entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    @SequenceGenerator(name="visitSequence", sequenceName = "s_visit", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitSequence")
    private Long id;
    private String localDate;
    private BigDecimal spentMoney;

    public Visit(String localDate, BigDecimal spentMoney){
        this.localDate = localDate;
        this.spentMoney = spentMoney;
    }

}
