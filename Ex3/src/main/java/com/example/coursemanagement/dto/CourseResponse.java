package com.example.coursemanagement.dto;

import com.example.coursemanagement.enums.CourseStatus;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private String instructor;
    private Double price;
    private CourseStatus status;
}
