package my.course.cafecase.repos;

import my.course.cafecase.entity.PastOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PastOrderRepo extends JpaRepository<PastOrder, Long> {
    List<PastOrder> findByClientId(Long clientId);
    PastOrder findByIdPastOrder(Long pastOrderId);
    List<PastOrder> findByIsGotov(boolean isGotov);
}
