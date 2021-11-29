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
    private Map<Long, Integer> orderMeal; // Состав заказа
    private Long clientId; // Айди заказчика
    private boolean isGotov;
    private Date orderDatePast; // Дата выполнения заказа
    private String pastOrderText; // Прошлый заказ

    public PastOrder() {
    }


    public String getPastOrderText() {
        return pastOrderText;
    }

    public void setPastOrderText(String pastOrderText) {
        this.pastOrderText = pastOrderText;
    }

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

    public Map<Long, Integer> getOrderMeal() {
        return orderMeal;
    }

    public void setOrderMeal(Map<Long, Integer> orderMeal) {
        this.orderMeal = orderMeal;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public boolean isGotov() {
        return isGotov;
    }

    public void setGotov(boolean gotov) {
        isGotov = gotov;
    }

    public Date getOrderDatePast() {
        return orderDatePast;
    }

    public void setOrderDatePast(Date orderDatePast) {
        this.orderDatePast = orderDatePast;
    }
}
