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

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Map<Integer, String> getOrderMeal() {
        return orderMeal;
    }

    public void setOrderMeal(Map<Integer, String> orderMeal) {
        this.orderMeal = orderMeal;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Order() {

    }


}
