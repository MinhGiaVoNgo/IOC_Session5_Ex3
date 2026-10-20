package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.dto.PageResponse;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.enums.CourseStatus;
import com.example.coursemanagement.repository.CourseRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Lấy danh sách khóa học có phân trang, sắp xếp, và lọc theo status.
     */
    public PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status) {
        // Safety check
        if (page < 0) {
            page = 0;
        }
        if (sortBy == null || sortBy.trim().isEmpty()) {
            sortBy = "id";
        }

        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Sử dụng JPQL findAllByStatus thay vì findAll
        Page<Course> coursePage = courseRepository.findAllByStatus(status, pageable);

        // Map Page<Course> → Page<CourseResponse>
        Page<CourseResponse> courseResponsePage = coursePage.map(course -> CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructor(course.getInstructor())
                .price(course.getPrice())
                .status(course.getStatus())
                .build()
        );

        // Chuyển Page → PageResponse
        return PageResponse.<CourseResponse>builder()
                .items(courseResponsePage.getContent())
                .page(courseResponsePage.getNumber())
                .size(courseResponsePage.getSize())
                .totalItems(courseResponsePage.getTotalElements())
                .totalPages(courseResponsePage.getTotalPages())
                .last(courseResponsePage.isLast())
                .build();
    }
}
