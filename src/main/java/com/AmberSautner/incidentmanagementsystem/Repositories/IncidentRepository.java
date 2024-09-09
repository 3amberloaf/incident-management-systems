package com.AmberSautner.incidentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmberSautner.incidentmanagement.model.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
