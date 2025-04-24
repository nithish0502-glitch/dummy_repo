package com.examly.springapp;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(2)
    void testCreateUser_Success() throws Exception {
        String userJson = "{ \"name\": \"Alice\", \"department\": \"Engineering\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("Engineering"));
    }

       

    @Test
    @Order(3)
    void testCreateLaptop_Success() throws Exception {
        String laptopJson = "{ \"brand\": \"Dell\", \"model\": \"XPS 15\", \"serialNumber\": \"ABC123XYZ\", \"status\": \"Assigned\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/laptop/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(laptopJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("Dell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Assigned"));
    }
 
    @Test
    @Order(4)
    void testGetAllLaptops_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/laptop")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].model").value("XPS 15"));
    }

    @Test
    @Order(1)
    void testGetAllLaptops_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/laptop")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No laptops found."));
    }

    @Test
    @Order(5)
    void testUpdateLaptop_Success() throws Exception {
        String laptopJson = "{ \"brand\": \"Dell\", \"model\": \"XPS 14\" }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/laptop/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(laptopJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("Dell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("XPS 14"));
    }

    @Test
    @Order(6)
    void testUpdateLaptop_NotFound() throws Exception {
        String laptopJson = "{ \"brand\": \"Dell\", \"model\": \"XPS 15\" }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/laptop/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(laptopJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Laptop with ID 99 not found."));
                

    }

    @Test
void testAssignLaptopToUser_UserNotFound() throws Exception {
    String laptopJson = "{ \"brand\": \"Dell\", \"model\": \"XPS 15\", \"serialNumber\": \"ABC123XYZ\", \"status\": \"Assigned\" }";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/laptop/user/99")
            .contentType(MediaType.APPLICATION_JSON)
            .content(laptopJson)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.content().string("User not found"));
          
}

    @Test
    @Order(10)
    void testGetLaptopById_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/laptop/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("Dell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("XPS 14"));
    }

    @Order(11)
    @Test
    void testGetLaptopsByDepartment_Success() throws Exception {
        String department = "Engineering";
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/laptop/byDepartment/" + department)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.department").value("Engineering"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.serialNumber").exists());
    }
            
    @Test
    @Order(12)
    void testGetLaptopById_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/laptop/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Laptop with ID 100 not found."));
    }

    @Test
    @Order(13)
    void testDeleteLaptop_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/laptop/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Laptop deleted successfully."));
    }

    @Test
    @Order(14)
    void testDeleteLaptop_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/laptop/200")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Laptop with ID 200 not found."));
    }

    @Test
    @Order(15)
    void testLaptopUnderMaintenanceException() {
        String laptopJson = "{"
                + "\"brand\": \"Dell\","
                + "\"model\": \"XPS 13\","
                + "\"serialNumber\": \"SN123456\","
                + "\"status\": \"Under Maintenance\""
                + "}";

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/laptop/user/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(laptopJson)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string("Laptop is under maintenance and cannot be assigned.")); 
    
        } catch (Exception e) {
            fail("LaptopUnderMaintenanceException should be thrown for a laptop under maintenance.");
        }
    }
    

    @Test
    void testQueryAnnotationPresentInLaptopRepository() {
        try {
            Class<?> repoClass = Class.forName("com.examly.springapp.repository.LaptopRepository");

            Method[] methods = repoClass.getDeclaredMethods();

            boolean hasQueryAnnotation = Arrays.stream(methods)
                    .flatMap(method -> Arrays.stream(method.getAnnotations()))
                    .anyMatch(annotation -> annotation.annotationType().equals(Query.class));

            assertTrue(hasQueryAnnotation,
                    "@Query annotation should be present on at least one method in LaptopRepository");
        } catch (ClassNotFoundException e) {
            fail("LaptopRepository class not found");
        }
    }

    @Test
    void testFoldersExist() {
        String[] folders = {
                "src/main/java/com/examly/springapp/controller",
                "src/main/java/com/examly/springapp/model",
                "src/main/java/com/examly/springapp/repository",
                "src/main/java/com/examly/springapp/service",
                "src/main/java/com/examly/springapp/exception"
        };

        for (String folderPath : folders) {
            File directory = new File(folderPath);
            assertTrue(directory.exists() && directory.isDirectory(),
                    "Folder does not exist: " + folderPath);
        }
    }

    @Test
    void testFilesExist() {
        String[] files = {
                "src/main/java/com/examly/springapp/controller/LaptopController.java",
                "src/main/java/com/examly/springapp/controller/UserController.java",
                "src/main/java/com/examly/springapp/model/Laptop.java",
                "src/main/java/com/examly/springapp/model/User.java",
                "src/main/java/com/examly/springapp/repository/LaptopRepository.java",
                "src/main/java/com/examly/springapp/repository/UserRepository.java",
                "src/main/java/com/examly/springapp/service/LaptopService.java",
                "src/main/java/com/examly/springapp/service/UserService.java",
                "src/main/java/com/examly/springapp/service/LaptopServiceImpl.java",
                "src/main/java/com/examly/springapp/service/UserServiceImpl.java"
        };

        for (String filePath : files) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(),
                    "File does not exist: " + filePath);
        }
    }
}