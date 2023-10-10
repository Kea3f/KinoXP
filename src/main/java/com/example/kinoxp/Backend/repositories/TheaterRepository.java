package com.example.kinoxp.Backend.repositories;

import com.example.kinoxp.Backend.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {


}
