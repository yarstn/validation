package com.example.eventvalidation.Controller;

import com.example.eventvalidation.ApiMessage.ApiMessage;
import com.example.eventvalidation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/event")
@Validated
public class EvantController {
    private final ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/events")
    public ResponseEntity<ArrayList<Event>> getEvents() {
        return ResponseEntity.ok(events);
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(new ApiMessage(errorMessage));
        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiMessage("Event added"));
    }


    @PutMapping("/update/{index}")
    public ResponseEntity<ApiMessage> updateEvent(@Valid @RequestBody Event event, @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(new ApiMessage(errorMessage));
        }

        if (index < 0 || index >= events.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiMessage("Index out of bounds"));
        }
        events.set(index, event);
        return ResponseEntity.ok(new ApiMessage("Event updated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<ApiMessage> deleteEvent(@PathVariable int index) {
        if (index < 0 || index >= events.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiMessage("Index out of bounds"));
        }
        events.remove(index);
        return ResponseEntity.ok(new ApiMessage("Event deleted"));
    }

    @PutMapping("/capacity/{index}/{capacity}")
    public ResponseEntity<ApiMessage> setCapacity(@PathVariable int index, @PathVariable int capacity) {
        if (index < 0 || index >= events.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiMessage("Index out of bounds"));
        }
        Event event = events.get(index);
        event.setCapacity(capacity);
        events.set(index, event);
        return ResponseEntity.ok(new ApiMessage("Event capacity updated"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<ApiMessage> searchEvent(@PathVariable int id) {
        Optional<Event> eventOptional = events.stream()
                .filter(event -> event.getId() == id)
                .findFirst();

        if (eventOptional.isPresent()) {
            return ResponseEntity.ok(new ApiMessage("Event found"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiMessage("Event not found"));
        }
    }
}
