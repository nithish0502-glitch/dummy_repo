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

import static org.junit.Assert.assertThat;
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
	void testAddDepartment() throws Exception {
		String departmentJson = "{ \"id\": 1, \"departmentName\": \"Test Department\", \"visionStatement\": \"Test visionStatement\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/department")
				.contentType(MediaType.APPLICATION_JSON)
				.content(departmentJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.departmentName").value("Test Department"))
				.andReturn();
	}

	@Test
	@Order(2)
	void testAddEmployee() throws Exception {
		String employeeJson = "{ \"employeeId\": 1, \"name\": \"Test Employee\", \"salary\": 50000, \"designation\": \"Software Engineer\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/employee/1/department")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.name").value("Test Employee"))
				.andReturn();
	}

	@Test
	@Order(3)
	void testGetDepartmentById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/department/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.employees[?(@.designation == 'Software Engineer')]").exists());
	}
	
	@Test
    @Order(4)
    public void testgetAllEmployees() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
						"$[?(@.designation  == 'Software Engineer')]")
                
                        .exists());
    }

	@Test
	@Order(5)
	void testDuplicateException() throws Exception {
		String jsonPayload ="{ \"id\": 1, \"departmentName\": \"Test Department\", \"visionStatement\": \"Test visionStatement\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/department")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonPayload)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
				.andExpect(content().string("Department with name Test Department already exists!"))
				.andReturn();
	}



  

@Test
@Order(6)
void testDeleteEmployee() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Employee 1 deleted successfully"));
}

@Test
    public void testOneToManyAnnotationPresent() {
        try {
            Class<?> departmentClass = Class.forName("com.examly.springapp.model.Department");
            Field employeesField = departmentClass.getDeclaredField("employees");
            OneToMany oneToManyAnnotation = employeesField.getAnnotation(OneToMany.class);

            assertNotNull(oneToManyAnnotation, "@OneToMany annotation should be present on 'employees' field");
        } catch (ClassNotFoundException e) {
            fail("Department class not found");
        } catch (NoSuchFieldException e) {
            fail("Field 'employees' not found in Department class");
        }
    }

@Test
public void testMethodHasQueryAnnotation() {
        try {
            // Use reflection to get the Class object for the EmployeeRepo interface
            Class<?> repoClass = Class.forName("com.examly.springapp.repository.EmployeeRepo");

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
                fail("No method with @Query annotation found in EmployeeRepo interface.");
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
	public void testDepartmentControllerFile() {
		String filePath = "src/main/java/com/examly/springapp/controller/DepartmentController.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testEmployeeControllerFile() {
		String filePath = "src/main/java/com/examly/springapp/controller/EmployeeController.java";
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
	public void testDepartmentModelFile() {
		String filePath = "src/main/java/com/examly/springapp/model/Department.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testEmployeeModelFile() {
		String filePath = "src/main/java/com/examly/springapp/model/Employee.java";
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
	public void testDepartmentRepositoryFile() {
		String filePath = "src/main/java/com/examly/springapp/repository/DepartmentRepo.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testEmployeeRepositoryFile() {
		String filePath = "src/main/java/com/examly/springapp/repository/EmployeeRepo.java";
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

	public void testDepartmentServiceFile() {
		String filePath = "src/main/java/com/examly/springapp/service/DepartmentService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testEmployeeServiceFile() {
		String filePath = "src/main/java/com/examly/springapp/service/EmployeeService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testEmployeeServcieImplFile() {
		String filePath = "src/main/java/com/examly/springapp/service/DepartmentServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testEmployeeServiceImplFile() {
		String filePath = "src/main/java/com/examly/springapp/service/EmployeeServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
}
