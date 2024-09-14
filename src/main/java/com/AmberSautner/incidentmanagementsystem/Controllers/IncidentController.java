package com.AmberSautner.incidentmanagementsystem.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AmberSautner.incidentmanagementsystem.Entities.Incident;
import com.AmberSautner.incidentmanagementsystem.Entities.User;
import com.AmberSautner.incidentmanagementsystem.Repositories.IncidentRepository;
import com.AmberSautner.incidentmanagementsystem.Repositories.UserRepository;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all incidents
    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // Get a single incident by ID
    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        Optional<Incident> incident = incidentRepository.findById(id);
        return incident.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new incident
    @PostMapping
    public ResponseEntity<?> createIncident(@RequestBody Incident incident) {
        // Check if the assigned user exists
        User assignedUser = incident.getAssignedUser();
        if (assignedUser != null && assignedUser.getId() != null) {
            Optional<User> user = userRepository.findById(assignedUser.getId());
            if (user.isPresent()) {
                // Set the actual user object from the database
                incident.setAssignedUser(user.get());
            } else {
                return ResponseEntity.badRequest().body("Assigned user not found.");
            }
        } else {
            return ResponseEntity.badRequest().body("Assigned user is required.");
        }

        // Save the incident
        Incident savedIncident = incidentRepository.save(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncident);
    }

    // Update an existing incident
    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @RequestBody Incident incidentDetails) {
        return incidentRepository.findById(id).map(incident -> {
            incident.setDescription(incidentDetails.getDescription());
            incident.setStatus(incidentDetails.getStatus());
            incident.setPriority(incidentDetails.getPriority());
            Incident updatedIncident = incidentRepository.save(incident);
            return ResponseEntity.ok(updatedIncident);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete an incident
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        if (incidentRepository.existsById(id)) {
            incidentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
