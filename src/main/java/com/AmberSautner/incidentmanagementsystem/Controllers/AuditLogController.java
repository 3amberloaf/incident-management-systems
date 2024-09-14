package com.AmberSautner.incidentmanagementsystem.Controllers;

import com.AmberSautner.incidentmanagementsystem.Entities.AuditLog;
import com.AmberSautner.incidentmanagementsystem.Repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditlogs")
public class AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Get all audit logs
    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    // Get audit logs by entity type
    @GetMapping("/entity/{entity}")
    public List<AuditLog> getAuditLogsByEntity(@PathVariable String entity) {
        return auditLogRepository.findByEntity(entity);
    }

    // Create a new audit log (Typically logs are automatically created, but this is an example)
    @PostMapping
    public ResponseEntity<AuditLog> createAuditLog(@RequestBody AuditLog auditLog) {
        AuditLog savedAuditLog = auditLogRepository.save(auditLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuditLog);
    }
}
