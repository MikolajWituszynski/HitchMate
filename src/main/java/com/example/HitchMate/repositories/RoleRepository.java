package com.example.HitchMate.repositories;

import com.example.HitchMate.Entity.Marker;
import com.example.HitchMate.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
