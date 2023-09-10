package com.example.HitchMate.Repositories;

import com.example.HitchMate.Entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    Marker findByUserIdAndOwner(Long id, String owner);
}
