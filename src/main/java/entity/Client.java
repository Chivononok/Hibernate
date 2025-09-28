package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String phone;
    private String status;
    private Double sum;

    public Client(){

    }
    public Client(Long id, String name,String surname, Integer age, String phone, String status, Double sum){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.status = status;
        this.sum = sum;
    }
}
