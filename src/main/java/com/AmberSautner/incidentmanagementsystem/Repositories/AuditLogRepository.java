package com.AmberSautner.incidentmanagementsystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AmberSautner.incidentmanagementsystem.Entities.AuditLog;


public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
