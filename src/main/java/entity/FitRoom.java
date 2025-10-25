package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class FItRooms {
    @Id
    @SequenceGenerator(name="fitroomsSequence", sequenceName = "s_firrooms", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fitroomsSequence")
    private Long id;
    private String roomName;
    private String roomNumber;
    private Long capacity;
    private String status;
    private Double price;
}
