package my.course.cafecase.repos;

import my.course.cafecase.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, Long> {
    List<Menu> findByTagFood(String tagFood);
    Menu findByIdFood(Long id);
}
