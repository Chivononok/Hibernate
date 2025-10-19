package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name="user_id")
public class Visitor extends User{
    private String lastDate;
    private String firstDate;
    private String status;
    private Double sum;
    @OneToMany
    List<Visit> visitList;

    public Visitor(String lastDate, String firstDate, String status, Double sum){
        this.lastDate = lastDate;
        this.firstDate = firstDate;
        this.status = status;
        this.sum = sum;
    }
}
