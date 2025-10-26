package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FitRoom {
    @Id
    @SequenceGenerator(name="fitroomSequence", sequenceName = "s_fitroom", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fitroomSequence")
    private Long id;
    private String roomName;
    private String roomNumber;
    private Long capacity;
    private String status;
    private Double price;
    @OneToMany(mappedBy = "fitRoom", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sign> signList;

    public FitRoom(String roomName, String roomNumber, Long capacity, String status, Double price){
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
    }
}
