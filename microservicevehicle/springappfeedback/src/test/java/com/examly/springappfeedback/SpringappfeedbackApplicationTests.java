package com.examly.springappfeedback;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.lang.reflect.Field; 
 
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class SpringappuserApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Order(2)
    @Test
    public void backend_testGetAllFeedBack() throws Exception {
        mockMvc.perform(get("/api/feedback")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print())
        .andExpect(content().contentType("application/json"))
    .andExpect(jsonPath("$").isArray())
    .andReturn();
    }
    
    @Test
    public void backend_testFeedbackHasManyToOneAnnotation() {
        try {
            // Use reflection to get the Class object for the Course class
            Class<?> courseClass = Class.forName("com.examly.springappfeedback.model.Feedback");

            // Get all declared fields in the Course class
            Field[] declaredFields = courseClass.getDeclaredFields();

            // Check each field for the @OneToOne annotation
            boolean hasOneToOne = false;
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(ManyToOne.class)) {
                    hasOneToOne = true;
                    break; // Stop checking once we find one field with @OneToMany
                }
            }
    
    
            // If no field with @OneToMany is found, fail the test
            if (!hasOneToOne) {
                fail("No field with @ManyToOne annotation found in Feedback class.");
            }

        } catch (ClassNotFoundException e) {
            // If the class is not found, fail the test
            fail("Class not found: " + e.getMessage());
        }
    }
 
@Test
public void backend_testLoanApplicationHasManyToOneAnnotation() {
    try {
        // Use reflection to get the Class object for the Course class
        Class<?> courseClass = Class.forName("com.examly.springappfeedback.model.Feedback");

        // Get all declared fields in the Course class
        Field[] declaredFields = courseClass.getDeclaredFields();

        // Check each field for the @OneToMany annotation
        boolean hasManyToOne = false;
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ManyToOne.class)) {
                hasManyToOne = true;
                break;
            }
        }


        // If no field with @OneToMany is found, fail the test
        if (!hasManyToOne) {
            fail("No field with @ManyToOne annotation found in Request class.");
        }

    } catch (ClassNotFoundException e) {
        // If the class is not found, fail the test
        fail("Class not found: " + e.getMessage());
    }
}

@Test
public void backend_testFeedbackInterfaceAndImplementation() {
    try {
        Class<?> interfaceClass = Class.forName("com.examly.springappfeedback.service.FeedbackService");
        Class<?> implementationClass = Class.forName("com.examly.springappfeedback.service.FeedbackServiceImpl");

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
 public void backend_testFeedbackControllerClassExists() {
   checkClassExists("com.examly.springappfeedback.controller.FeedbackController");
 }

 
 @Test
   public void backend_testFeedbackModelClassExists() {
       checkClassExists("com.examly.springappfeedback.model.Feedback");
   }

} 
