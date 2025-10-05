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

    public FitRoom(String roomName, String roomNumber, Long capacity, String status, Double price){
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
    }
}
