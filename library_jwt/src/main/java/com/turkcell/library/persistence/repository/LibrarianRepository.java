package com.turkcell.library.persistence.repository;

import com.turkcell.library.domain.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    Optional<Librarian> findByEmail(String email);
    boolean existsByEmail(String email);
}
