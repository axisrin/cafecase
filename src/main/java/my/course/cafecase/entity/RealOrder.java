package my.course.cafecase.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
public class RealOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrder; // Айди Заказа
    @ElementCollection
    private Map<Long, Integer> orderMeal; // Состав заказа
    private Long clientId; // Айди заказчика
    private boolean isGotov;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
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

    public RealOrder() {

    }


}
