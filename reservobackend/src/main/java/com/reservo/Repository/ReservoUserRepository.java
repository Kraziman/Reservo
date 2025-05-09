package com.reservo.Repository;

import com.reservo.Models.ReservoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservoUserRepository extends JpaRepository<ReservoUser, Long> {
    Optional<ReservoUser> findByEmail();
    Optional<ReservoUser> findByUsername();

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
