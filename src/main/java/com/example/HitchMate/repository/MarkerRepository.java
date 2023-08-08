package com.example.HitchMate.repository;

import com.example.HitchMate.entity.Location;
import com.example.HitchMate.entity.Marker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkerRepository  extends JpaRepository<Marker, Long> {
}
