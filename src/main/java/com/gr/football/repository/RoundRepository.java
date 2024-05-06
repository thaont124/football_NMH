package com.gr.football.repository;

import com.gr.football.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundRepository extends JpaRepository<Round, Integer> {
    List<Round> findBySeasonSeasonId(Integer seasonId);
}
