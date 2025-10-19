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
public class Sign {
    @Id
    @SequenceGenerator(name="signSequence", sequenceName = "s_sign", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "signSequence")
    private Long id;
    private String date;
    private String time;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "fitroom_id")
    private FitRoom fitRoom;

    public Sign(String date, String time){
        this.date = date;
        this.time = time;
    }
}
