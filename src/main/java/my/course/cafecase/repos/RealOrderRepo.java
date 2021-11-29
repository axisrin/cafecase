package my.course.cafecase.repos;

import my.course.cafecase.entity.RealOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface RealOrderRepo extends JpaRepository<RealOrder, Long> {
    RealOrder findByClientId(Long clientId);
}
