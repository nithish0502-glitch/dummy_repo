package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.util.Arrays;
import org.springframework.data.jpa.repository.Query;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.persistence.OneToMany;

import org.junit.jupiter.api.MethodOrderer;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
 class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // Test adding a new restaurant
    @Test
    @Order(2)
     void testAddRestaurant() throws Exception {
        String restaurantJson = "{ \"name\": \"Pizza Palace\", \"location\": \"Main Street\", \"description\": \"Best pizza in town\" }";

        mockMvc.perform(post("/api/restaurant")
            .contentType(MediaType.APPLICATION_JSON)
            .content(restaurantJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Pizza Palace"))
            .andExpect(jsonPath("$.location").value("Main Street"));
    }

    @Test
    @Order(3)
     void testDuplicateAddRestaurant() throws Exception {
        String restaurantJson = "{ \"name\": \"Pizza Palace\", \"location\": \"Main Street\", \"description\": \"Best pizza in town\" }";

        mockMvc.perform(post("/api/restaurant")
            .contentType(MediaType.APPLICATION_JSON)
            .content(restaurantJson))
            .andExpect(status().isConflict())
            .andExpect(content().string("A restaurant with the same name and location already exists."));

    }

    // Test retrieving a restaurant by ID
    @Test
    @Order(4)
     void testGetRestaurantById() throws Exception {
        // Assuming a restaurant with ID 1 exists from testAddRestaurant
        mockMvc.perform(get("/api/restaurant/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Pizza Palace"));
    }

    // Test retrieving a non-existent restaurant
    @Test
    @Order(5)
     void testGetRestaurantById_NotFound() throws Exception {
        mockMvc.perform(get("/api/restaurant/999")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Restaurant with ID 999 not found."));
    }

    // Test retrieving all restaurants
    @Test
    @Order(6)
     void testGetAllRestaurants() throws Exception {
        mockMvc.perform(get("/api/restaurant")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    @Order(1)
     void testGetAllRestaurants_NoContent() throws Exception {
        // This test assumes that the restaurant list is empty.
        mockMvc.perform(get("/api/restaurant")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    // Test adding a new dish to a restaurant
    @Test
    @Order(7)
     void testAddDish() throws Exception {
        String dishJson = "{  \"name\": \"Margherita\", \"price\": 10.0, \"stock\": 5, \"description\": \"Classic pizza\" }";

        // Assuming a restaurant with ID 1 exists
        mockMvc.perform(post("/api/dish/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(dishJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Margherita"));
    }

    // Test retrieving dishes that are under a certain price range
    @Test
    @Order(8)
     void testGetDishesUnderPriceRange() throws Exception {
        // This assumes that your service returns dishes under the price of 15.0
        mockMvc.perform(get("/api/dish/search/15.0")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].name").exists());
    }

    // Test when no dishes match the provided price range
    @Test
    @Order(9)
     void testGetDishesUnderPriceRange_NoDishes() throws Exception {
        mockMvc.perform(get("/api/dish/search/100.0")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent())
            .andExpect(content().string("No dishes found under the specified price range."));
    }

    // Test handling dish out of stock exception
    @Test
    @Order(10)
     void testDishOutOfStockException() throws Exception {
        String dishJson = "{  \"name\": \"Margherita\", \"price\": 10.0, \"stock\": 0, \"description\": \"Classic pizza\" }";

        // Assuming a restaurant with ID 1 exists
        mockMvc.perform(post("/api/dish/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(dishJson))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Dish is currently out of stock."));
    }

    // Test deleting an existing dish
    @Test
    @Order(11)
     void testDeleteDish() throws Exception {
        // Assuming a dish with ID 1 exists from testAddDish
        mockMvc.perform(delete("/api/dish/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }
    
    // Test deleting a non-existent dish
    @Test
    @Order(12)
     void testDeleteDish_NotFound() throws Exception {
        mockMvc.perform(delete("/api/dish/999")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Dish not found with ID: 999"));
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
            "src/main/java/com/examly/springapp/controller/RestaurantController.java",
            "src/main/java/com/examly/springapp/controller/DishController.java"
        };

        for (String filePath : controllerFiles) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(), "File does not exist: " + filePath);
        }

        // Test for files in the model folder
        String[] modelFiles = {
            "src/main/java/com/examly/springapp/model/Restaurant.java",
            "src/main/java/com/examly/springapp/model/Dish.java"
        };

        for (String filePath : modelFiles) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(), "File does not exist: " + filePath);
        }

        // Test for files in the repository folder
        String[] repoFiles = {
            "src/main/java/com/examly/springapp/repository/RestaurantRepo.java",
            "src/main/java/com/examly/springapp/repository/DishRepo.java"
        };

        for (String filePath : repoFiles) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(), "File does not exist: " + filePath);
        }

        // Test for files in the service folder
        String[] serviceFiles = {
            "src/main/java/com/examly/springapp/service/RestaurantService.java",
            "src/main/java/com/examly/springapp/service/DishService.java",
            "src/main/java/com/examly/springapp/service/RestaurantServiceImpl.java",
            "src/main/java/com/examly/springapp/service/DishServiceImpl.java"
        };

        for (String filePath : serviceFiles) {
            File file = new File(filePath);
            assertTrue(file.exists() && file.isFile(), "File does not exist: " + filePath);
        }
    }

    @Test
    void testRestaurantServiceInterfaceExists() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.service.RestaurantService");
            assertNotNull(clazz, "RestaurantService interface should exist.");
            assertTrue(clazz.isInterface(), "RestaurantService should be an interface.");
        } catch (ClassNotFoundException e) {
            fail("RestaurantService interface not found.");
        }
    }

    @Test
    void testDishServiceInterfaceExists() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.service.DishService");
            assertNotNull(clazz, "DishService interface should exist.");
            assertTrue(clazz.isInterface(), "DishService should be an interface.");
        } catch (ClassNotFoundException e) {
            fail("DishService interface not found.");
        }
    }

    @Test
     void testQueryAnnotationPresentInDishRepo() {
        try {
            Class<?> dishRepoClass = Class.forName("com.examly.springapp.repository.DishRepo");

            Method[] methods = dishRepoClass.getMethods();

            boolean hasQueryAnnotation = Arrays.stream(methods)
                    .anyMatch(method -> Arrays.stream(method.getDeclaredAnnotations())
                            .anyMatch(annotation -> annotation.annotationType().equals(Query.class)));

            assertTrue(hasQueryAnnotation,
                    "@Query annotation should be present on at least one method in DishRepo");
        } catch (ClassNotFoundException e) {
            fail("DishRepo class not found. Ensure the class name and package are correct.");
        }
    }

    @Test
     void testOneToManyAnnotationPresentInRestaurant() {
        try {
            Class<?> restaurantClass = Class.forName("com.examly.springapp.model.Restaurant");
            Field dishField = restaurantClass.getDeclaredField("dishes");
            OneToMany oneToManyAnnotation = dishField.getAnnotation(OneToMany.class);

            assertNotNull(oneToManyAnnotation,
                    "@OneToMany annotation should be present on 'dishes' field in Restaurant class");
        } catch (ClassNotFoundException e) {
            fail("Restaurant class not found");
        } catch (NoSuchFieldException e) {
            fail("Field 'dishes' not found in Restaurant class");
        }
    }
}