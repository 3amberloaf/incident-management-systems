package com.AmberSautner.incidentmanagementsystem.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmberSautner.incidentmanagementsystem.Entities.AuditLog;


public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByEntity(String entity);
}
