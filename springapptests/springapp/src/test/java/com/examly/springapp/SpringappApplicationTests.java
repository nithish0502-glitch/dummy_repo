package com.examly.springapp;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    void testCreateCustomer_Success() throws Exception {
        String customerJson = "{"
                + "\"name\": \"John Doe\","
                + "\"email\": \"john.doe@example.com\","
                + "\"phoneNumber\": \"1234567890\","
                + "\"address\": \"123 Main St, New York\","
                + "\"isVerified\": true"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isVerified").value(true));
    }

    @Test
    @Order(15)
    void testAccountAccessDeniedException() throws Exception {
        String customerJson = "{"
                + "\"name\": \"Unauthorized User\","
                + "\"email\": \"unauth@example.com\","
                + "\"phoneNumber\": \"0000000000\","
                + "\"address\": \"Unknown\","
                + "\"isVerified\": false"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/access/denied")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().string("Unauthorized access to customer accounts. Verification failed."));
    }

    // The rest of your tests (from @Order(1) to @Order(14) and @Order(16)) remain unchanged.

    @Test
    public void testQueryAnnotationPresentInAccountRepository() {
        try {
            Class<?> repoClass = Class.forName("com.examly.springapp.repository.AccountRepository");

            Method[] methods = repoClass.getDeclaredMethods();

            boolean hasQueryAnnotation = Arrays.stream(methods)
                    .flatMap(method -> Arrays.stream(method.getAnnotations()))
                    .anyMatch(annotation -> annotation.annotationType().equals(Query.class));

            assertTrue(hasQueryAnnotation,
                    "@Query annotation should be present on at least one method in AccountRepository");
        } catch (ClassNotFoundException e) {
            fail("AccountRepository class not found");
        }
    }

    @Test
    public void testFoldersExist() {
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
    public void testFilesExist() {
        String[] files = {
                "src/main/java/com/examly/springapp/controller/CustomerController.java",
                "src/main/java/com/examly/springapp/controller/AccountController.java",
                "src/main/java/com/examly/springapp/model/Customer.java",
                "src/main/java/com/examly/springapp/model/Account.java",
                "src/main/java/com/examly/springapp/repository/CustomerRepository.java",
                "src/main/java/com/examly/springapp/repository/AccountRepository.java",
                "src/main/java/com/examly/springapp/service/CustomerService.java",
                "src/main/java/com/examly/springapp/service/AccountService.java",
                "src/main/java/com/examly/springapp/service/CustomerServiceImpl.java",
                "src/main/java/com/examly/springapp/service/AccountServiceImpl.java"
        };

        for (String filePath : files) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(),
                    "File does not exist: " + filePath);
        }
    }
}
