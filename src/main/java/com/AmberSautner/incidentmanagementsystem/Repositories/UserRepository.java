package com.AmberSautner.incidentmanagementsystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmberSautner.incidentmanagementsystem.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
