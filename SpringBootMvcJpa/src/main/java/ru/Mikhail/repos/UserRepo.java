package ru.Mikhail.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.Mikhail.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
