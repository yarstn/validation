package com.example.validationexc.TrackerSystemController;

import com.example.validationexc.ApiMessage.ApiMessage;
import com.example.validationexc.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/track-system")
public class TrackSystemController {
    private final ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/projects")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addProject(@Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiMessage(message));
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiMessage("Project added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<Object> updateProject(@PathVariable int index, @Valid @RequestBody Project updatedProject, Errors errors) {
        if (index < 0 || index >= projects.size()) {
            return ResponseEntity.status(404).body(new ApiMessage("Project not found"));
        }
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiMessage(message));
        }
        projects.set(index, updatedProject);
        return ResponseEntity.status(200).body(updatedProject);
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        if (index < 0 || index >= projects.size()) {
            return ResponseEntity.status(404).body(new ApiMessage("Project not found"));
        }
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiMessage("Project deleted"));
    }
//NOT_STARTED
@PutMapping("/status/{id}")
public ResponseEntity<ApiMessage> updateProjectStatus(@PathVariable int id) {
    for (Project project : projects) {

        if (project.getId() == id) {

            switch (project.getStatus()) {
                case "NOT_STARTED":
                    if (project.getStatus().equals("NOT_STARTED")) {
                        project.setStatus("IN_PROGRESS");
                        return ResponseEntity.status(200).body(new ApiMessage("Project status updated to IN_PROGRESS"));
                    } else {
                        return ResponseEntity.status(400).body(new ApiMessage("Invalid status transition"));
                    }
                case "IN_PROGRESS":
                    if (project.getStatus().equals("IN_PROGRESS")) {
                        project.setStatus("COMPLETED");
                        return ResponseEntity.status(200).body(new ApiMessage("Project status updated to COMPLETED"));
                    }
                case "COMPLETED":
                    if (project.getStatus().equals("COMPLETED")) {
                        project.setStatus("COMPLETED");
                        return ResponseEntity.status(200).body(new ApiMessage("Project status is already COMPLETED"));
                    }
            }
        }
    }
    return ResponseEntity.status(404).body(new ApiMessage("Project not found"));
}
//DONE
@GetMapping("/search/{title}")
public ResponseEntity searchProjectByTitle(@PathVariable String title) {
    for (Project project : projects) {
        if (project.getTitle().equals(title)) { // Use .equals() for string comparison
            return ResponseEntity.status(200).body(new ApiMessage("Project found"));
        }
    }
    return ResponseEntity.status(404).body(new ApiMessage("Project not found"));
}


//DONE
    @GetMapping("/company-name/{company}")
    public ResponseEntity searchProjectByName(@PathVariable String company) {
        ArrayList<Project> projects1 = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equals(company)) {
                projects1.add(project);
            }
            return ResponseEntity.ok(projects1);

        }
        return ResponseEntity.status(200).body(new ApiMessage("Project found"));

    }
}
}
