package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import java.io.File;
import java.util.Arrays;
import org.springframework.data.jpa.repository.Query;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.examly.springapp.model.Gym;
import com.examly.springapp.model.Membership;

import jakarta.persistence.OneToMany;
import org.junit.jupiter.api.MethodOrderer;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // Test adding a new Gym
    @Test
    @Order(2)
     void testAddGym() throws Exception {
        String gymJson = "{ \"name\": \"Fitness Plus\", \"location\": \"Downtown\", \"description\": \"Best gym in town\" }";

        mockMvc.perform(post("/api/gym")
            .contentType(MediaType.APPLICATION_JSON)
            .content(gymJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Fitness Plus"))
            .andExpect(jsonPath("$.location").value("Downtown"));
    }

    @Test
    @Order(3)
     void testDuplicateAddGym() throws Exception {
        String gymJson = "{ \"name\": \"Fitness Plus\", \"location\": \"Downtown\", \"description\": \"Best gym in town\" }";
        mockMvc.perform(post("/api/gym")
            .contentType(MediaType.APPLICATION_JSON)
            .content(gymJson))
            .andExpect(status().isConflict())
            .andExpect(content().string("A gym with the same name and location already exists."));
    }

    // Test retrieving a Gym by ID
    @Test
    @Order(4)
     void testGetGymById() throws Exception {
        // Assuming a gym with ID 1 exists from testAddGym
        mockMvc.perform(get("/api/gym/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Fitness Plus"));
    }

    // Test retrieving a non-existent Gym
    @Test
    @Order(5)
     void testGetGymById_NotFound() throws Exception {
        mockMvc.perform(get("/api/gym/999")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Gym with ID 999 not found."));
    }

    // Test retrieving all Gyms
    @Test
    @Order(6)
     void testGetAllGyms() throws Exception {
        mockMvc.perform(get("/api/gym")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    @Order(1)
         void testGetAllGyms_NoContent() throws Exception {
        // This test assumes that the gym list is empty.
        mockMvc.perform(get("/api/gym")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    // Test adding a new membership for a gym
    @Test
    @Order(7)
     void testAddMembership() throws Exception {
        String membershipJson = "{ \"memberName\": \"John Doe\", \"startDate\": \"2023-01-01\", \"endDate\": \"2023-12-31\", \"type\": \"Premium\" }";

        // Assuming a gym with ID 1 exists
        mockMvc.perform(post("/api/membership/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(membershipJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.memberName").value("John Doe"));
    }

    // Test retrieving memberships by gym ID
    @Test
    @Order(8)
     void testGetMembershipsByGymId() throws Exception {
        // This assumes that your service returns memberships for gym ID 1
        mockMvc.perform(get("/api/membership/gym/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].memberName").exists());
    }

    // Test when no memberships match the provided gym ID
    @Test
    @Order(9)
     void testGetMembershipsByGymId_NoMemberships() throws Exception {
        mockMvc.perform(get("/api/membership/gym/999")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
            //.andExpect(content().string("No memberships found for this gym."));
    }

    // Test renewing a membership before expiration
    @Test
    @Order(11)
     void testRenewMembership() throws Exception {
        String renewalJson = "{ \"memberName\": \"John Doe\", \"startDate\": \"2023-01-01\", \"endDate\": \"2045-12-31\", \"type\": \"Premium\" }";

        // Assuming a membership with ID 1 exists and is not expired
        mockMvc.perform(put("/api/membership/renew/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(renewalJson))
        .andExpect(status().isOk()) 
        .andExpect(jsonPath("$.endDate").value("2045-12-31"));
     }




    // Test renewing an expired membership
    @Test
@Order(12)
void testRenewExpiredMembership() throws Exception {
    String renewalJson = "{ \"memberName\": \"John Doe\", \"startDate\": \"2023-01-01\", \"endDate\": \"2045-12-31\", \"type\": \"Premium\" }";

    // Assuming a membership with ID 99 does not exist
    mockMvc.perform(put("/api/membership/renew/99")
        .contentType(MediaType.APPLICATION_JSON)
        .content(renewalJson)) 
        .andExpect(status().isNotFound())
        .andExpect(content().string("Membership with ID:99 not found."));
}


    
    // Test retrieving all expired memberships
    @Test
    @Order(10)
     void testGetExpiredMemberships() throws Exception {
        mockMvc.perform(get("/api/membership/expired")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].memberName").exists());
    }

    @Test
    void testFoldersAndFilesExist() {
        // Test for folders
        String[] directories = {
            "src/main/java/com/examly/springapp/controller",
            "src/main/java/com/examly/springapp/model",
            "src/main/java/com/examly/springapp/repository",
            "src/main/java/com/examly/springapp/service"
        };

        for (String directoryPath : directories) {
            File directory = new File(directoryPath);
            assertTrue(directory.exists() && directory.isDirectory(), "Directory does not exist: " + directoryPath);
        }

        // Test for files in the controller folder
        String[] controllerFiles = {
            "src/main/java/com/examly/springapp/controller/GymController.java",
            "src/main/java/com/examly/springapp/controller/MembershipController.java"
        };

        for (String filePath : controllerFiles) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(), "File does not exist: " + filePath);
        }

        // Test for files in the model folder
        String[] modelFiles = {
            "src/main/java/com/examly/springapp/model/Gym.java",
            "src/main/java/com/examly/springapp/model/Membership.java"
        };

        for (String filePath : modelFiles) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(), "File does not exist: " + filePath);
        }

        // Test for files in the repository folder
        String[] repoFiles = {
            "src/main/java/com/examly/springapp/repository/GymRepo.java",
            "src/main/java/com/examly/springapp/repository/MembershipRepo.java"
        };

        for (String filePath : repoFiles) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(), "File does not exist: " + filePath);
        }

        // Test for files in the service folder
        String[] serviceFiles = {
            "src/main/java/com/examly/springapp/service/GymService.java",
            "src/main/java/com/examly/springapp/service/MembershipService.java",
            "src/main/java/com/examly/springapp/service/GymServiceImpl.java",
            "src/main/java/com/examly/springapp/service/MembershipServiceImpl.java"
        };

        for (String filePath : serviceFiles) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(), "File does not exist: " + filePath);
        }
    }

    @Test
    void testGymServiceInterfaceExists() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.service.GymService");
            assertNotNull(clazz, "GymService interface should exist.");
            assertTrue(clazz.isInterface(), "GymService should be an interface.");
        } catch (ClassNotFoundException e) {
            fail("GymService interface not found.");
        }
    }

    @Test
    void testMembershipServiceInterfaceExists() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.service.MembershipService");
            assertNotNull(clazz, "MembershipService interface should exist.");
            assertTrue(clazz.isInterface(), "MembershipService should be an interface.");
        } catch (ClassNotFoundException e) {
            fail("MembershipService interface not found.");
        }
    }

    @Test
     void testQueryAnnotationPresentInMembershipRepo() {
        try {
            Class<?> membershipRepoClass = Class.forName("com.examly.springapp.repository.MembershipRepo");

            Method[] methods = membershipRepoClass.getMethods();

            boolean hasQueryAnnotation = Arrays.stream(methods)
                    .anyMatch(method -> Arrays.stream(method.getDeclaredAnnotations())
                            .anyMatch(annotation -> annotation.annotationType().equals(Query.class)));

            assertTrue(hasQueryAnnotation,
                    "@Query annotation should be present on at least one method in MembershipRepo");
        } catch (ClassNotFoundException e) {
            fail("MembershipRepo class not found. Ensure the class name and package are correct.");
        }
    }

    @Test
     void testOneToManyAnnotationPresentInGym() {
        try {
            Class<?> gymClass = Class.forName("com.examly.springapp.model.Gym");
            Field membershipsField = gymClass.getDeclaredField("memberships");
            OneToMany oneToManyAnnotation = membershipsField.getAnnotation(OneToMany.class);
            assertNotNull(oneToManyAnnotation,
                    "@OneToMany annotation should be present on 'memberships' field in Gym class");
        } catch (ClassNotFoundException e) {
            fail("Gym class not found");
        } catch (NoSuchFieldException e) {
            fail("Field 'memberships' not found in Gym class");
        }
    }
}