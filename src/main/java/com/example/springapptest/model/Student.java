package com.example.springapptest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CGPA = "cgpa";

    @Schema(description = "Unique identifier of the Student.", example = "1", required = true)
    private Long id;

    @Schema(description = "Name  of the Student.", example = "ABC", required = true)
    @Size(max = 100)
    private String name;

    @Schema(description = "CGPA of the Student.", example = "9.2", required = true)
    private Float cgpa;


}
