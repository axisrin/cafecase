package my.course.cafecase.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrder; // Айди Заказа
    @ElementCollection
    private Map<Integer, String> orderMeal; // Состав заказа
    private Long clientId; // Айди заказчика
    private Date orderDate; // Дата заказа


    public Order() {

    }


}
