package com.gr.football.repository;

import com.gr.football.entity.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefereeRepository extends JpaRepository<Referee, Integer> {
}
