package br.edu.iftm.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.iftm.springapp.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findUserByEmail(String email);
}