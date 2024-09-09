package com.AmberSautner.incidentmanagementsystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmberSautner.incidentmanagementsystem.Entities.Incident;


public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
