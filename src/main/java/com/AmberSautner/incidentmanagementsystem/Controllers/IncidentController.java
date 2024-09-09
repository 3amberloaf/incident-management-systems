package com.AmberSautner.incidentmanagementsystem.controller;

import com.AmberSautner.incidentmanagementsystem.model.Incident;
import com.AmberSautner.incidentmanagementsystem.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;

    // Get all incidents
    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Create new incident
    @PostMapping
    public Incident createIncident(@RequestBody Incident incident) {
        return incidentRepository.save(incident);
    }

    // Get incident by ID
    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Incident not found"));
    }

    // Update an incident
    @PutMapping("/{id}")
    public Incident updateIncident(@PathVariable Long id, @RequestBody Incident updatedIncident) {
        Incident existingIncident = incidentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Incident not found"));
        existingIncident.setTitle(updatedIncident.getTitle());
        existingIncident.setDescription(updatedIncident.getDescription());
        existingIncident.setStatus(updatedIncident.getStatus());
        return incidentRepository.save(existingIncident);
    }

    // Delete an incident
    @DeleteMapping("/{id}")
    public void deleteIncident(@PathVariable Long id) {
        incidentRepository.deleteById(id);
    }
}
