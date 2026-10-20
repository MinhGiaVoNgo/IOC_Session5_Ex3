package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.dto.PageResponse;
import com.example.coursemanagement.enums.CourseStatus;
import com.example.coursemanagement.service.CourseService;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status
    ) {
        PageResponse<CourseResponse> pageResponse = courseService.getPagedCoursesByStatus(page, size, sortBy, direction, status);

        ApiResponse<PageResponse<CourseResponse>> response = ApiResponse.<PageResponse<CourseResponse>>builder()
                .success(true)
                .message("Ok")
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
