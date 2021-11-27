package my.course.cafecase.repos;

import my.course.cafecase.entity.RealOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<RealOrder, Long> {
}
