package com.AmberSautner.incidentmanagementsystem.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
        return ResponseEntity.ok(savedIncident);
    }
}
