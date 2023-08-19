package br.com.monteiro.business;

import br.com.monteiro.service.CourseService;
import br.com.monteiro.service.stubs.CourseServiceStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseBusinessStubTest {

    @Test
    void testCoursesRelatedToSpring_When_UsingAStub() {

        //Given
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        //When
        var filteredCourses = business.retriveCoursesRelatedToString("Leandro");

        //Then
        assertEquals(4, filteredCourses.size());

    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAFooBarStudent() {

        //Given
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        //When
        var filteredCourses = business.retriveCoursesRelatedToString("Foo Bar");

        //Then
        assertEquals(0, filteredCourses.size());

    }

}
