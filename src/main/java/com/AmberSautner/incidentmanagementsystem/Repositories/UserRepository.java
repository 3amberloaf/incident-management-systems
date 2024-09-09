package com.AmberSautner.incidentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmberSautner.incidentmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
