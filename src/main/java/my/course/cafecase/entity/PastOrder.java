package my.course.cafecase.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
public class PastOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPastOrder; // Айди Прошедшего Заказа
    private Long pastId; // Айди прошедшего заказа
    @ElementCollection
    private Map<Integer, String> orderMeal; // Состав заказа
    private Long clientId; // Айди заказчика
    private Date orderDate; // Дата заказа
    private Date orderDatePast; // Дата выполнения заказа

    public Long getIdPastOrder() {
        return idPastOrder;
    }

    public void setIdPastOrder(Long idPastOrder) {
        this.idPastOrder = idPastOrder;
    }

    public Long getPastId() {
        return pastId;
    }

    public void setPastId(Long pastId) {
        this.pastId = pastId;
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

    public Date getOrderDatePast() {
        return orderDatePast;
    }

    public void setOrderDatePast(Date orderDatePast) {
        this.orderDatePast = orderDatePast;
    }
}
