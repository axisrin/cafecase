package my.course.cafecase.repos;

import my.course.cafecase.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long>  {
}
