package com.examly.springapp;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.persistence.OneToMany;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


import java.io.File;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void testAddUniversity() throws Exception {
		String universityJson = "{ \"universityId\": 1, \"universityName\": \"Test University\", \"location\": \"Some Location\", \"studentPopulation\": 1000 }";

		mockMvc.perform(MockMvcRequestBuilders.post("/university")
				.contentType(MediaType.APPLICATION_JSON)
				.content(universityJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.universityName").value("Test University"))
				.andReturn();
	}

	@Test
	@Order(2)
	void testAddCourses() throws Exception {
		String courseJson = "{ \"courseId\": 1, \"name\": \"Test Course\", \"instructor\": \"Test Instructor\", \"fees\": 99.99 }";


		mockMvc.perform(MockMvcRequestBuilders.post("/course/1/university")
				.contentType(MediaType.APPLICATION_JSON)
				.content(courseJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.name").value("Test Course"))
				.andReturn();
	}

	@Test
	@Order(3)
	void testGetUniversitysById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/university/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.universityId").value(1))
				.andExpect(jsonPath("$.courses[?(@.name == 'Test Course')]").exists());
	}
	
	@Test
    @Order(4)
    public void testgetAllCoursesByFeesGreaterThan() throws Exception {

		int fees =90;
        mockMvc.perform(MockMvcRequestBuilders.get("/course/findtheCourseGreaterthan/{fees}",fees)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
						"$[?(@.name  == 'Test Course')]")
                
                        .exists());
    }



  
@Test
@Order(5)
void testDeleteCourse() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/course/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Course 1 deleted successfully"));
}

@Test
    public void testOneToManyAnnotationPresent() {
        try {
            Class<?> universityClass = Class.forName("com.examly.springapp.model.University");
            Field coursesField = universityClass.getDeclaredField("courses");
            OneToMany oneToManyAnnotation = coursesField.getAnnotation(OneToMany.class);

            assertNotNull(oneToManyAnnotation, "@OneToMany annotation should be present on 'courses' field");
        } catch (ClassNotFoundException e) {
            fail("University class not found");
        } catch (NoSuchFieldException e) {
            fail("Field 'courses' not found in University class");
        }
    }

@Test
public void testMethodHasQueryAnnotation() {
        try {
            // Use reflection to get the Class object for the EmployeeRepo interface
            Class<?> repoClass = Class.forName("com.examly.springapp.repository.CourseRepo");

            // Get all declared methods in the EmployeeRepo interface
            Method[] declaredMethods = repoClass.getDeclaredMethods();

            // Check each method for the @Query annotation
            boolean hasQueryAnnotation = false;
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(Query.class)) {
                    hasQueryAnnotation = true;
                    break; // Stop checking once we find one method with @Query
                }
            }

            // If no method with @Query is found, fail the test
            if (!hasQueryAnnotation) {
                fail("No method with @Query annotation found in CourseRepo interface.");
            }

        } catch (ClassNotFoundException e) {
            // If the class is not found, fail the test
            fail("Class not found: " + e.getMessage());
        }
    }





	@Test
	public void testControllerFolder() {
		String directoryPath = "src/main/java/com/examly/springapp/controller";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test
	public void testUniversityControllerFile() {
		String filePath = "src/main/java/com/examly/springapp/controller/UniversityController.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testCourseControllerFile() {
		String filePath = "src/main/java/com/examly/springapp/controller/CourseController.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testModelFolder() {
		String directoryPath = "src/main/java/com/examly/springapp/model";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test
	public void testUniversityModelFile() {
		String filePath = "src/main/java/com/examly/springapp/model/University.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testCourseModelFile() {
		String filePath = "src/main/java/com/examly/springapp/model/Course.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testRepositoryFolder() {
		String directoryPath = "src/main/java/com/examly/springapp/repository";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test
	public void testUniversityRepositoryFile() {
		String filePath = "src/main/java/com/examly/springapp/repository/UniversityRepo.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testCourseRepositoryFile() {
		String filePath = "src/main/java/com/examly/springapp/repository/CourseRepo.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testServiceFolder() {
		String directoryPath = "src/main/java/com/examly/springapp/service";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test

	public void testUniversityServiceFile() {
		String filePath = "src/main/java/com/examly/springapp/service/UniversityService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testCourseServiceFile() {
		String filePath = "src/main/java/com/examly/springapp/service/CourseService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testUniversityServiceImplFile() {
		String filePath = "src/main/java/com/examly/springapp/service/UniversityServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testCourseServiceImplFile() {
		String filePath = "src/main/java/com/examly/springapp/service/CourseServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
}
