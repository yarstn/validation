package com.example.validationexc.Model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
@NotNull(message = "cannot be null")
@Size(min = 3, message = "length more than 2")
private int id;

@NotEmpty(message = "cannot be empty")
@Size(min = 9, message = "length more than 8")
private String title;

    @NotEmpty(message = "cannot be empty")
    @Size(min = 16 , message = " description legnth more than 15")
private String description;

  @NotNull(message = "status cannot be empty")
  @Pattern(regexp = "^(NOT_STARTED|IN_PROGRESS|COMPLETED)$", message = "Status must be one of: Not Started, In Progress, or Completed")
    private String status;

    @NotNull(message = "companyName cannot be empty")
    @Size(min = 7, message = "length more than 6")
    private String companyName;
}
