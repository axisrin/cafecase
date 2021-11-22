package my.course.cafecase.repos;

import my.course.cafecase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>  {
}
