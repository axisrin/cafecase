package my.course.cafecase.repos;

import my.course.cafecase.entity.PastOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastOrderRepo extends JpaRepository<PastOrder, Long> {
}
