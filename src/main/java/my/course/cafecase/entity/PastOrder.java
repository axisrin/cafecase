package my.course.cafecase.entity;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Map;

public class PastOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Айди Прошедшего Заказа
    private Long pastId; // Айди прошедшего заказа
    @ElementCollection
    private Map<Integer, String> orderMeal; // Состав заказа
    private Long clientId; // Айди заказчика
    private Date orderDate; // Дата заказа
    private Date orderDatePast; // Дата выполнения заказа


}
