package cardealerbackend.backend.repository;

import cardealerbackend.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    User findUserByUserName(String userName);
}
