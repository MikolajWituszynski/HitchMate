package com.example.HitchMate.Repositories;

import com.example.HitchMate.Entities.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
}
