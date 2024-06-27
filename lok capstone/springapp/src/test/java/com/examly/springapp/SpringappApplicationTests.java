package com.examly.springapp;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.lang.reflect.Field;  // Import the Field class

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.persistence.ManyToOne;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {
	
	 @Autowired
	    private MockMvc mockMvc;
	
	
	 
    @Test
    @Order(1)
    public void backend_testGetInstituteAll() throws Exception {
        mockMvc.perform(get("/api/institute")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print())
     .andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$").isArray())
    .andReturn();
    
    }
 
    @Test
    @Order(2)
    public void backend_testGetCourseAll() throws Exception {
        mockMvc.perform(get("/api/course")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print())
     .andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$").isArray())
    .andReturn();
    
    }
 
    
    
    @Test
    @Order(3)
    public void backend_testCourseHasManyToOneAnnotation() {
        try {
            // Use reflection to get the Class object for the Course class
            Class<?> courseClass = Class.forName("com.examly.springapp.model.Course");

            // Get all declared fields in the Course class
            Field[] declaredFields = courseClass.getDeclaredFields();

            // Check each field for the @OneToMany annotation
            boolean hasManyToOne = false;
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(ManyToOne.class)) {
                	hasManyToOne = true;
                    break; // Stop checking once we find one field with @OneToMany
                }
            }

            // If no field with @OneToMany is found, fail the test
            if (!hasManyToOne) {
                fail("No field with @ManyToOne annotation found in Course class.");
            }

        } catch (ClassNotFoundException e) {
            // If the class is not found, fail the test
            fail("Class not found: " + e.getMessage());
        }
    }
    
    @Test
    @Order(4)
    public void backend_testAdmissionHasManyToOneAnnotation() {
        try {
            // Use reflection to get the Class object for the Course class
            Class<?> courseClass = Class.forName("com.examly.springapp.model.Admission");

            // Get all declared fields in the Course class
            Field[] declaredFields = courseClass.getDeclaredFields();

            // Check each field for the @OneToMany annotation
            boolean hasManyToOne = false;
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(ManyToOne.class)) {
                	hasManyToOne = true;
                    break; // Stop checking once we find one field with @OneToMany
                }
            }

            // If no field with @OneToMany is found, fail the test
            if (!hasManyToOne) {
                fail("No field with @ManyToOne annotation found in Admission class.");
            }

        } catch (ClassNotFoundException e) {
            // If the class is not found, fail the test
            fail("Class not found: " + e.getMessage());
        }
    }

    
    @Test
    @Order(5)
    public void backend_testInstituteInterfaceAndImplementation() {
        try {
            Class<?> interfaceClass = Class.forName("com.examly.springapp.service.InstituteService");
            Class<?> implementationClass = Class.forName("com.examly.springapp.service.InstituteServiceImpl");

            assertTrue(interfaceClass.isInterface(), "The specified class is not an interface");
            assertTrue(interfaceClass.isAssignableFrom(implementationClass), "Implementation does not implement the interface");
        } catch (ClassNotFoundException e) {
            fail("Interface or implementation not found");
        }
    }
    
    
    @Test
    @Order(6)
    public void backend_testAdmissionInterfaceAndImplementation() {
        try {
            Class<?> interfaceClass = Class.forName("com.examly.springapp.service.AdmissionService");
            Class<?> implementationClass = Class.forName("com.examly.springapp.service.AdmissionServiceImpl");

            assertTrue(interfaceClass.isInterface(), "The specified class is not an interface");
            assertTrue(interfaceClass.isAssignableFrom(implementationClass), "Implementation does not implement the interface");
        } catch (ClassNotFoundException e) {
            fail("Interface or implementation not found");
        }
    }
    
   
    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

	 @Test
   public void backend_testInstituteControllerClassExists() {
       checkClassExists("com.examly.springapp.controller.InstituteController");
   }
	 
	 @Test
	   public void backend_testCourseControllerClassExists() {
	       checkClassExists("com.examly.springapp.controller.CourseController");
	   }
	 
	 @Test
	   public void backend_testStudentModelClassExists() {
	       checkClassExists("com.examly.springapp.model.Student");
	   }
	 
	 @Test
	   public void backend_testInstituteModelClassExists() {
	       checkClassExists("com.examly.springapp.model.Institute");
	   }
	 
	 @Test
	   public void backend_testAdmissionControllerClassExists() {
	       checkClassExists("com.examly.springapp.controller.AdmissionController");
	   }
	 
	 @Test
	   public void backend_testAdmissionModelClassExists() {
	       checkClassExists("com.examly.springapp.model.Admission");
	   }

}
