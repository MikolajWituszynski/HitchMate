package com.example.HitchMate.repositories;

import com.example.HitchMate.Entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    Marker findByIdAndOwnBy(Long id, String ownBy);
}
