package by.nintendo.datajpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private long petId;
    private int quantity;
    private LocalDateTime shipDate;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    private boolean complete;
}
