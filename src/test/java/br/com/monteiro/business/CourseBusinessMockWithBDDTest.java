package br.com.monteiro.business;

import br.com.monteiro.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


public class CourseBusinessMockWithBDDTest {

    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setup() {
        //Given
        mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);
        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
        ;
    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAStub() {

        given(mockService.retrieveCourses("Leandro")).willReturn(courses);

        //Then
        var filteredCourses = business.retriveCoursesRelatedToString("Leandro");
        assertThat(filteredCourses.size(), is(4));

    }

    @Test
    void testCoursesRelatedToSpring_When_UsingAFooBarStudent() {

        //When
        var filteredCourses = business.retriveCoursesRelatedToString("Foo Bar");

        //Then
        assertThat(filteredCourses.size(), is(0));

    }

    @DisplayName("Delete Courses not Related to Spring Using Mockito sould call Method")
    @Test
    void testDeleteCourseNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourse(){

        //Given
        given(mockService.retrieveCourses("Leandro"))
                .willReturn(courses);

        //When
        business.deleteCoursesNotRelatedToSpring("Leandro");

        //Then
        verify(mockService, atLeastOnce())
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockService, atLeast(1))
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockService)
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockService, times(1))
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockService)
                .deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");
        verify(mockService, never())
                .deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");

    }


    @DisplayName("Delete Courses not Related to Spring Using Mockito sould call Method")
    @Test
    void testDeleteCourseNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourseV2(){

        //Given
        given(mockService.retrieveCourses("Leandro"))
                .willReturn(courses);

        //When
        business.deleteCoursesNotRelatedToSpring("Leandro");

        //Then
        then(mockService)
                .should()
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        then(mockService)
                .should()
                .deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");
        then(mockService)
                .should(never())
                .deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
    }

}
