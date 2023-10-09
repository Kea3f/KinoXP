package com.example.kinoxp.Backend.repositories;

import com.example.kinoxp.Backend.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Integer> {
}
