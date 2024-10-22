package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.persistence.ManyToOne;

import java.lang.reflect.Field;  // Import the Field class

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

@Autowired
private MockMvc mockMvc;


    @Test
	@Order(1)
    public void backend_day10_testControllerFolder() {

		String directoryPath = "src/main/java/com/examly/springapp/controller";

		File directory = new File(directoryPath);
 
		assertTrue(directory.exists() && directory.isDirectory());
 
	}
    
    @Test
    @Order(2)
	public void backend_day10_testControllerFileTest() {

		String filePath = "src/main/java/com/examly/springapp/controller/TestController.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
    

   @Test
   @Order(3)
   public void backend_day10_testWelcomeApi() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.get("/api/test/welcome"))
			.andExpect(MockMvcResultMatchers.status().isOk());
       }

   
   
   @Test
   @Order(4)
   public void backend_day11_testModelFolder() {

   		String directoryPath = "src/main/java/com/examly/springapp/model";

   		File directory = new File(directoryPath);
    
   		assertTrue(directory.exists() && directory.isDirectory());
    
   	}
	
	@Test
    @Order(5)
	public void backend_day11_testModelFileFlight() {

		String filePath = "src/main/java/com/examly/springapp/entity/Flight.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	@Order(6)
	public void backend_day11_testGetAllFlightList() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/flights")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andReturn();
	}
	 
	@Test
	@Order(7)
 	public void backend_day12_testcontrollerteamFile() {
 
		String filePath = "src/main/java/com/examly/springapp/controller/FlightController.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
    @Test
	@Order(8)
 	public void backend_day12_testserviceFlightFile() {
 
		String filePath = "src/main/java/com/examly/springapp/service/FlightService.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
    }
	
	@Test
@Order(9)
public void backend_day12_testAddFlight() throws Exception {
    String flightData = "{\"flightId\": 1,\"flightNumber\": \"AI202\", \"airline\": \"Air India\", \"departureLocation\": \"Delhi\", \"arrivalLocation\": \"Mumbai\", \"departureTime\": \"2024-10-21T10:00:00\", \"arrivalTime\": \"2024-10-21T12:00:00\", \"price\": 7500.00}";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/flights") // Assuming the endpoint is /api/flight
            .contentType(MediaType.APPLICATION_JSON)
            .content(flightData)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.flightNumber").value("AI202"))
            .andExpect(jsonPath("$.airline").value("Air India"))
            .andExpect(jsonPath("$.departureLocation").value("Delhi"))
            .andExpect(jsonPath("$.arrivalLocation").value("Mumbai"))
            .andExpect(jsonPath("$.departureTime").value("2024-10-21T10:00:00"))
            .andExpect(jsonPath("$.arrivalTime").value("2024-10-21T12:00:00"))
            .andExpect(jsonPath("$.price").value(7500.00))
            .andReturn();
}



	@Test
    @Order(10)
    void backend_day13_testGetAllFlights() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/flights")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[?(@.airline == 'Air India')]").exists())
                .andReturn();
    }

	@Test
	@Order(11)
 	public void backend_day14_testmodelFile() {
 
		String filePath = "src/main/java/com/examly/springapp/model/Booking.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
    }


	@Test
	@Order(12)
 	public void backend_day14_testserviceFile() {
 
		String filePath = "src/main/java/com/examly/springapp/repository/BookingRepo.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	
// 	@Test
// @Order(13)
// void backend_day14_testAddBooking() throws Exception {

//     String bookingData = "{\"flight\": {\"flightId\": 1}, \"user\": {\"userId\": 1}, \"bookingDate\": \"2024-10-21T10:00:00\", \"numberOfPassengers\": 2, \"status\": \"CONFIRMED\"}";

//     mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings") // Assuming the endpoint is /api/booking
//             .contentType(MediaType.APPLICATION_JSON)
//             .content(bookingData)
//             .accept(MediaType.APPLICATION_JSON))
//             .andExpect(MockMvcResultMatchers.status().isOk())
//             .andExpect(jsonPath("$.numberOfPassengers").value(2))
//             .andReturn();
// }




// @Test
// @Order(14)
// void backend_day14_testGetAllBookings() throws Exception {

//     mockMvc.perform(MockMvcRequestBuilders.get("/api/bookings")
//             .accept(MediaType.APPLICATION_JSON))
//             .andExpect(MockMvcResultMatchers.status().isOk())
//             .andExpect(jsonPath("$").isArray())
//             .andExpect(jsonPath("$[?(@.numberOfPassengers == 2)]").exists()) // Change the number as needed
//             .andReturn();
// }

	@Test
public void backend_day15_testBookingHasManyToOneAnnotations() {
    try {
        // Use reflection to get the Class object for the Booking class
        Class<?> bookingClass = Class.forName("com.examly.springapp.model.Booking");

        // Get all declared fields in the Booking class
        Field[] declaredFields = bookingClass.getDeclaredFields();

        // Check for the fields with @ManyToOne annotation
        boolean hasFlightManyToOne = false;
        boolean hasUserManyToOne = false;

        for (Field field : declaredFields) {
            if (field.getName().equals("flight") && field.isAnnotationPresent(ManyToOne.class)) {
                hasFlightManyToOne = true;
            }
            if (field.getName().equals("user") && field.isAnnotationPresent(ManyToOne.class)) {
                hasUserManyToOne = true;
            }
        }

        // Fail the test if any field is missing the @ManyToOne annotation
        if (!hasFlightManyToOne) {
            fail("No field with @ManyToOne annotation found for 'flight' in Booking class.");
        }
        if (!hasUserManyToOne) {
            fail("No field with @ManyToOne annotation found for 'user' in Booking class.");
        }

    } catch (ClassNotFoundException e) {
        // If the class is not found, fail the test
        fail("Class not found: " + e.getMessage());
    }
}

@Test
public void backend_day16_testBookingServiceInterfaceAndImplementation() {
    try {
        Class<?> interfaceClass = Class.forName("com.examly.springapp.service.BookingService");
        Class<?> implementationClass = Class.forName("com.examly.springapp.service.BookingServiceImpl");

        assertTrue(interfaceClass.isInterface(), "The specified class is not an interface");
        assertTrue(interfaceClass.isAssignableFrom(implementationClass), "Implementation does not implement the interface");
    } catch (ClassNotFoundException e) {
        fail("Interface or implementation not found: " + e.getMessage());
    }
}


	
	
	@Test
	@Order(16)
	public void backend_day17_testExceptionFile() {
		 
		String filePath = "src/main/java/com/examly/springapp/exception/ExceedsTeamBudgetException.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	@Test
	@Order(17)
 	public void backend_day18_testcontrollerOrganizerFile() {
 
		String filePath = "src/main/java/com/examly/springapp/controller/OrganizerController.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	@Test
	@Order(18)
	void backend_day18_getallSoldPlayer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/organizer/sold-players")
				.accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andReturn();
	}

    
    @Test
	@Order(19)
 	public void backend_day19_testEntityUserFile() {
 
		String filePath = "src/main/java/com/examly/springapp/entity/User.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(20)
 	public void backend_day20_testCorsUserFile() {
 
		String filePath = "src/main/java/com/examly/springapp/configuration/CorsConfig.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
    
	
}