package com.examly.springapp;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.ActiveProfiles;
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
    @Order(3)
    void testCreateCustomer_MissingFields() throws Exception {
        String customerJson = "{ \"name\": \"\", \"phoneNumber\": \"\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Customer name and phone number are required."));
    }

    @Test
    @Order(4)
    void testGetAllCustomers_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address").value("123 Main St, New York"));
    }

    @Test
    @Order(1)
    void testGetAllCustomers_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("No customers found."));
    }

    @Test
    @Order(5)
    void testUpdateCustomer_Success() throws Exception {
        String customerJson = "{ \"name\": \"Updated Name\", \"phoneNumber\": \"9876543210\" }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("9876543210"));
    }

    @Test
    @Order(6)
    void testUpdateCustomer_NotFound() throws Exception {
        String customerJson = "{ \"name\": \"Updated Name\", \"phoneNumber\": \"9876543210\" }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/customer/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Customer with ID 99 not found."));
    }

    @Test
    @Order(7)
    void testCreateAccount_Success() throws Exception {
        String accountJson = "{"
                + "\"accountNumber\": \"AC-12345\","
                + "\"balance\": 1000.00,"
                + "\"isActive\": true"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("AC-12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000.00));
    }

    @Test
    @Order(8)
    void testCustomerHasLinkedAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].account.accountNumber").value("AC-12345"));
    }

    @Test
    @Order(9)
    void testCreateAccount_CustomerNotFound() throws Exception {
        String accountJson = "{ \"accountNumber\": \"AC-12345\", \"balance\": 1000.00 }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Customer with ID 99 not found."));
    }

    @Test
    @Order(10)
    void testGetAccountById_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("AC-12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(1000.00));
    }

    @Test
    @Order(11)
    void testGetAccountsByCustomer_Success() throws Exception {
        String customerName = "John Doe";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/byCustomer/" + customerName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.name").value(customerName));
    }

    @Test
    @Order(12)
    void testGetAccountById_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Account with ID 100 not found."));
    }

    @Test
    @Order(13)
    void testDeleteAccount_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Account deleted successfully."));
    }

    @Test
    @Order(14)
    void testDeleteAccount_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/200")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Account with ID 200 not found."));
    }

    @Test
    @Order(15)
    void testAccountAccessDeniedException() {
        try {
            String customerJson = "{"
                    + "\"name\": \"Unauthorized User\","
                    + "\"email\": \"unauth@example.com\","
                    + "\"phoneNumber\": \"0000000000\","
                    + "\"address\": \"No Access St\","
                    + "\"isVerified\": false"
                    + "}";

            mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/access/denied")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(customerJson)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andExpect(MockMvcResultMatchers.content().string("Unauthorized access to customer accounts. Customer is not verified."));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception thrown during testAccountAccessDeniedException: " + e.getMessage());
        }
    }

    @Test
    @Order(16)
    void testFindAllCustomersWithActiveAccounts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/activeAccounts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].account.isActive").value(true));
    }

    @Test
     void testQueryAnnotationPresentInAccountRepository() {
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
