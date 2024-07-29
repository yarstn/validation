package com.example.eventvalidation.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @NotNull(message = "ID cannot be null")
    @Min(value = 3, message = "ID must be greater than 2")
    private int id;  // Use Integer instead of int for nullability

    @NotNull(message = "Description cannot be null")
    @Size(min = 15, message = "Description must be at least 15 characters long")
    private String description;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 26, message = "Capacity must be more than 25")
    private int capacity;  // Use Integer instead of int for nullability

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;
}
