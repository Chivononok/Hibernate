package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fitroom", schema = "public")
@Subselect("select p.id as id, p.roomName as roomName, p.roomNumber as roomNumber, p.capacity as capacity, " +
        "p.status as status, p.price as price from public.fitroom p where p.price<10")
@Immutable
@ToString
public class FitroomWithSubselect {
    @Id
    private Long id;
    private String roomName;
    private String roomNumber;
    private Long capacity;
    private String status;
    private Double price;
}
