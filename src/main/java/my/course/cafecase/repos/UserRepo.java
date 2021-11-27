package my.course.cafecase.repos;

import my.course.cafecase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long>  {
    User findByUsername(String username);

    User findByActivationCode(String code);

    List<User> findByEmail(String email);
}
