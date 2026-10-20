package com.example.coursemanagement.config;

import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.enums.CourseStatus;
import com.example.coursemanagement.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public DataInitializer(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) {
        if (courseRepository.count() == 0) {
            List<Course> courses = List.of(
                Course.builder().title("Java Spring Boot").description("Khóa học Spring Boot cơ bản").instructor("Nguyen Van A").price(500000.0).status(CourseStatus.ACTIVE).build(),
                Course.builder().title("Angular Frontend").description("Khóa học Angular từ cơ bản đến nâng cao").instructor("Tran Van B").price(600000.0).status(CourseStatus.ACTIVE).build(),
                Course.builder().title("React JS").description("Khóa học React JS cho người mới bắt đầu").instructor("Le Thi C").price(450000.0).status(CourseStatus.INACTIVE).build(),
                Course.builder().title("Database MySQL").description("Khóa học MySQL chuyên sâu").instructor("Pham Van D").price(350000.0).status(CourseStatus.ACTIVE).build(),
                Course.builder().title("Docker & Kubernetes").description("Khóa học container hóa ứng dụng").instructor("Hoang Van E").price(700000.0).status(CourseStatus.INACTIVE).build()
            );
            courseRepository.saveAll(courses);
            System.out.println("✅ Đã thêm " + courses.size() + " khóa học mẫu vào database.");
        }
    }
}
