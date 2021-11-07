package ru.otus.lec23.security.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.lec23.security.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUsername(String username);

}
